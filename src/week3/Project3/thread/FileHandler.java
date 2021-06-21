package week3.Project3.thread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public List<String> fileToString(File file) {
        List<String> text = new ArrayList<>();
        String line;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader((file).getAbsolutePath()))) {
                while ((line = br.readLine()) != null) {
                    text.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("There is no such file");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    public void fileWriter(File file) {
        FileWriter fw;
        file = new File("Resource/Project3/output/alert.txt");
        try {
            fw = new FileWriter(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
