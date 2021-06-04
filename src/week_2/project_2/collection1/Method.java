package week_2.project_2.collection1;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Method {
    File file;

    public Method(File file) {
        this.file = file;
    }

    public ArrayList<String> changeToString() {
        ArrayList<String> text = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader((this.file).getAbsolutePath()));
            while ((line = br.readLine()) != null) {
                text.add(line);
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
    public ArrayList<String> getChar(int startIndex, String s, int numChar) {
        ArrayList<String> charArray = new ArrayList<>();
        for (int i = startIndex; i <= (startIndex + numChar - 1); i++) {
            if (Character.isDigit(s.charAt(i))) {
                charArray.add(String.valueOf(s.charAt(i)));
            }
        }

        return charArray;
    }

    ArrayList<ArrayList<String>> Header = new ArrayList<>();

    // arraylist to store all syntax in each line (using for struct header)
    public ArrayList returnStructHeader(int startIndex, ArrayList<String> struct, int numChar) {
        for (int j = 0; j < struct.size() - 1; j++) {             // tai sao phai -1 o day
            Header.add(getChar(startIndex, struct.get(j), numChar));
        }
        return Header;
    }

    ArrayList<String> checkFutureDate = new ArrayList<>();

    // return a string arraylist of all valid date (checking for future dates)
    public ArrayList<String> checkFutureDate(ArrayList<String> message) {
        String string;
        Date date = new Date();

        Instant instant = date.toInstant();
        Date currentDate = Date.from(instant);      //getting the current date in Date class

        String dateRegex = "(\\d{2}-\\d{2}-\\d{4})";
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy"); //change date to string
        String stringDate = null;

        for (int i = 0; i < message.size(); i++) {
            string = message.get(i);
            Matcher m = Pattern.compile(dateRegex).matcher(string);
            if (m.find()) {
                try {
                    date = new SimpleDateFormat("dd-MM-yyyy").parse(m.group(0));
                    if (date.before(currentDate)) {
                        stringDate = fm.format(date);
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

    ArrayList<ArrayList<String>> Bottom = new ArrayList<>();

    // search for bottom syntax in each message string
    public ArrayList getSyntaxinMessage(ArrayList<String> message, int numChar) {
        // array list to store a single header
        for (int j = 0; j < message.size(); j++) {             // tai sao phai -1 o day
            int startIndex = message.get(j).length() - numChar;
            Bottom.add(getChar(startIndex, message.get(j), numChar));
        }
        return Bottom;
    }

    ArrayList<ArrayList<String>> DiffBottom = new ArrayList<>();

    // return arraylist of different syntax in message
    public ArrayList getDiffSyntaxMessage() {
        for (int i = 0; i < Bottom.size(); i++) {
            for (int j = 0; j < DiffBottom.size(); j++) {
                if (!Bottom.get(i).equals(DiffBottom.get(j))) {
                    DiffBottom.add(Bottom.get(i));
                }
            }
        }
        return DiffBottom;
    }

    ArrayList<Date> dateArrayList = new ArrayList<>();

    //from the validate dateArray above, check the struct bottom and time to see if it is at least 1 month away
    public void checkDateFinal() {
        // count appearance of each syntax in message
        int[] countSyntax = new int[DiffBottom.size()];
        for (int i = 0; i < countSyntax.length; i++) {
            countSyntax[i] = 0;
        }

        // mapping the date and the syntax of each line in the message string
        HashMap<String, ArrayList<String>> mapDateandSyntax = new HashMap<>();
        for (int i = 0; i < checkFutureDate.size(); i++) {
            mapDateandSyntax.put(checkFutureDate.get(i), Bottom.get(i));
        }

        // count appearance of each syntax in the message
        for (int i = 0; i < checkFutureDate.size(); i++) {
            if (!checkFutureDate.get(i).equals("Invalid date")) {
                countSyntax[i]++;
            }
        }


        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        for (Map.Entry<String, ArrayList<String>> temp : mapDateandSyntax.entrySet()) {
            if (!temp.getKey().equals("Invalid date")) {
                Date date;
                try {
                    date = df.parse(temp.getKey());
                    dateArrayList.add(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
//            if (!checkFutureDateArray.get(i).equals("Invalid date") && checkStructinMessage(i) == true) {
//                mapDateandSyntax.getKey();
//            }


    }


    public void checkValidDate(ArrayList<String> message, ArrayList<String> checkFutureDate, ArrayList returnHeader) {
        checkFutureDate = checkFutureDate(message);
        ArrayList<String> lastText = new ArrayList<>();

        ArrayList<Date> dateArrayList = new ArrayList<>();
        Date invalidDate = new Date();
        invalidDate = null;


        // change back the string of date to Date class
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (String dateToString : checkFutureDate) {
            Date startDate;
            if (dateToString != "Invalid date") {
                try {
                    startDate = df.parse(dateToString);
                    dateArrayList.add(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                dateArrayList.add(invalidDate);
            }
        }


        for (int i = 0; i < message.size(); i++) {
            String string = message.get(i);
            // loop to get the header for each line
            for (int j = message.get(i).length() - 4; j < message.get(i).length(); j++) {
                lastText.add(String.valueOf(string.charAt(j)));
            }
            for (Object temp : returnHeader) {
                if (lastText == temp) {

                }
            }
        }
        //    return dateArrayList;
    }
}
