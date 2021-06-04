package week_2.project_2.collection1;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int[] count = new int[4];
        String[] name = new String[4];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
            name[i] = scan.next();
        }

        Map<String, Integer> m = new HashMap<String, Integer>(); //implementation of the Map Interface

        for (int i = 0; i < name.length; i++) {
            m.put(name[i], count[i]);
        }

        for (Map.Entry<String, Integer> me : m.entrySet())// Traversing of the elements through the Map
        {
            System.out.print(me.getKey() + ":");
            System.out.println(me.getValue());
        }

    }
}
