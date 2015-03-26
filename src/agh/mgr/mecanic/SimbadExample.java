package agh.mgr.mecanic;

import simbad.gui.Simbad;

import simbad.sim.*;
import javax.vecmath.Vector3d;

/**
 * Created by maciejmarczynski on 01.03.15.
 */
public class SimbadExample {
    public static void main(String []args){
        boolean backgroundMode = true;
        Simbad frame = new Simbad(new MyEnv(), backgroundMode);

    }
}


