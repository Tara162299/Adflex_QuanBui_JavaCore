package week2.chapter6;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

public class Date extends java.util.Date {
    public static void main(String[] args) {
        LocalDate myObj = LocalDate.now();      // Create a date object
        LocalTime myObj1 = LocalTime.now();      // Create a time object
        System.out.println(myObj);
        System.out.println(myObj1);


        // Calendar class
        System.out.print("\n");
        Date d1 = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        if (Calendar.SUNDAY == c.getFirstDayOfWeek()) {         //boolean
            System.out.println("Monday is the first day of the week");
        } else {
            System.out.println("Trillionth milli day of week is " + c.get(Calendar.DAY_OF_WEEK)); //find out the day of the week that the trillionth millisecond falls on.

        }


        c.add(Calendar.MONTH, 1);                   // add a month in the current date in object c
        java.util.Date d2 = c.getTime();                   // Convert object c's value back to an instance of Date.
        System.out.println("Before adding, the date is: " + d1.toString());
        System.out.println("After adding, the date is:  " + d2.toString());


        // The DateFormat Class
        System.out.print("\n");
        Date d3 = new Date();
        DateFormat[] dfa = new DateFormat[6];
        dfa[0] = DateFormat.getInstance();
        dfa[1] = DateFormat.getDateInstance();
        dfa[2] = DateFormat.getDateInstance(DateFormat.SHORT);
        dfa[3] = DateFormat.getDateInstance(DateFormat.MEDIUM);
        dfa[4] = DateFormat.getDateInstance(DateFormat.LONG);
        dfa[5] = DateFormat.getDateInstance(DateFormat.FULL);
        for (DateFormat df : dfa) {
            System.out.println(df.format(d3));
        }

        // The number format class:
        System.out.print("\n");
        float f1 = 123.4567f;
        Locale locFR = new Locale("fr");          // France
        NumberFormat[] nfa = new NumberFormat[4];
        nfa[0] = NumberFormat.getInstance();
        nfa[1] = NumberFormat.getInstance(locFR);
        nfa[2] = NumberFormat.getCurrencyInstance();
        nfa[3] = NumberFormat.getCurrencyInstance(locFR);
        for(NumberFormat nf : nfa)
            System.out.println(nf.format(f1));

    }
}
