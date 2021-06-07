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

    List<List<String>> getSyntaxStruct = new ArrayList<>();

    // arraylist to store all syntax in each line (using for struct syntax)
    public List<List<String>> returnStructSyntax(int startIndex, List<String> struct, int numChar) {
        for (int j = 0; j < struct.size() - 1; j++) {             // tai sao phai -1 o day
            getSyntaxStruct.add(getChar(startIndex, struct.get(j), numChar));
        }
        return getSyntaxStruct;
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
        for (int i = 0; i < checkFutureDate.size(); i++) {
            if (!checkFutureDate.get(i).equals(("Invalid date"))) {
                newCheckFutureDate.add(checkFutureDate.get(i));
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



    // new map of each syntax and dates that satisfies the 1-month rule
    Map<List<String>, List<Date>> mapSyntaxDates_Final = new LinkedHashMap<>();

    // map syntax (key) and dates having the syntax (values)
    public Map<List<String>, List<Date>> mapSyntaxDates() {
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
                String dateEachSyntax = checkFutureDate.get(messageDateIndex);

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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

    public void returnFinalMessage () {
        for (List<String> eachMessage : mapSyntaxDates_Final.keySet()) {
            
        }
    }
}


