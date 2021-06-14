package week_3;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class helperMethods_3 {
    File file;

    public helperMethods_3(File file) {
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

    public List<String> getDateString(List<String> message) {
        List<String> tempDateList = new ArrayList<>();
        String eachMessageLine;

        String dateRegex = "(\\d\\d\\d\\d)\\/([0]{0,1}[1-9]|1[012])\\/([1-9]|([012][0-9])|(3[01])) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS");
        String stringDate = null;

        for (int i = 0; i < message.size(); i++) {
            eachMessageLine = message.get(i);
            Matcher matcherDate = Pattern.compile(dateRegex).matcher(eachMessageLine);

            if (matcherDate.find()) {
                try {
                    Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS").parse(matcherDate.group());
                    stringDate = formatDate.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tempDateList.add(stringDate);
            }
        }
        return tempDateList;
    }

    public String getLongandLat(String s) {
        String result = null;
        String positionRegex = "\\|\\d{3}\\|\\d{3}";
        Pattern pattern = Pattern.compile(positionRegex);
        Matcher match = pattern.matcher(s);

        if (match.find()) {
            result = match.group();
        }
        return result;
    }

    public String getLong(String LongandLat) {
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

    public String getLat(String LongandLat) {
        StringBuilder result = new StringBuilder();

        for (int i = 5; i <= 7; i++) {
            result.append(LongandLat.charAt(i));
        }
        return result.toString();
    }


    public void checkCurrentPosition() {

    }

    public void checkEnterArea() {

    }

    public void checkLeaveArea() {

    }
}
