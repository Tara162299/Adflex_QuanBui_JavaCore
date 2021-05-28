package week_2.chapter_6.Tokenizing_String;

public class split {
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {

        // split the string before a particular regex
        String s = "I love you baby and it is quite alright";
        String[] token = s.split("a");
        for (String s1 : token) {
            System.out.println(">" + s1 + "<");
        }


    }
}
