package week_2.project_2.collection1;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Method {
    File file;

    public Method(File file) {
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

    // get the array containing desired characters from a string (using for struct header)
    public List<String> getChar(int startIndex, String s, int numChar) {
        List<String> charArray = new ArrayList<>();
        for (int i = startIndex; i <= (startIndex + numChar - 1); i++) {
            if (Character.isDigit(s.charAt(i))) {
                charArray.add(String.valueOf(s.charAt(i)));
            }
        }

        return charArray;
    }

    List<List<String>> Header = new ArrayList<>();

    // arraylist to store all syntax in each line (using for struct header)
    public List<List<String>> returnStructHeader(int startIndex, List<String> struct, int numChar) {
        for (int j = 0; j < struct.size() - 1; j++) {             // tai sao phai -1 o day
            Header.add(getChar(startIndex, struct.get(j), numChar));
        }
        return Header;
    }

    List<String> checkFutureDate = new ArrayList<>();

    // return a string arraylist of all valid date (checking for future dates)
    public List<String> checkFutureDate(List<String> message) {
        String eachMessageLine;
        Date currentDate = new Date();

        String dateRegex = "(\\d{2}-\\d{2}-\\d{4})";
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS"); //change date to string
        String stringDate = null;

        for (int i = 0; i < message.size(); i++) {
            eachMessageLine = message.get(i);
            Matcher m = Pattern.compile(dateRegex).matcher(eachMessageLine);
            if (m.find()) {
                try {
                    Date date = new SimpleDateFormat("dd-MM-yyyy").parse(m.group(0));
                    if (date.before(currentDate)) {
                        stringDate = formatDate.format(date);
                    } else {
                        stringDate = "Invalid date";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                checkFutureDate.add(stringDate);
            }
        }
        return checkFutureDate;
    }

    List<List<String>> Bottom = new ArrayList<>();

    // search for bottom syntax in each message string
    public List<List<String>> getSyntaxinMessage(List<String> message, int numChar) {
        // array list to store a single header
        for (int j = 0; j < message.size(); j++) {             // tai sao phai -1 o day
            int startIndex = message.get(j).length() - numChar;
            Bottom.add(getChar(startIndex, message.get(j), numChar));
        }
        return Bottom;
    }

    HashSet<List<String>> DiffBottom = new LinkedHashSet<>();

    // return Hashset of different syntax in message
    public HashSet<List<String>> getDiffSyntaxMessage() {
        for (int i = 0; i < Bottom.size(); i++) {
            DiffBottom.add(Bottom.get(i));
        }
        return DiffBottom;
    }

    Map<List<String>, Integer> countAppearanceSyntax = new LinkedHashMap<>();

    public Map<List<String>, Integer> countAppearanceSyntax() {
        List<String> newCheckFutureDate = new ArrayList<>();
        List<List<String>> newBottom = new ArrayList<>();
        // new list containing only valid dates
        // new list containing only syntax corresponding with valid dates
        for (int i = 0; i < checkFutureDate.size(); i++) {
            if (!checkFutureDate.get(i).equals(("Invalid date"))) {
                newCheckFutureDate.add(checkFutureDate.get(i));
                newBottom.add(Bottom.get(i));

            }
        }

        // count the appearance of each syntax in the newBottom list
        int countSyntax = 0;

        for (List<String> key : Bottom) {
            countAppearanceSyntax.put(key, countSyntax);
        }

        for (List<String> key : DiffBottom) {
            int bound = newBottom.size();
            for (int i = 0; i < bound; i++) {
                if (key.equals(newBottom.get(i))) {
                    int temp = countAppearanceSyntax.get(key) + 1;
                    countAppearanceSyntax.replace(key, temp);
                }
            }
        }

        return countAppearanceSyntax;
    }

    List<List<String>> listValidSyntax = new ArrayList<>();

    //list containing all the syntax that is valid (appear at least 1 time)
    public List<List<String>> getValidSyntax() {
        for (List<String> key : countAppearanceSyntax.keySet()) {
            if (countAppearanceSyntax.get(key) > 0) {
                listValidSyntax.add(key);
            }
        }

        return listValidSyntax;
    }

    Map<List<String>, List<Date>> mapSyntaxDates = new LinkedHashMap<>();

    //from the validate dateArray above, check the struct bottom and time to see if it is at least 1 month away
    public Map<List<String>, List<Date>> mapSyntaxDates() {

        // loop checking every syntax that is valid
        for (List<String> validSyntax : listValidSyntax) {
            int count = 0;
            List<Integer> indexList = new ArrayList<>();
            List<Date> dateCorrespondingToEachSyntax = new ArrayList<>();
            // loop to get index of the syntax in the date array
            for (List<String> syntax : Bottom) {
                if (validSyntax.equals(syntax)) {
                    indexList.add(count);
                }
                count++;
            }
            // loop to store all string date that has the same syntax
            for (int messageDateIndex : indexList) {
                String dateEachSyntax = checkFutureDate.get(messageDateIndex);

                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                if (!dateEachSyntax.equals("Invalid date")) {
                    try {
                        Date eachDateinSameSyntax = df.parse(dateEachSyntax);
                        dateCorrespondingToEachSyntax.add(eachDateinSameSyntax);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                 //   List<Date> newDateCorrespondingToEachSyntax = Collections.sort(dateCorrespondingToEachSyntax);
                    mapSyntaxDates.put(validSyntax, dateCorrespondingToEachSyntax);
                }
            }
        }
        return mapSyntaxDates;
    }

    // add specifically 1 month to the current date
    public Date addMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    // new map of each syntax and dates that satisfies the 1-month rule
    Map<List<String>, List<Date>> Final_mapSyntaxDates = new LinkedHashMap<>();

    // get the final map of each syntax with final valid dates
    public Map<List<String>, List<Date>> checkDate_1month() {

        for (List<String> eachSyntax : mapSyntaxDates.keySet()) {
            List<Date> listDateEachSyntax = mapSyntaxDates.get(eachSyntax);
            List<Date> finalDateList = new ArrayList<>();

            Date firstDate = listDateEachSyntax.get(0);
            finalDateList.add(firstDate);

            for (Date date : listDateEachSyntax) {
                if (date.after(addMonth(firstDate)) || date.equals(addMonth(firstDate))) {
                    finalDateList.add(date);
                    firstDate = date;
                }
            }
            Final_mapSyntaxDates.put(eachSyntax, finalDateList);
        }
        return Final_mapSyntaxDates;
    }
}


