package week_2.project_2.collection1;


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

//        String startDateString = "27-06-2007";
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//        Date startDate;
//        try {
//            startDate = df.parse(startDateString);
//            System.out.println(startDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        Date date = new Date();
//        System.out.println(date);
//        String s = "haahahaha";
//
//        StringBuilder sth = new StringBuilder();
//        for (int i = 0; i < 5; i++) {
//            sth.append('a');
//        }
//        System.out.println(sth.toString());

        List<String> dateToString = new ArrayList<>();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS"); //change date to string

        Date date = new Date();
        String dateString = formatDate.format(date);
        System.out.println(date);
        System.out.println(dateString);
        //dateToString.add(dateString);

        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        List<Date> listDate = new ArrayList<>();
        List<Date> listDate1 = new ArrayList<>();
        List<Date> listDate2 = new ArrayList<>();

        listDate.add(date1);
        listDate.add(date2);
        listDate.add(date3);

        listDate1.add(date1);
        listDate1.add(date2);

        listDate2.add(date1);

        String string1 = "String 1";
        String string2 = "String 2";
        String string3 = "String 3";

        Map<String, List<Date>> mapSyntaxDates_Final = new LinkedHashMap<>();
        mapSyntaxDates_Final.put(string1, listDate);
        mapSyntaxDates_Final.put(string2, listDate1);
        mapSyntaxDates_Final.put(string3, listDate2);

        for (String stringChecking : mapSyntaxDates_Final.keySet()) {
            List<Date> hahaha = mapSyntaxDates_Final.get(stringChecking);
            System.out.println(hahaha);
        }


    }

}




