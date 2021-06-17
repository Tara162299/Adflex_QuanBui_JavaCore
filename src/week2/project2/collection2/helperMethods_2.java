package week2.project2.collection2;

import java.io.*;
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

        return originalString + "|" + stringToBeInserted;
    }

    // String list of dates that in desired format in string
    public List<String> getDateStringforName(List<String> message) {
        List<String> tempDateList = new ArrayList<>();
        String eachMessageLine;

        String dateRegex = "(\\d\\d\\d\\d)/([0]{0,1}[1-9]|1[012])/([1-9]|([012][0-9])|(3[01]))";

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
        String stringDate = null;

        for (String s : message) {
            eachMessageLine = s;
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

        String dateRegex = "(\\d\\d\\d\\d)/([0]{0,1}[1-9]|1[012])/([1-9]|([012][0-9])|(3[01])) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String stringDate = null;

        for (String s : message) {
            eachMessageLine = s;
            Matcher matcherDate = Pattern.compile(dateRegex).matcher(eachMessageLine);

            if (matcherDate.find()) {
                try {
                    Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(matcherDate.group());
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

        for (List<String> strings : dateStringList) {
            diffDateString.addAll(strings);
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
}
