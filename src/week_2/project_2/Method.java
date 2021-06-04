package week_2.project_2;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Method {
    File file = new File("File");

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

    // store all the desired characters in each line in an arraylist (using for struct header)
    public ArrayList returnStructHeader(int startIndex, ArrayList<String> struct, int numChar) {
        // array list to store a single header
        ArrayList<ArrayList<String>> Header = new ArrayList<>();

        for (int j = 0; j < struct.size() - 1; j++) {             // tai sao phai -1 o day
            Header.add(getChar(startIndex, struct.get(j), numChar));
        }
        return Header;
    }


    // return an arraylist of all valid date (checking for future dates)
    public ArrayList<String> checkFutureDate(ArrayList<String> message) {
        ArrayList<String> dateArray = new ArrayList<>();
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
                dateArray.add(stringDate);
            }
        }

        return dateArray;
    }


    // search for bottom struct in each message string
    public ArrayList getStructinMessage(ArrayList<String> message, int numChar) {
        // array list to store a single header

        ArrayList<ArrayList<String>> Bottom = new ArrayList<>();

        for (int j = 0; j < message.size(); j++) {             // tai sao phai -1 o day
            int startIndex = message.get(j).length() - numChar;
            Bottom.add(getChar(startIndex, message.get(j), numChar));
        }
        return Bottom;
    }

    //from the validate dateArray above, check the struct bottom and time to see if it is at least 1 month away
    public void checkDateFinal(ArrayList<String> message, ArrayList<String> checkFutureDate, ArrayList returnStructHeader, ArrayList getStructinMessage) {

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


//    public static void returnPhone(ArrayList<String> message) {
//        int countChar = 0;
//        ArrayList<String> phoneNumber = new ArrayList<>();
//        ArrayList<ArrayList<String>> phoneArray = new ArrayList<>();
//
//        for (int i = 0; i < message.size(); i++) {
//            String string = message.get(i);
//            for (int j = 1; j <= 12; j++) {
//                if (Character.isDigit(string.charAt(j))) {
//                    phoneNumber.add(String.valueOf(string.charAt(j)));
//                    countChar++;
//                }
//            }
//
//        }
//    }
//
//    public static void checkValidPhone(ArrayList<String> message) {
//        ArrayList<String> phoneArray = new ArrayList<>();
//        String phoneRegex = "^\\+(?:[0-9] ?){11}[0-9]$";
//
//        if (Pattern.compile(phoneRegex).matcher("+84 0234768746").matches()) {
//            System.out.println("yes");
//        } else {
//            System.out.println("no");
//        }
//    }