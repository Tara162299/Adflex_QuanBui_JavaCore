package week2.chapter_6;
public class String_StringBuilder_StringBuffer {
    // Should use Stringbuilder as it is not thread safe (its methods are not synchronized).
    public static void main(String[] args) {

        StringBuilder s1 = new StringBuilder("Ducati Monster");
        // adding another string at s1.length() position
        s1.insert(s1.length(), " is fucking beautiful");

        System.out.println(s1);

        System.out.println();

        // reverse string s1 using method reverse
        s1.reverse();
        System.out.println(s1);
        System.out.println();

        // toString method to returns a string representing the data contained by StringBuilder Object.
        s1.toString();
        System.out.println(s1.reverse());
        System.out.println("");

        //Comparing runtime of StringBuilder and StringBuffer
        long startTime1 = System.currentTimeMillis();
        StringBuffer sb1 = new StringBuffer("Java");
        for (int i = 0; i < 10000; i++) {
            sb1.append("Tpoint");
        }
        System.out.println("Time taken by StringBuffer: " + (System.currentTimeMillis() - startTime1) + "ms");

        long startTime2 = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder("Java");
        for (int i = 0; i < 10000; i++) {
            sb2.append("Tpoint");
        }
        System.out.println("Time taken by StringBuilder: " + (System.currentTimeMillis() - startTime2) + "ms");
    }
}

