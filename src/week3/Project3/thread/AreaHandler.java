package week3.Project3.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AreaHandler {
    String areaPath = "Resource/Project3/input/area.txt";
    File areaFile = new File(areaPath);
    FileHandler dataExtraction = new FileHandler();
    List<String> areaInput = dataExtraction.fileToString(areaFile);

    public List<String> getLongArea(String s) {
        String LongRegex = "\\|\\d{3}\\|\\d{3}";
        String temp = null;
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(LongRegex);
        Matcher match = pattern.matcher(s);

        if (match.find()) {
            temp = match.group();
        }

        StringBuilder longLeft = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            longLeft.append(temp.charAt(i));
        }

        StringBuilder longRight = new StringBuilder();
        for (int i = 5; i <= 7; i++) {
            longRight.append(temp.charAt(i));
        }

        result.add(longLeft.toString());
        result.add(longRight.toString());

        return result;
    }

    public List<String> getLatArea(String s) {
        String LongRegex = "\\|\\d{3}\\|\\d{3}\\|\\d{3}\\|\\d{3}";
        String temp = null;
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(LongRegex);
        Matcher match = pattern.matcher(s);

        if (match.find()) {
            temp = match.group();
        }

        StringBuilder latTop = new StringBuilder();
        for (int i = 9; i <= 11; i++) {
            latTop.append(temp.charAt(i));
        }

        StringBuilder latBottom = new StringBuilder();
        for (int i = 13; i <= 15; i++) {
            latBottom.append(temp.charAt(i));
        }

        result.add(latTop.toString());
        result.add(latBottom.toString());

        return result;
    }
}
