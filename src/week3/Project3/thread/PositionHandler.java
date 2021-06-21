package week3.Project3.thread;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionHandler {
    String positionPath = "Resource/Project3/input/positions.txt";

    File positionFile = new File(positionPath);
    FileHandler dataExtraction = new FileHandler();

    List<String> postitionInput = dataExtraction.fileToString(positionFile);

    public Set<String> diffShipName() {
        Set<String> nameShipSet = new HashSet<>();
        String nameRegex = "\\d{7}";
        String temp = null;

        Pattern pattern = Pattern.compile(nameRegex);

        for (String eachPositionLine : postitionInput) {
            Matcher match = pattern.matcher(eachPositionLine);

            if (match.find()) {
                temp = match.group();
            }

            nameShipSet.add(temp);
        }
        return nameShipSet;
    }

    public String getShipLongitude (String s) {
        String LongRegex = "\\|\\d{3}\\|";
        String temp = null;
        StringBuilder result = new StringBuilder();

        Pattern pattern = Pattern.compile(LongRegex);

        Matcher match = pattern.matcher(s);

        if (match.find()) {
            temp = match.group();
        }

        assert temp != null;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) != '|') {
                result.append(temp.charAt(i));
            }
        }
        return result.toString();
    }

    public String getShipLatitude (String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 12; i <= 14; i++) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    public String getShipDate(String s) {
        String dateRegex = "(\\d\\d\\d\\d)/([0]{0,1}[1-9]|1[012])/([1-9]|([012][0-9])|(3[01])) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String stringDate = null;

        Matcher matcherDate = Pattern.compile(dateRegex).matcher(s);

        if (matcherDate.find()) {
            try {
                Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(matcherDate.group());
                stringDate = formatDate.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return stringDate;
    }

    public Map<String, List<String>> shipMap() {
        Map<String, List<String>> shipMap = new LinkedHashMap<>();

        String nameRegex = "\\d{2}\\d{5}";
        String temp = null;

        Pattern pattern = Pattern.compile(nameRegex);

        for (String name : diffShipName()) {
            List<String> tempString = new ArrayList<>();
            for (String eachPositionLine : postitionInput) {
                Matcher match = pattern.matcher(eachPositionLine);

                if (match.find()) {
                    temp = match.group();
                }

                assert temp != null;
                if (temp.equals(name)) {
                    tempString.add(eachPositionLine);
                }
            }
            shipMap.put(name, tempString);
        }
        return shipMap;
    }
}