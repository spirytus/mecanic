package agh.mgr.mecanic;

import agh.mgr.mecanic.misc.tools.SerializableScanHistory;
import agh.mgr.mecanic.misc.tools.Visualizer;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.ui.RefineryUtilities;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.*;
// TODO: REVIEW

// TODO's:
// 1. Implement linear regression.
// From points that belongs to wall -> y=ax +b.
// Next measure distance to this wall.
// This will give you better accuracy (hopefully)

public class Positioner {
    public static final Integer LAST_POINT_DELTA = 5; // Difference from last measured point of wall. Odszumianie



    public static void execute(List<MapPoint> scannedPoints){
        List<List<Integer>> edges = findEdges(scannedPoints);
        System.out.println("ROGI");
        System.out.println(edges);
        List<List<MapPoint>> listOfWalls = null;
        try {
            listOfWalls = extractWalls(scannedPoints, edges);
        } catch (OMGNoEdgesDetectedException e) {
            e.printStackTrace();
            return;
        }
        //System.out.println("LISTA SCIAN");
        System.out.println(listOfWalls);

        Map<String, List<Double>> wallNameToClosestsPoints = new HashMap<String, List<Double>>();
        wallNameToClosestsPoints.put("LEFT", new LinkedList<Double>(Arrays.asList(-90.0)));
        wallNameToClosestsPoints.put("RIGHT", new LinkedList<Double>(Arrays.asList(90.0)));
        wallNameToClosestsPoints.put("FRONT", new LinkedList<Double>(Arrays.asList(0.0)));

        List<MapPoint> closestsPointOfWalls = new LinkedList<MapPoint>();
        for(List<MapPoint> wall : listOfWalls){
            Collections.sort(wall, new Comparator<MapPoint>() {
                @Override
                public int compare(MapPoint o1, MapPoint o2) {
                    if(o1.getDistance() > o2.getDistance()){
                        return 1;
                    } else if(o1.getDistance() < o2.getDistance()){
                        return -1;
                    }
                    return 0;
                }
            });
            // System.out.println("Minimal distance");
            //System.out.println(wall);
            closestsPointOfWalls.add(wall.get(0));
        }
        addClosestPointsToWalls(closestsPointOfWalls, wallNameToClosestsPoints);
      //  System.out.println(listOfWalls);

        //System.out.println(scannedPointsToCoordinates(scannedPoints));
    }

    private static void addClosestPointsToWalls(List<MapPoint> closestsPointOfWalls, Map<String, List<Double>> wallNameToClosestsPoints) {
        Collections.sort(closestsPointOfWalls, new Comparator<MapPoint>() {
            @Override
            public int compare(MapPoint o1, MapPoint o2) {
                if(o1.getAngle() > o2.getAngle()){
                    return 1;
                }
                else if(o1.getDistance() < o2.getDistance()){
                    return -1;
                }
                return 0;
            }
        });

        String []wallNames = {"LEFT", "FRONT", "RIGHT"};
        int wallIndex = 0;
        for(int i=0; i<wallNames.length; i++){
            String wallName = wallNames[i];
            double lastAngle = wallNameToClosestsPoints.get(wallName).get(wallNameToClosestsPoints.size()-1);
            double scannedAngle = closestsPointOfWalls.get(wallIndex).getAngle();
            System.out.print(wallName + " DISTANCE: " + closestsPointOfWalls.get(wallIndex).getDistance() + "ANGLE: " + scannedAngle);
            if(lastAngle - LAST_POINT_DELTA < scannedAngle && lastAngle + LAST_POINT_DELTA > scannedAngle){
                wallNameToClosestsPoints.get(wallName).add(scannedAngle);
                wallIndex++;
            }
            else{
                wallNameToClosestsPoints.get(wallName).add(scannedAngle);
            }

        }
    }


    public static List<Point> scannedPointsToCoordinates(List<MapPoint> scannedPoints){
        List<Point> points = new LinkedList<Point>();
        for(MapPoint p : scannedPoints){
            double x = p.getDistance() * Math.sin(Math.toRadians(p.getAngle()));
            double y = p.getDistance() * Math.cos(Math.toRadians(p.getAngle()));
            points.add(new Point(x,y));
        }
        return points;
    }

    public static List<List<MapPoint>> extractWalls(List<MapPoint> scannedPoints, List<List<Integer>> allEdges) throws OMGNoEdgesDetectedException {
        if(allEdges == null){
            throw new OMGNoEdgesDetectedException();
        }

        List<List<MapPoint>> listOfWalls = new LinkedList<List<MapPoint>>();
        int beginOfWall = 0;

        for(List<Integer> edge : allEdges){
            listOfWalls.add(scannedPoints.subList(beginOfWall, edge.get(0) - 1));
            beginOfWall=edge.get(edge.size()-1);
        }
        List<Integer> lastEdge = allEdges.get(allEdges.size()-1);
        listOfWalls.add(scannedPoints.subList(lastEdge.get(lastEdge.size()-1), scannedPoints.size()-1));
        return listOfWalls;
    }

    public static class Point{
        public double x;
        public double y;
        public Point(double x, double y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "(X: " + this.x + ", Y: " + this.y + ")\n";
        }
    }

    public static List<MapPoint> getScannedPoints(){
        List<MapPoint> position = new LinkedList<MapPoint>();
        double wallDistance = 10;
        //1. Normally you'd need to fetch it from Hokuyo
        for(int i=-45; i<46; i++ ){
            double angle = i;
            double kont = Math.abs(i);
            double distance = wallDistance/Math.cos(Math.toRadians(45-kont)); // TODO: YAGNI - MapPoint.xposition
            position.add(new MapPoint(distance, angle, 1));
        }
        return position;
    }

    // Returns list of possible edged. IMPORTANT : BEST TO REMOVE NEIGHBOURHOODS TOO.
    public static List<List<Integer>> findEdges(List<MapPoint> scannedPoints){
        // WINDOWS SIZE - ilosc sasiadow z prawej i lewej, dla ktorych liczymy roznice miedzy ich odległosciami
        int WINDOW_SIZE = 20; // srednio 2 skany na stopien - czyli 10 stopni

    List<Integer> possibleEdges = new LinkedList<Integer>();
        int size = scannedPoints.size();

        double[] scanDistances = getScanDistances(scannedPoints);

        // [0,0,0,dist1,dist2,dist3,...]
        List<Double> sumTenNeighbours = initSumOfNeighboursDistances(WINDOW_SIZE);
        Map<Integer, Double> indexToDifference = new LinkedHashMap<Integer, Double>();

        for(int i=WINDOW_SIZE;i<(scannedPoints.size()-WINDOW_SIZE);i++){
            double sumOfLeftNeighbourDifferences = lewo(Arrays.copyOfRange(scanDistances, i - WINDOW_SIZE, i));
            double sumOfRightNeighbourDifferences = prawo(Arrays.copyOfRange(scanDistances, i, i + WINDOW_SIZE));

            sumTenNeighbours.add(sumOfLeftNeighbourDifferences + sumOfRightNeighbourDifferences);
        }


        for (int i=0;i<sumTenNeighbours.size();i++) {
            indexToDifference.put(i,sumTenNeighbours.get(i));
        }

        //TU JEST ZLE !!
        Map<Integer,Double> filteredIndexToDifferenceMap = new LinkedHashMap<Integer, Double>();

        for (Map.Entry<Integer, Double> integerDoubleEntry : indexToDifference.entrySet()) {
            if(integerDoubleEntry.getValue()>50){
                filteredIndexToDifferenceMap.put(integerDoubleEntry.getKey(), integerDoubleEntry.getValue());
            }
        }

        LinkedList<Integer> integers = new LinkedList<Integer>(filteredIndexToDifferenceMap.keySet());
        Collections.sort(integers);

        // Jesli tu odpowiednie density, to mozna usunac ten zakres
        List<List<Integer>> listOfEdges = detectEdgesFromIndexes(integers, scannedPoints);

        return listOfEdges;

    }

    private static List<Double> initSumOfNeighboursDistances(int WINDOW_SIZE) {
        List<Double> sumTenNeighbours = new LinkedList<Double>();

        for(int i=0;i<WINDOW_SIZE;i++){
            sumTenNeighbours.add(0.0);
        }
        return sumTenNeighbours;
    }

    private static double[] getScanDistances(List<MapPoint> scannedPoints) {
        double[] valuez = new double[scannedPoints.size()];
        for (int i=0; i<scannedPoints.size(); i++) {
            valuez[i] = scannedPoints.get(i).getDistance();
        }
        return valuez;
    }

    private static List<List<Integer>> detectEdgesFromIndexes(LinkedList<Integer> integers, List<MapPoint> scannedPoints) {
        int previous = integers.get(0);
        int current;
        int DIFF = 5; // TODO: Configurable - dległośc miedzy indeksami punktów podejrzewanych o bycie krawedzia
        int MIN_EDGE_LEN = 7; // TODO: Configurable - minimalna ilość odczytó∑ do uznania czegoś za krawędź

        boolean isEdgeInProgress = false;
        int noOfPointsInCorner = 0;

        List<List<Integer>> cornerIndexesList = new LinkedList<List<Integer>>();
        List<Integer> potentialCorner = null;

        for (int i=1; i<integers.size(); i++) {
            if(!isEdgeInProgress){
                potentialCorner = new LinkedList<Integer>();
                potentialCorner.add(previous);
                noOfPointsInCorner++;
                isEdgeInProgress = true;
            }

            current = integers.get(i);

            if ((current - previous) < DIFF) {
                potentialCorner.add(current);

                noOfPointsInCorner++;
            }
            else{
                if(noOfPointsInCorner >= MIN_EDGE_LEN){
                    cornerIndexesList.add(potentialCorner);
                }
                noOfPointsInCorner = 0;
                isEdgeInProgress = false;
                potentialCorner = null;
            }
            previous = current;
            if(i == (integers.size()-1) && potentialCorner != null){
                cornerIndexesList.add(potentialCorner);
            }
        }
        System.out.println("WYKRYTE ROGI" + cornerIndexesList);

    return cornerIndexesList;
    }
    public static void printDistancesToWall(List<MapPoint> mapPoints, List<List<Integer>> edges) {
        try {
            List<List<MapPoint>> walls = Positioner.extractWalls(mapPoints, edges);
            int i = 0;
            for (List<MapPoint> wall : walls) {

                SimpleRegression wallRegression = getWallRegression(wall);

                double a = wallRegression.getSlope();
                double b = wallRegression.getIntercept();

                double A = -a;
                double B = 1;
                double C = -b;

                double distance = Math.abs(C)/Math.sqrt(A*A+B*B);
                System.out.println("y = " + a +"x + "+ b);
                System.out.println("DISTANCE " + distance);

            }
            System.out.println("-------------");


        } catch (OMGNoEdgesDetectedException e) {
            e.printStackTrace();
        }
    }

    public static SimpleRegression getWallRegression(List<MapPoint> wall) {
        SimpleRegression simpleRegression = new SimpleRegression(true);

        for (MapPoint mapPoint : wall) {
            double x = mapPoint.getDistance() * Math.sin(Math.toRadians(mapPoint.getAngle()));
            double y = mapPoint.getDistance() * Math.cos(Math.toRadians(mapPoint.getAngle()));

            simpleRegression.addData(x, y);
        }

        return simpleRegression;
//        double a = simpleRegression.getSlope();
//        double b = simpleRegression.getIntercept();
    }
    public static double lewo(double[] array){
        double sum = 0.0;
        for(int i=1;i<array.length;i++){
            sum+=array[i]-array[i-1];
        }
        return sum;
    }
    public static double prawo(double[] array){
        double sum = 0.0;
        for(int i=1;i<array.length;i++){
            sum+=array[i-1]-array[i];
        }
        return sum;
    }

}
