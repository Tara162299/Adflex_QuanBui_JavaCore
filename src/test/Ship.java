package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ship implements Runnable {
    String name;
    int currentLong;
    int currentLat;
    String currentTime;
    List<List<Boolean>> checkPos;

    public Ship(String name) {
        this.name = name;
    }

    public Ship (String name, List<List<Boolean>> checkPos, String currentTime, int currentLong, int currentLat) {
        this.checkPos = checkPos;
        this.name = name;
        this.currentTime = currentTime;
        this.currentLat = currentLat;
        this.currentLong = currentLong;
    }

    public Ship(int currentLong, int currentLat, String currentTime) {
        this.currentLat = currentLat;
        this.currentLong = currentLong;
        this.currentTime = currentTime;
    }


    @Override
    public void run() {
//        for (int i = 1; i < (longPositionList.get(0)).size(); i++) {
//            int currentLong = Integer.parseInt((longPositionList.get(temp)).get(i));
//            int currentLat = Integer.parseInt((latPositionList.get(temp)).get(i));
//            String currentTime = (timeList.get(temp)).get(i);
//
//            newShip = new Ship(currentLong, currentLat, currentTime);
//
//
//            for (int j = 0; j < longAreaList.size(); j++) {
//                if (checkInOrOut.get(temp).get(j).get(i - 1) && !checkInOrOut.get(temp).get(j).get(i)) {
//                    fw.write(shipName + "|Canh bao xam nhap vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime + "\n");
//                } else if (!checkInOrOut.get(temp).get(j).get(i - 1) && checkInOrOut.get(temp).get(j).get(i)) {
//                    fw.write(shipName + "|Canh bao di ra khoi vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime + "\n");
//                }
//            }
//        }
    }
}
