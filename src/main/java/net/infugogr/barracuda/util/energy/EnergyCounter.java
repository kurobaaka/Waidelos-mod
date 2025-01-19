package net.infugogr.barracuda.util.energy;

public class EnergyCounter {

    public static String CounterWh(int i) {
        float i2 = i;
        String text = "Wh";
        if(i >= 1000){
            i2 =  (float)((int) i/100)/10;
            text = "kWh";
        }
        if(i >= 1000000){
            i2 = (float)((int) i/100000)/10;
            text = "mWh";
        }
        return i2 + text;
    }

    public static String CounterWh(long i) {
        float
                i2 = i;
        String text = "Wh";
        if(i >= 1000){
            i2 = (float)((int) i/100)/10;
            text = "kWh";
        }
        if(i >= 1000000){
            i2 = (float)((int) i/100000)/10;
            text = "mWh";
        }
        return i2 + text;
    }

    public static String CounterAh(int i) {
        float i2 = i;
        String text = "Ah";
        if(i >= 1000){
            i2 =  (float)((int) i/100)/10;
            text = "kAh";
        }
        if(i >= 1000000){
            i2 = (float)((int) i/100000)/10;
            text = "mAh";
        }
        return i2 + text;
    }

    public static String CounterAh(long i) {
        float i2 = i;
        String text = "Ah";
        if(i >= 1000){
            i2 =  (float)((int) i/100)/10;
            text = "kAh";
        }
        if(i >= 1000000){
            i2 = (float)((int) i/100000)/10;
            text = "mAh";
        }
        return i2 + text;
    }

    public static String CounterBu(long i) {
        float i2 = (float) i/4;
        String text = "Bu";
        if(i >= 1000){
            i2 =  (float)((int) i/400)/10;
            text = "kBu";
        }
        if(i >= 1000000){
            i2 = (float)((int) i/400000)/10;
            text = "mBu";
        }
        return i2 + text;
    }
}
