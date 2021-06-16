package test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Runner_Test implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        File area = new File("Resource/Project3/input/area.txt");
        File position = new File("Resource/Project3/input/positions.txt");

        helper_Test Area = new helper_Test(area.getAbsoluteFile());
        helper_Test Position = new helper_Test(position.getAbsoluteFile());

        List<String> areaInput = Area.changeToString();
        List<String> positionInput = Position.changeToString();


        List<List<String>> longAreaList = new ArrayList<>();
        List<List<String>> latAreaList = new ArrayList<>();

        for (String s : areaInput) {
            String longAndLatArea = Area.getLongandLatArea(s);

            List<String> longArea = Area.getLongArea(longAndLatArea);
            longAreaList.add(longArea);

            List<String> latArea = Area.getLatArea(longAndLatArea);
            latAreaList.add(latArea);
        }

        Set<String> nameShipSet = new HashSet<>();

        for (String value : positionInput) {
            String name = Position.getShipName(value);
            nameShipSet.add(name);
        }

        List<List<String>> shipInputList = new ArrayList<>();
        List<List<String>> longPositionList = new ArrayList<>();
        List<List<String>> latPositionList = new ArrayList<>();
        List<List<String>> timeList = new ArrayList<>();
        List<List<List<Boolean>>> checkInorOut = new ArrayList<>();

        //assign size for the above list
        for (int i = 0; i < nameShipSet.size(); i++) {
            List<String> tempString = new ArrayList<>();
            List<String> longPOS = new ArrayList<>();
            List<String> latPOS = new ArrayList<>();
            List<String> time = new ArrayList<>();
            shipInputList.add(tempString);
            longPositionList.add(longPOS);
            latPositionList.add(latPOS);
            timeList.add(time);
        }

        for (int i = 0; i < nameShipSet.size(); i++) {
            List<List<Boolean>> check = new ArrayList<>();
            for (int j = 0; j < areaInput.size(); j++) {
                List<Boolean> check_Small = new ArrayList<>();
                check.add(check_Small);
            }
            checkInorOut.add(check);
        }





























        int indexOfShip = 0;
        for (String shipName : nameShipSet) {
            for (String s : positionInput) {
                boolean checkingName = Position.checkShipname(shipName, s);
                if (checkingName) {
                    shipInputList.get(indexOfShip).add(s);

                    String longAndLatPosition = Position.getLongandLatPosition(s);

                    String getLongPosition = Position.getLongPosition(longAndLatPosition);
                    longPositionList.get(indexOfShip).add(getLongPosition);

                    String getLatPosition = Position.getLatPosition(longAndLatPosition);
                    latPositionList.get(indexOfShip).add(getLatPosition);

                    String timePosition = Position.getDateString(s);
                    timeList.get(indexOfShip).add(timePosition);
                }
            }
            indexOfShip += 1;
        }

        int temp = 0;
        for (String shipName : nameShipSet) {
            for (int i = 0; i < (longPositionList.get(0)).size(); i++) {
                boolean checkStatus;                    // false = in, true = out
                int currentLong = Integer.parseInt((longPositionList.get(temp)).get(i));
                int currentLat = Integer.parseInt((latPositionList.get(temp)).get(i));
                String currentTime = (timeList.get(temp)).get(i);

                for (int j = 0; j < longAreaList.size(); j++) {
                    int longLeft = Integer.parseInt(longAreaList.get(j).get(0));
                    int longRight = Integer.parseInt(longAreaList.get(j).get(1));
                    boolean checkLong = Position.checkLong(currentLong, longLeft, longRight);


                    int latTop = Integer.parseInt(latAreaList.get(j).get(0));
                    int latDown = Integer.parseInt(latAreaList.get(j).get(1));
                    boolean checkLat = Position.checkLat(currentLat, latTop, latDown);


                    if (checkLong == true && checkLat == true) {
                    //    System.out.println(shipName + "|Canh bao xam nhap vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime);
                        checkStatus = false;
                    } else {
                    //    System.out.println(shipName + "|Canh bao di ra khoi vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime);
                        checkStatus = true;
                    }

                    checkInorOut.get(temp).get(j).add(checkStatus);
                }
            }
            temp += 1;
        }

        int temp2 = 0;
        for (String shipName : nameShipSet) {
            for (int i = 1; i < (longPositionList.get(0)).size(); i++) {
                int currentLong = Integer.parseInt((longPositionList.get(temp2)).get(i));
                int currentLat = Integer.parseInt((latPositionList.get(temp2)).get(i));
                String currentTime = (timeList.get(temp2)).get(i);

                for (int j = 0; j < longAreaList.size(); j++) {
                    if (checkInorOut.get(temp2).get(j).get(i - 1) == true && checkInorOut.get(temp2).get(j).get(i) == false) {
                        System.out.println(shipName + "|Canh bao xam nhap vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime);
                    } else if (checkInorOut.get(temp2).get(j).get(i - 1) == false && checkInorOut.get(temp2).get(j).get(i) == true) {
                        System.out.println(shipName + "|Canh bao di ra khoi vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime);
                    }
                }
            }
            temp2 += 1;
        }
    }
}

