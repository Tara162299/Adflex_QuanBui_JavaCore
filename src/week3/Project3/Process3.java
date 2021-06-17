package week3.Project3;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Process3 {
    File file;

    public Process3(File file) {
        this.file = file;
    }

    public List<String> changeToString() {
        List<String> text = new ArrayList<>();
        String line;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader((this.file).getAbsolutePath()))) {
                while ((line = br.readLine()) != null) {
                    text.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("There is no such file");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    public String getDateString(String message) {
        String eachMessageLine;

        String dateRegex = "(\\d\\d\\d\\d)\\/([0]{0,1}[1-9]|1[012])\\/([1-9]|([012][0-9])|(3[01])) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS");
        String stringDate = null;

        Matcher matcherDate = Pattern.compile(dateRegex).matcher(message);

        if (matcherDate.find()) {
            try {
                Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS").parse(matcherDate.group());
                stringDate = formatDate.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //tempDateList.add(stringDate);

        }
        return stringDate;
    }

    public String getShipName(String s) {
        String nameRegex = "\\d{2}\\d{5}\\|";
        String temp = null;
        StringBuilder result = new StringBuilder();

        Pattern pattern = Pattern.compile(nameRegex);
        Matcher match = pattern.matcher(s);

        if (match.find()) {
            temp = match.group();
        }

        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != '|') {
                result.append(temp.charAt(i));
            }
        }
        return result.toString();
    }

    public boolean checkShipname(String nameShip, String s) {
        boolean statement = false;
        StringBuilder result = new StringBuilder();
        String positionRegex = "\\d{2}\\d{5}\\|";
        Pattern pattern = Pattern.compile(positionRegex);
        Matcher match = pattern.matcher(s);

        String temp = null;

        if (match.find()) {
            temp = match.group();
        }

        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != '|') {
                result.append(temp.charAt(i));
            }
        }

        if ((result.toString()).equals(nameShip)) {
            statement = true;
        }

        return statement;
    }

    public String getLongandLatPosition(String s) {
        String result = null;
        String positionRegex = "\\|\\d{3}\\|\\d{3}";
        Pattern pattern = Pattern.compile(positionRegex);
        Matcher match = pattern.matcher(s);

        if (match.find()) {
            result = match.group();
        }
        return result;
    }

    public String getLongPosition(String LongandLat) {
        String LongRegex = "\\|\\d{3}\\|";
        String temp = null;
        StringBuilder result = new StringBuilder();

        Pattern pattern = Pattern.compile(LongRegex);
        Matcher match = pattern.matcher(LongandLat);

        if (match.find()) {
            temp = match.group();
        }

        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != '|') {
                result.append(temp.charAt(i));
            }
        }
        return result.toString();
    }

    public String getLatPosition(String LongandLat) {
        StringBuilder result = new StringBuilder();

        for (int i = 5; i <= 7; i++) {
            result.append(LongandLat.charAt(i));
        }
        return result.toString();
    }

    public String getLongandLatArea(String s) {
        String result = null;
        String positionRegex = "\\|\\d{3}\\|\\d{3}\\|\\d{3}\\|\\d{3}";
        Pattern pattern = Pattern.compile(positionRegex);
        Matcher match = pattern.matcher(s);

        if (match.find()) {
            result = match.group();
        }
        return result;
    }

    public List<String> getLongArea(String LongandLatArea) {
        String LongRegex = "\\|\\d{3}\\|\\d{3}";
        String temp = null;
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(LongRegex);
        Matcher match = pattern.matcher(LongandLatArea);

        if (match.find()) {
            temp = match.group();
        }

        StringBuilder longLeft = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            longLeft.append(temp.charAt(i));
        }

        StringBuilder longRight = new StringBuilder();
        for (int i = 5; i <= 7; i++) {
            longRight.append(temp.charAt(i));
        }

        result.add(longLeft.toString());
        result.add(longRight.toString());

        return result;
    }

    public List<String> getLatArea(String LongandLatArea) {
        String LongRegex = "\\|\\d{3}\\|\\d{3}\\|\\d{3}\\|\\d{3}";
        String temp = null;
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(LongRegex);
        Matcher match = pattern.matcher(LongandLatArea);

        if (match.find()) {
            temp = match.group();
        }

        StringBuilder latTop = new StringBuilder();
        for (int i = 9; i <= 11; i++) {
            latTop.append(temp.charAt(i));
        }

        StringBuilder latBottom = new StringBuilder();
        for (int i = 13; i <= 15; i++) {
            latBottom.append(temp.charAt(i));
        }

        result.add(latTop.toString());
        result.add(latBottom.toString());

        return result;
    }

    public boolean checkLong(int longPosition, int longLeftArea, int longRightArea) {
        boolean statement = false;

        if (longLeftArea <= longPosition && longPosition <= longRightArea) {
            statement = true;
        }

        return statement;
    }

    public boolean checkLat(int latPosition, int latUpArea, int latDownArea) {
        boolean statement = false;

        if (latDownArea <= latPosition && latPosition <= latUpArea) {
            statement = true;
        }

        return statement;
    }

    public void process () {
        long startTime = System.nanoTime();

        File area = new File("Resource/Project3/input/area.txt");
        File position = new File("Resource/Project3/input/positions.txt");

        Process3 Area = new Process3(area.getAbsoluteFile());
        Process3 Position = new Process3(position.getAbsoluteFile());

        List<String> areaInput = Area.changeToString();
        List<String> positionInput = Position.changeToString();


        // get longitude and latitude in the Area file
        List<List<String>> longAreaList = new ArrayList<>();
        List<List<String>> latAreaList = new ArrayList<>();

        for (String s : areaInput) {
            String longAndLatArea = Area.getLongandLatArea(s);

            List<String> longArea = Area.getLongArea(longAndLatArea);
            longAreaList.add(longArea);

            List<String> latArea = Area.getLatArea(longAndLatArea);
            latAreaList.add(latArea);
        }

        // return a list of all unique ship names
        Set<String> nameShipSet = new HashSet<>();

        for (String value : positionInput) {
            String name = Position.getShipName(value);
            nameShipSet.add(name);
        }

        // list to contain all input list for each ship
        List<List<String>> shipInputList = new ArrayList<>();

        // return longitude, latitude, time and boolean checking current position
        List<List<String>> longPositionList = new ArrayList<>();
        List<List<String>> latPositionList = new ArrayList<>();
        List<List<String>> timeList = new ArrayList<>();
        List<List<List<Boolean>>> checkInOrOut = new ArrayList<>();

        // initiate each list above according to the number of ship
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
            checkInOrOut.add(check);
        }

        // return the data of longitude, latitude and time for all ship
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


        File file = new File("Resource/Project3/output/alert.txt");
        FileWriter fw;
        try {
            fw = new FileWriter(file.getAbsolutePath());
            int temp = 0;
            for (String shipName : nameShipSet) {

                // return the boolean checking for each ship
                for (int i = 0; i < (longPositionList.get(0)).size(); i++) {
                    boolean checkStatus;                    // false = in, true = out
                    int currentLong = Integer.parseInt((longPositionList.get(temp)).get(i));
                    int currentLat = Integer.parseInt((latPositionList.get(temp)).get(i));

                    for (int j = 0; j < longAreaList.size(); j++) {
                        int longLeft = Integer.parseInt(longAreaList.get(j).get(0));
                        int longRight = Integer.parseInt(longAreaList.get(j).get(1));
                        boolean checkLong = Position.checkLong(currentLong, longLeft, longRight);


                        int latTop = Integer.parseInt(latAreaList.get(j).get(0));
                        int latDown = Integer.parseInt(latAreaList.get(j).get(1));
                        boolean checkLat = Position.checkLat(currentLat, latTop, latDown);


                        if (!checkLong || !checkLat) {
                            checkStatus = false;
                        } else {
                            checkStatus = true;
                        }

                        checkInOrOut.get(temp).get(j).add(checkStatus);
                    }
                }

                // check the boolean and printout the result
                for (int i = 1; i < (longPositionList.get(0)).size(); i++) {
                    int currentLong = Integer.parseInt((longPositionList.get(temp)).get(i));
                    int currentLat = Integer.parseInt((latPositionList.get(temp)).get(i));
                    String currentTime = (timeList.get(temp)).get(i);

                    for (int j = 0; j < longAreaList.size(); j++) {
                        if (!checkInOrOut.get(temp).get(j).get(i - 1) && checkInOrOut.get(temp).get(j).get(i)) {
                            fw.write(shipName + "|Canh bao xam nhap vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime + "\n");
                        } else if (checkInOrOut.get(temp).get(j).get(i - 1) && !checkInOrOut.get(temp).get(j).get(i)) {
                            fw.write(shipName + "|Canh bao di ra khoi vung|Vung " + (j + 1) + "| " + currentLong + "| " + currentLat + "| " + currentTime + "\n");
                        }
                    }
                }
                temp += 1;
            }
            fw.flush();
            fw.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("The program took " + (totalTime / Math.pow(10, 9) + " second to run"));
    }
}
