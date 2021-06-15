package test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class runner implements Runnable {

    @Override
    public void run() {

    }


    public static void main(String[] args) {
        File area = new File("Resource/Project3/input/area.txt");
        File position = new File("Resource/Project3/input/positions.txt");

        helper Area = new helper(area.getAbsoluteFile());
        helper Position = new helper(position.getAbsoluteFile());

        List<String> areaInput = Area.changeToString();
        List<String> positionInput = Position.changeToString();

//        Set<String> nameShipList = new HashSet<>();
//
//        for (int i = 0; i < positionInput.size(); i++) {
//            String name = Position.getShipName(positionInput.get(i));
//            nameShipList.add(name);
//        }
//
//        System.out.println(nameShipList);
//
//        List<List<String>>[] longPositionListArray = new ArrayList[nameShipList.size()];
//        List<List<String>>[] latPositionListArray = new ArrayList[nameShipList.size()];

//        for (int i = 0; i < nameShipList.size(); i++) {
//            if (Position.checkShipname(nameShipList.))
//        }

        List<List<String>> longAreaList = new ArrayList<>();
        List<List<String>> latAreaList = new ArrayList<>();

        for (int i = 0; i < areaInput.size(); i++) {
            String longAndLatArea = Area.getLongandLatArea(areaInput.get(i));

            List<String> longArea = Area.getLongArea(longAndLatArea);
            longAreaList.add(longArea);

            List<String> latArea = Area.getLatArea(longAndLatArea);
            latAreaList.add(latArea);
        }

        List<String> longPositionList = new ArrayList<>();
        List<String> latPositionList = new ArrayList<>();
        List<String> timePositionList = Position.getDateString(positionInput);

        // loop to get list of longtitude and list of latitude for each ship in position input
        for (int i = 0; i < positionInput.size(); i++) {
            String longAndLatPosition = Position.getLongandLatPosition(positionInput.get(i));
            String getLongPosition = Position.getLongPosition(longAndLatPosition);
            longPositionList.add(getLongPosition);
            String getLatPosition = Position.getLatPosition(longAndLatPosition);
            latPositionList.add(getLatPosition);
        }

        for (int i = 0; i < longAreaList.size(); i++) {
            for (int j = 0; j < positionInput.size(); j++) {
                int currentLong = Integer.valueOf(longPositionList.get(j));
                int longLeft = Integer.valueOf(longAreaList.get(i).get(0));
                int longRight = Integer.valueOf(longAreaList.get(i).get(1));
                boolean checkLong = Position.checkLong(currentLong, longLeft, longRight);

                int currentLat = Integer.valueOf(latPositionList.get(j));
                int latTop = Integer.valueOf(latAreaList.get(i).get(0));
                int latDown = Integer.valueOf(latAreaList.get(i).get(1));
                boolean checkLat = Position.checkLat(currentLat, latTop, latDown);

                if (checkLong == true && checkLat == true) {
                    System.out.println("Ship " + (j + 1) + " is in the area " + (i + 1));
                }
                else {
                    System.out.println("Ship " + (j + 1) + " is not in the area " + (i + 1));
                }
            }

        }

    }
}
