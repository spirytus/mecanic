package agh.mgr.mecanic;

import java.util.LinkedList;
import java.util.List;

public class PoseTrack {
    private double gradient; // czyli nachylenie z jakim chcemy pokonywac trase

    public PoseTrack(double gradient) {
        this.gradient = gradient;
        this.poseTrack = new LinkedList<Pose>();
    }

    public List<Pose> getPoseTrack() {
        return poseTrack;
    }

    private List<Pose> poseTrack;

    public void addPose(Pose pose){
        poseTrack.add(pose);
    }

}

