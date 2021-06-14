package week_2.project_2.collection1;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class helperMethods_1 {
    File file;

    public helperMethods_1(File file) {
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

    // get the array containing desired characters from a string (using for struct syntax)
    public List<String> getChar(int startIndex, String s, int numChar) {
        List<String> charArray = new ArrayList<>();
        for (int i = startIndex; i <= (startIndex + numChar - 1); i++) {
            if (Character.isDigit(s.charAt(i))) {
                charArray.add(String.valueOf(s.charAt(i)));
            }
        }

        return charArray;
    }

    List<String> originalDateList = new ArrayList<>();

    // return a string arraylist of all valid date (checking for future dates)
    public List<String> originalDateList(List<String> message) {
        String eachMessageLine;
        Date currentDate = new Date();

        String dateRegex = "([1-9]|([012][0-9])|(3[01]))\\-([0]{0,1}[1-9]|1[012])\\-(\\d\\d\\d\\d) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d";
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
        String stringDate = null;

        for (int i = 0; i < message.size(); i++) {
            eachMessageLine = message.get(i);
            Matcher matcher = Pattern.compile(dateRegex).matcher(eachMessageLine);

            if (matcher.find()) {
                try {
                    Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS").parse(matcher.group());
                    if (date.before(currentDate)) {
                        stringDate = formatDate.format(date);
                    } else {
                        stringDate = "Invalid date";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                originalDateList.add(stringDate);
            }
        }
        return originalDateList;
    }

    List<List<String>> originalSyntaxList = new ArrayList<>();

    // search for bottom syntax in each message string
    public List<List<String>> getSyntaxinMessage(List<String> message, int numChar) {
        // array list to store a single header
        for (int j = 0; j < message.size(); j++) {             // tai sao phai -1 o day
            int startIndex = message.get(j).length() - numChar;
            originalSyntaxList.add(getChar(startIndex, message.get(j), numChar));
        }
        return originalSyntaxList;
    }

    HashSet<List<String>> DiffSyntaxinMessage = new LinkedHashSet<>();

    // return Hashset of different syntax in message
    public HashSet<List<String>> getDiffSyntaxMessage() {
        for (int i = 0; i < originalSyntaxList.size(); i++) {
            DiffSyntaxinMessage.add(originalSyntaxList.get(i));
        }
        return DiffSyntaxinMessage;
    }

    Map<List<String>, Integer> countAppearanceEachSyntax = new LinkedHashMap<>();

    public Map<List<String>, Integer> countAppearanceEachSyntax() {
        List<String> newCheckFutureDate = new ArrayList<>();
        List<List<String>> newBottom = new ArrayList<>();
        // new list containing only valid dates
        // new list containing only syntax corresponding with valid dates
        for (int i = 0; i < originalDateList.size(); i++) {
            if (!originalDateList.get(i).equals(("Invalid date"))) {
                newCheckFutureDate.add(originalDateList.get(i));
                newBottom.add(originalSyntaxList.get(i));

            }
        }

        // count the appearance of each syntax in the newBottom list
        int countSyntax = 0;

        for (List<String> key : originalSyntaxList) {
            countAppearanceEachSyntax.put(key, countSyntax);
        }

        for (List<String> key : DiffSyntaxinMessage) {
            int bound = newBottom.size();
            for (int i = 0; i < bound; i++) {
                if (key.equals(newBottom.get(i))) {
                    int temp = countAppearanceEachSyntax.get(key) + 1;
                    countAppearanceEachSyntax.replace(key, temp);
                }
            }
        }

        return countAppearanceEachSyntax;
    }

    List<List<String>> validSyntaxList = new ArrayList<>();

    //list containing all the syntax that validly appear at least 1 time
    public List<List<String>> getValidSyntax() {
        for (List<String> key : countAppearanceEachSyntax.keySet()) {
            if (countAppearanceEachSyntax.get(key) > 0) {
                validSyntaxList.add(key);
            }
        }

        return validSyntaxList;
    }

    // add specifically 1 month to the current date
    public Date addMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }


    // map syntax (key) and dates having the syntax (values)
    public Map<List<String>, List<Date>> mapSyntaxDates() {
        // new map of each syntax and dates that satisfies the 1-month rule
        Map<List<String>, List<Date>> mapSyntaxDates_Final = new LinkedHashMap<>();

        // map of each syntax and dates before satisfies the 1-month rule
        Map<List<String>, List<Date>> mapSyntaxDates = new LinkedHashMap<>();

        // loop mapping each syntax to the corresponding dates
        for (List<String> validSyntax : validSyntaxList) {
            int count = 0;
            List<Integer> indexList = new ArrayList<>();
            List<Date> dateCorrespondingToEachSyntax = new ArrayList<>();

            // loop to get index of the syntax in the date array
            for (List<String> syntax : originalSyntaxList) {
                if (validSyntax.equals(syntax)) {
                    indexList.add(count);
                }
                count++;
            }

            // loop to store all dates that has the same syntax
            for (int messageDateIndex : indexList) {
                String dateEachSyntax = originalDateList.get(messageDateIndex);

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
                if (!dateEachSyntax.equals("Invalid date")) {
                    try {
                        Date eachDateinSameSyntax = dateFormat.parse(dateEachSyntax);
                        dateCorrespondingToEachSyntax.add(eachDateinSameSyntax);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Collections.sort(dateCorrespondingToEachSyntax);
                    mapSyntaxDates.put(validSyntax, dateCorrespondingToEachSyntax);
                }
            }
        }

        // applying one-month rule to get final date
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
            mapSyntaxDates_Final.put(eachSyntax, finalDateList);
        }
        return mapSyntaxDates_Final;
    }


    public List<String> getDateToString(List<Date> dateList) {
        List<String> dateToString = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS"); //change date to string

        for (int i = 0; i < dateList.size(); i++) {
            Date date = dateList.get(i);
            String dateString = formatDate.format(date);
            dateToString.add(dateString);
        }

        return dateToString;
    }
}




