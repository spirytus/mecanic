package agh.mgr.mecanic;

import agh.mgr.mecanic.data.tracks.PaperTrack;

public class MainExecutor {
    public static void main(String args[]){

        TrackExecutor.execute(new PaperTrack(), 100);

    }
}