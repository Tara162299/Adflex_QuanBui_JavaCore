package test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class helper_Test {
    File file;

    public helper_Test(File file) {
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
}
