package agh.mgr.mecanic.tools;

import agh.mgr.mecanic.data.SerializableScanHistory;

public class Sandbox {
    public static void main(String []args){
        System.out.println(SerializableScanHistory.loadScans("/Users/maciejmarczynski/SKAAN.out"));
    }
}