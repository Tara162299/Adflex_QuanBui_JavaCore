package week_2.project_2.collection2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {

        File files = new File("Resource/Project2_Collection2/input/");
        List<File> fileList = new ArrayList<>();

        for (File f : files.listFiles()) {
            f = new File(f.getAbsolutePath());
            fileList.add(f);
        }

        helperMethods[] fileArrays = new helperMethods[files.listFiles().length];
        int count = 0;
        for (File f : fileList) {
            fileArrays[count] = new helperMethods(f.getAbsoluteFile());
            count++;
        }

        for (int i = 0; i < fileArrays.length; i++) {
            System.out.println(fileArrays[i].changeToString());
            System.out.println(fileArrays[i].originalDateList(fileArrays[i].changeToString()));
            System.out.println();
        }



    }


}
