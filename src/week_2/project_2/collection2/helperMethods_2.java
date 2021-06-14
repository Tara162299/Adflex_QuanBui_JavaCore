package week_2.project_2.collection2;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class helperMethods_2 {
    File file;

    public helperMethods_2(File file) {
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


    public String insertString(String originalString, String stringToBeInserted) {
        StringBuilder newString = new StringBuilder();
        newString.append(originalString + "|" + stringToBeInserted);

        String result = newString.toString();
        return result;
    }

    // String list of dates that in desired format in string
    public List<String> getDateStringforName(List<String> message) {
        List<String> tempDateList = new ArrayList<>();
        String eachMessageLine;

        String dateRegex = "(\\d\\d\\d\\d)\\/([0]{0,1}[1-9]|1[012])\\/([1-9]|([012][0-9])|(3[01]))";

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
        String stringDate = null;

        for (int i = 0; i < message.size(); i++) {
            eachMessageLine = message.get(i);
            Matcher matcherDate = Pattern.compile(dateRegex).matcher(eachMessageLine);

            if (matcherDate.find()) {
                try {
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(matcherDate.group());
                    stringDate = formatDate.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tempDateList.add(stringDate);
            }
        }
        return tempDateList;
    }

    public List<String> getDateStringforContent(List<String> message) {
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

    public StringBuilder removeCharacter(String string, char c) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != c) {
                result.append(string.charAt(i));
            }

        }
        return result;
    }

    public Set<String> getDiffDateString(List<List<String>> dateStringList) {
        Set<String> diffDateString = new LinkedHashSet<>();

        for (int i = 0; i < dateStringList.size(); i++) {
            for (String string : dateStringList.get(i)) {
                diffDateString.add(string);
            }
        }
        return diffDateString;
    }

    public String getPhone(File file) {
        String name = file.getName();
        StringBuilder nameFile = new StringBuilder();

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != '.' && name.charAt(i) != 't' && name.charAt(i) != 'x' && name.charAt(i) != 't') {
                nameFile.append(name.charAt(i));
            }
        }

        return nameFile.toString();
    }


//    public List<String> dateToString(List<Date> dateList) {
//        List<String> dateToString = new ArrayList<>();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS");
//
//        for (Date date : dateList) {
//            String string = dateFormat.format(date);
//            dateToString.add(string);
//        }
//        return dateToString;
//    }


//    public List<Date> getOriginalDateList(List<String> message) {
//        List<Date> dateList = new ArrayList<>();
//        String eachMessageLine;
//
//        String dateRegex = "(\\d\\d\\d\\d)\\/([0]{0,1}[1-9]|1[012])\\/([1-9]|([012][0-9])|(3[01])) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
//
//        for (int i = 0; i < message.size(); i++) {
//            eachMessageLine = message.get(i);
//            Matcher matcher = Pattern.compile(dateRegex).matcher(eachMessageLine);
//
//            if (matcher.find()) {
//                try {
//                    Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS").parse(matcher.group());
//                    dateList.add(date);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return dateList;
//    }

}
