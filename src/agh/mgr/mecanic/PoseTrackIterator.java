package agh.mgr.mecanic;

public class PoseTrackIterator {

    private PoseTrack poseTrack;
    private int currentElement;
    private int poseTrackSize;
    private boolean executeContinuously;

    public PoseTrackIterator(PoseTrack poseTrack) {
        this.poseTrack = poseTrack;
        this.currentElement = 0;
        this.poseTrackSize = poseTrack.getPoseTrack().size();
        this.executeContinuously = false;
    }

    public Pose getNext(){
        if(currentElement < poseTrackSize){
            return this.poseTrack.getPoseTrack().get(currentElement);
        }
        else if (this.executeContinuously){
            currentElement -= poseTrackSize;
            return this.poseTrack.getPoseTrack().get(currentElement);
        }
        
        return this.poseTrack.getPoseTrack().get(0);


    }
}
