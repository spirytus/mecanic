package agh.mgr.mecanic;

import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.*;
// TODO: REVIEW

// TODO's:
// 1. Implement linear regression.
// From points that belongs to wall -> y=ax +b.
// Next measure distance to this wall.
// This will give you better accuracy (hopefully)

public class Positioner {
    public static final Integer LAST_POINT_DELTA = 5; // Difference from last measured point of wall

    public  static void execute(List<MapPoint> scannedPoints /*plik do logowania*/){
        //List<MapPoint> scannedPoints = getScannedPoints();
        System.out.println(scannedPoints);
        List<Integer> edges = findEdges(scannedPoints);
        System.out.println(edges);
        List<List<MapPoint>> listOfWalls = extractWalls(scannedPoints, edges);

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
            System.out.println("Minimal distance");
            System.out.println(wall.get(0));
            closestsPointOfWalls.add(wall.get(0));
        }
        addClosestPointsToWalls(closestsPointOfWalls, wallNameToClosestsPoints);
        System.out.println(listOfWalls);

        System.out.println(scannedPointsToCoordinates(scannedPoints));
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

    public static List<List<MapPoint>> extractWalls(List<MapPoint> scannedPoints, List<Integer> edgeIndices){
        List<List<MapPoint>> listOfWalls = new LinkedList<List<MapPoint>>();
        int beginOfWall = 0;
        for(int edgeIndex : edgeIndices){
            listOfWalls.add(scannedPoints.subList(beginOfWall + 1, edgeIndex - 1));
            beginOfWall=edgeIndex;
        }
        listOfWalls.add(scannedPoints.subList(beginOfWall+1, scannedPoints.size()-1));
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
    public static List<Integer> findEdges(List<MapPoint> scannedPoints){
        List<Integer> possibleEdges = new LinkedList<Integer>();
        int size = scannedPoints.size();
        boolean isIncreasing = scannedPoints.get(0).getDistance() > scannedPoints.get(1).getDistance();

        for(int i=1; i<size-1; i++){
            if(isIncreasing && scannedPoints.get(i).getDistance() > scannedPoints.get(i+1).getDistance()){
                possibleEdges.add(i);
            }
            isIncreasing = scannedPoints.get(i).getDistance() < scannedPoints.get(i+1).getDistance();
        }
        return possibleEdges;

    }
}
