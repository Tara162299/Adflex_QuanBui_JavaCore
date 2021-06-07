package week_2.project_2.collection1;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) {
//        int[] count = new int[4];
//        String[] name = new String[4];
//        Scanner scan = new Scanner(System.in);
//        for (int i = 0; i < count.length; i++) {
//            count[i] = 0;
//            name[i] = scan.next();
//        }
//
//        Map<String, Integer> m = new HashMap<String, Integer>(); //implementation of the Map Interface
//
//        for (int i = 0; i < name.length; i++) {
//            m.put(name[i], count[i]);
//        }
//
//        for (Map.Entry<String, Integer> me : m.entrySet())// Traversing of the elements through the Map
//        {
//            System.out.print(me.getKey() + ":");
//            System.out.println(me.getValue());
//        }
//
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//        String dateInString = "07-06-2013";
//        ArrayList<Date> dateArrayList = new ArrayList<>();
//
//        try {
//            Date date = formatter.parse(dateInString);
//            System.out.println(date);
//            dateArrayList.add(date);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < dateArrayList.size(); i++) {
//            System.out.println(dateArrayList.get(i));
//        }

        String startDateString = "27-06-2007";
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate;
        try {
            startDate = df.parse(startDateString);
            System.out.println(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}

