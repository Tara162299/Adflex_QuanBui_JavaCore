package week2.project2.collection1;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Process1 {
    File file;
    Pattern timePattern = Pattern.compile("dd-MM-yyyy HH:mm:ss");
    String invalidDate = "Invalid date";

    public Process1(File file) {
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

        String dateRegex = "([1-9]|([012][0-9])|(3[01]))-([0]{0,1}[1-9]|1[012])-(\\d\\d\\d\\d) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d";
        SimpleDateFormat formatDate = new SimpleDateFormat(timePattern.pattern());
        String stringDate = null;

        for (String s : message) {
            eachMessageLine = s;
            Matcher matcher = Pattern.compile(dateRegex).matcher(eachMessageLine);

            if (matcher.find()) {
                try {
                    Date date = new SimpleDateFormat(timePattern.pattern()).parse(matcher.group());
                    if (date.before(currentDate)) {
                        stringDate = formatDate.format(date);
                    } else {
                        stringDate = invalidDate;
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
        for (String s : message) {             // tai sao phai -1 o day
            int startIndex = s.length() - numChar;
            originalSyntaxList.add(getChar(startIndex, s, numChar));
        }
        return originalSyntaxList;
    }

    HashSet<List<String>> DiffSyntaxinMessage = new LinkedHashSet<>();

    // return Hashset of different syntax in message
    public void getDiffSyntaxMessage() {
        DiffSyntaxinMessage.addAll(originalSyntaxList);
    }

    Map<List<String>, Integer> countAppearanceEachSyntax = new LinkedHashMap<>();

    public void countAppearanceEachSyntax() {
        List<String> newCheckFutureDate = new ArrayList<>();
        List<List<String>> newBottom = new ArrayList<>();
        // new list containing only valid dates
        // new list containing only syntax corresponding with valid dates
        for (int i = 0; i < originalDateList.size(); i++) {
            if (!originalDateList.get(i).equals((invalidDate))) {
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
            for (List<String> strings : newBottom) {
                if (key.equals(strings)) {
                    int temp = countAppearanceEachSyntax.get(key) + 1;
                    countAppearanceEachSyntax.replace(key, temp);
                }
            }
        }

    }

    List<List<String>> validSyntaxList = new ArrayList<>();

    //list containing all the syntax that validly appear at least 1 time
    public void getValidSyntax() {
        for (List<String> key : countAppearanceEachSyntax.keySet()) {
            if (countAppearanceEachSyntax.get(key) > 0) {
                validSyntaxList.add(key);
            }
        }

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

                DateFormat dateFormat = new SimpleDateFormat(timePattern.pattern());
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
        SimpleDateFormat formatDate = new SimpleDateFormat(timePattern.pattern()); //change date to string

        for (Date date : dateList) {
            String dateString = formatDate.format(date);
            dateToString.add(dateString);
        }

        return dateToString;
    }

    public void process() {
        List<String> toStringMessage = changeToString();

        // extract all the date of the String message to a list (checked for future date)
        List<String> dateArray = originalDateList(toStringMessage);


        // choosing numChar base on the syntax that has the most character.
        List<List<String>> SyntaxMessageList = getSyntaxinMessage(toStringMessage, 5);

        getDiffSyntaxMessage();

        // map each syntax with its number of valid appearances
        countAppearanceEachSyntax();
        getValidSyntax();

        Map<List<String>, List<Date>> syntaxDateMap = mapSyntaxDates();

        for (List<String> syntaxChecking : syntaxDateMap.keySet()) {
            // creating new output file of the valid syntax
            StringBuilder strbul = new StringBuilder();

            for (String str : syntaxChecking) {
                strbul.append(str);
            }

            // check and write valid message to the corresponding output file

            for (List<String> messageSyntaxChecking : SyntaxMessageList) {

                // checking if the syntax of mapSyntaxDates is the same as original syntax list
                if (syntaxChecking.equals(messageSyntaxChecking)) {
                    List<Date> dateChecking = syntaxDateMap.get(syntaxChecking);
                    int indexSyntaxMessage = SyntaxMessageList.indexOf(messageSyntaxChecking); // ********

                    for (int i = 0; i < dateChecking.size(); i++) {
                        // if the date from original date list = date from map
                        if (dateArray.get(indexSyntaxMessage).equals(getDateToString(dateChecking).get(i))) {
                            String messageChosen = toStringMessage.get(indexSyntaxMessage);

                            try {
                                FileWriter fw = new FileWriter("Resource/Project2_Collection1/output/" + strbul + ".txt");
                                fw.write(messageChosen + "\n");
                                fw.flush();
                                fw.close();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }
    }
}




