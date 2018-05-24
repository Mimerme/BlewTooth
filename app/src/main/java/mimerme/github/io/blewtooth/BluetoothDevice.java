package mimerme.github.io.blewtooth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BluetoothDevice {
    String name;
    short rssi;
    String lastUpdate;

    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String currentDateandTime = sdf.format(new Date());
        return lastUpdate;
    }

    public BluetoothDevice(String name, short rssi){
        this.name = name;
        this.rssi = rssi;
        this.lastUpdate = getTime();
    }

    public void setSignalStrength(short rssi){
        this.rssi = rssi;
        this.lastUpdate = getTime();
    }

    public short getSignalStrength(){
        return this.rssi;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return name + " : " + rssi;
    }
}
