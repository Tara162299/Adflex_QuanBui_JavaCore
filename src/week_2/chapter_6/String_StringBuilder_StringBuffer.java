public class String_StringBuilder_StringBuffer {
    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer("Ducati Monster");
        // adding another string at s1.length() position
        s1.insert(s1.length(), " is fucking beautiful");

        System.out.println(s1);

        System.out.println("");

        // reverse string s1 using method reverse
        s1.reverse();
        System.out.println(s1);


        // toString method to returns a string representing the data contained by StringBuilder Object.
        System.out.println("");
        s1.toString();
        System.out.println(s1.reverse());
        System.out.println("");

        long startTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer("Java");
        for (int i = 0; i < 10000; i++) {
            sb.append("Tpoint");
        }
        System.out.println("Time taken by StringBuffer: " + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder("Java");
        for (int i = 0; i < 10000; i++) {
            sb2.append("Tpoint");
        }
        System.out.println("Time taken by StringBuilder: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}

