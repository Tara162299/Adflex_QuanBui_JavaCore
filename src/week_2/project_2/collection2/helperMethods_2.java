package week_2.project_2.collection2;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


    public List<String> originalDateList(List<String> message) {
        List<String> originalDateList = new ArrayList<>();
        String eachMessageLine;

        String dateRegex = "(\\d\\d\\d\\d)\\/([0]{0,1}[1-9]|1[012])\\/([1-9]|([012][0-9])|(3[01])) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS");
        String stringDate = null;

        for (int i = 0; i < message.size(); i++) {
            eachMessageLine = message.get(i);
            Matcher matcher = Pattern.compile(dateRegex).matcher(eachMessageLine);

            if (matcher.find()) {
                try {
                    Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS").parse(matcher.group());
                    stringDate = formatDate.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                originalDateList.add(stringDate);
            }
        }
        return originalDateList;
    }
}
