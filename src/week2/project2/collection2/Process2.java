package week2.project2.collection2;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Process2 {
    File file;

    public Process2(File file) {
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

    public void process() {
        File files = new File("Resource/Project2_Collection2/input");
        List<File> fileList = new ArrayList<>();

        for (File f : Objects.requireNonNull(files.listFiles())) {
            f = new File(f.getAbsolutePath());
            fileList.add(f);
        }

        // create helperMethod for each input file
        Process2[] fileArrays = new Process2[Objects.requireNonNull(files.listFiles()).length];
        int count = 0;

        List<String> phoneNum = new ArrayList<>();
        for (File f : fileList) {
            fileArrays[count] = new Process2(f.getAbsoluteFile());
            phoneNum.add(fileArrays[count].getPhone(f));
            count++;
        }

        List<List<String>> dateStringListforName = new ArrayList<>();

        List<List<String>> dateStringListFinal = new ArrayList<>();

        // loop to create each Date list and String list for dates for each input file
        for (int i = 0; i < fileArrays.length; i++) {
            String phoneNumber = phoneNum.get(i);

            List<String> DateStringforContent = fileArrays[i].getDateStringforContent(fileArrays[i].changeToString());
            List<String> DateStringforName = fileArrays[i].getDateStringforName(fileArrays[i].changeToString());

            Collections.sort(DateStringforContent);
            Collections.sort(DateStringforName);

            List<String> DateStringFinal = new ArrayList<>();

            for (String s : DateStringforContent) {
                String added = fileArrays[i].insertString(s, phoneNumber);
                DateStringFinal.add(added);
            }
            Collections.sort(DateStringFinal);

            dateStringListFinal.add(DateStringFinal);
            dateStringListforName.add(DateStringforName);
        }

        // a list of all String dates in the input files
        List<String> dateStringforName = new ArrayList<>();
        for (List<String> stringList : dateStringListforName) {
            dateStringforName.addAll(stringList);
        }

        List<String> dateStringforContent = new ArrayList<>();
        for (List<String> strings : dateStringListFinal) {
            dateStringforContent.addAll(strings);
        }

        //get a set of all different dates to create output files
        Set<String> diffDateString = fileArrays[0].getDiffDateString(dateStringListforName);

        // creating all output files
        for (String date : diffDateString) {

            // get the index of all dates in dateString that are equal to current-loop date in dateString
            List<Integer> indexList = new ArrayList<>();
            for (int i = 0; i < dateStringforName.size(); i++) {
                if (dateStringforName.get(i).equals(date)) {
                    indexList.add(i);
                }
            }

            // add all dates that having the index in indexList to a new date list
            List<String> chosenDate = new ArrayList<>();
            for (int i : indexList) {
                chosenDate.add(dateStringforContent.get(i));
            }

            Collections.sort(chosenDate);

            StringBuilder newDate = fileArrays[0].removeCharacter(date, '/');
            File file = new File("Resource/Project2_Collection2/output/" + newDate + ".txt");
            FileWriter fw;
            try {
                fw = new FileWriter(file.getAbsolutePath());
                for (String s : chosenDate) {
                    fw.write(s + "|");
                    fw.write("Ha noi cua toi" + "\n");
                }
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
