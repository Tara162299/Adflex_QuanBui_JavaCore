package week3.Project3.thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Execution {
    String nameShip;

    public Execution(String nameShip) {
        this.nameShip = nameShip;
    }

    public boolean checkLong(int longPosition, int longLeftArea, int longRightArea) {

        return longLeftArea <= longPosition && longPosition <= longRightArea;
    }

    public boolean checkLat(int latPosition, int latUpArea, int latDownArea) {

        return latDownArea <= latPosition && latPosition <= latUpArea;
    }

    public void process() {
        long startTime = System.nanoTime();

        PositionHandler testPosition = new PositionHandler();
        Execution execution = new Execution(nameShip);
        AreaHandler testArea = new AreaHandler();

        Ship newShip = new Ship(nameShip);

        List<String> eachShipList = testPosition.shipMap().get(nameShip);
        List<String> eachAreaList = testArea.areaInput;

        List<List<Boolean>> statementList = new ArrayList<>();

        for (int i = 0; i < testArea.areaInput.size(); i++) {
            List<Boolean> check = new ArrayList<>();
            statementList.add(check);
        }

        for (String s : eachShipList) {
            newShip.setLongitude(Integer.parseInt((testPosition.getShipLongitude(s))));
            newShip.setLatitude(Integer.parseInt((testPosition.getShipLatitude(s))));
            newShip.setCurrentTime((testPosition.getShipDate(s)));

            int currentLong = newShip.getLongitude();
            int currentLat = newShip.getLatitude();
            boolean statement;

            for (int k = 0; k < eachAreaList.size(); k++) {
                int longRight = Integer.parseInt(testArea.getLongArea(eachAreaList.get(k)).get(0));
                int longLeft = Integer.parseInt(testArea.getLongArea(eachAreaList.get(k)).get(1));
                int latBottom = Integer.parseInt(testArea.getLatArea(eachAreaList.get(k)).get(1));
                int latTop = Integer.parseInt(testArea.getLatArea(eachAreaList.get(k)).get(0));

                boolean checkLong = execution.checkLong(currentLong, longRight, longLeft);
                boolean checkLat = execution.checkLat(currentLat, latTop, latBottom);

                statement = checkLong && checkLat;
                statementList.get(k).add(statement);
            }
        }

        File file = new File("Resource/Project3/output/alert.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(file.getAbsolutePath());

            for (int i = 1; i < eachShipList.size(); i++) {
                newShip.setLongitude(Integer.parseInt((testPosition.getShipLongitude(eachShipList.get(i)))));
                newShip.setLatitude(Integer.parseInt((testPosition.getShipLatitude(eachShipList.get(i)))));
                newShip.setCurrentTime((testPosition.getShipDate(eachShipList.get(i))));

                int currentLong = newShip.getLongitude();
                int currentLat = newShip.getLatitude();
                String currentTime = newShip.getCurrentTime();

                for (int j = 0; j < testArea.areaInput.size(); j++) {
                    if (!statementList.get(j).get(i - 1) && statementList.get(j).get(i)) {
                        fw.write(nameShip + "|Canh bao xam nhap vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime + "\n");
                    } else if (statementList.get(j).get(i - 1) && !statementList.get(j).get(i)) {
                        fw.write(nameShip + "|Canh bao di ra khoi vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime + "\n");
                    }
                }
            }
            fw.flush();
            fw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("The program took " + (totalTime / Math.pow(10, 9) + " second to run"));
    }
}
