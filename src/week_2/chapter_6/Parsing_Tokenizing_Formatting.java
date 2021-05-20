package week_2.chapter_6;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing_Tokenizing_Formatting {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("a.b?");                            //Pattern.CASE_INSENSITIVE
        Matcher matcher = pattern.matcher("abaaaba");
        while (matcher.find()) {
            System.out.print(matcher.start() + " ");
        }

        System.out.println();

        Pattern p = Pattern.compile(".*x.x");
        Matcher m = p.matcher(" yyxxxyxx");
        while (m.find()) {
            System.out.print(m.start() + " ");
        }


    }
}
