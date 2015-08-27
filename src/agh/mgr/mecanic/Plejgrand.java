package agh.mgr.mecanic;

class LolStateObject {
    public String lolValue;
    public LolStateObject(String value){
        lolValue=value;
    }
}

public class Plejgrand {


    public static LolStateObject VAR = new LolStateObject("INITIAL");

    public static void main(String args[]){
        Thread tWriter = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started T Writersss");
                int i = 0;
                while(true){
                    try {
                        Thread.sleep(333);
                        Plejgrand.VAR = new LolStateObject("VAR " + Integer.toString(i++));
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
                }
            }
        });
        Thread tReader = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started T Reader");
                while(true){
                    try {
                        Thread.sleep(1000);
                        System.out.println(Plejgrand.VAR.lolValue);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        tWriter.start();
        tReader.start();
    }

}
