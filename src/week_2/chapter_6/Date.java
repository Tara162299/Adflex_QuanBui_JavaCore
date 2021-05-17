import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
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
        Calendar c = Calendar.getInstance();                // important!!!
        c.setTime(d1);                          // #1
        if (Calendar.SUNDAY == c.getFirstDayOfWeek()) {        // #2
            System.out.println("Monday is the first day of the week");
        } else {
            System.out.println("trillionth milli day of week is " + c.get(Calendar.DAY_OF_WEEK));
        }
        c.add(Calendar.MONTH, 1);
        java.util.Date d2 = c.getTime();                    // #3 Convert c's value back to an instance of Date.
        System.out.println("new date " + d2.toString());


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

        // The numberformat class:
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
