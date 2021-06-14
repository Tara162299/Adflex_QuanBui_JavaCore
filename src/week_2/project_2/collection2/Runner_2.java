package week_2.project_2.collection2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Runner_2 {
    public static void main(String[] args) {

        File files = new File("Resource/Project2_Collection2/input");
        List<File> fileList = new ArrayList<>();

        for (File f : files.listFiles()) {
            f = new File(f.getAbsolutePath());
            fileList.add(f);
        }

        // create helperMethod for each input file
        helperMethods_2[] fileArrays = new helperMethods_2[files.listFiles().length];
        int count = 0;

        List<String> phoneNum = new ArrayList<>();
        for (File f : fileList) {
            fileArrays[count] = new helperMethods_2(f.getAbsoluteFile());
            phoneNum.add(fileArrays[count].getPhone(f));
            //System.out.println(fileArrays[count].getPhone(f));
            count++;
        }
        System.out.println();

        // list to store all String list for dates for all input files
        List<List<String>> dateStringListforName = new ArrayList<>();

        List<List<String>> dateStringListforContent = new ArrayList<>();

        List<List<String>> dateStringListFinal = new ArrayList<>();

        // loop to create each Date list and String list for dates for each input file
        for (int i = 0; i < fileArrays.length; i++) {
            System.out.println(fileArrays[i].changeToString());
            String s = phoneNum.get(i);
            System.out.println(s);

            List<String> DateStringforContent = fileArrays[i].getDateStringforContent(fileArrays[i].changeToString());
            List<String> DateStringforName = fileArrays[i].getDateStringforName(fileArrays[i].changeToString());

            Collections.sort(DateStringforContent);
            Collections.sort(DateStringforName);

            List<String> DateStringFinal = new ArrayList<>();

            for (int j = 0; j < DateStringforContent.size(); j++) {
                String added = fileArrays[i].insertString(DateStringforContent.get(j), s);
                DateStringFinal.add(added);
            }
            Collections.sort(DateStringFinal);

            dateStringListFinal.add(DateStringFinal);
            dateStringListforName.add(DateStringforName);
            dateStringListforContent.add(DateStringforContent);

            System.out.println(dateStringListforName.get(i));
            System.out.println(dateStringListFinal.get(i));

            System.out.println();
        }

        // a list of all String dates in the input files
        List<String> dateString = new ArrayList<>();
        for (int i = 0; i < dateStringListforName.size(); i++) {
            for (String date : dateStringListforName.get(i)) {
                dateString.add(date);
            }
        }

        List<String> dateStringWithTime = new ArrayList<>();
        for (int i = 0; i < dateStringListforContent.size(); i++) {
            for (String date : dateStringListFinal.get(i)) {
                dateStringWithTime.add(date);
            }
        }

        System.out.println();
        //get a set of all different dates to create output files
        Set<String> diffDateString = fileArrays[0].getDiffDateString(dateStringListforName);
        System.out.println(diffDateString);

        System.out.println();
        // creating all output files
        for (String date : diffDateString) {

            // get the index of all dates in dateString that are equal to current-loop date in dateString
            List<Integer> indexList = new ArrayList<>();
            for (int i = 0; i < dateString.size(); i++) {
                if (dateString.get(i).equals(date)) {
                    indexList.add(i);
                }
            }

            // add all dates that having the index in indexList to a new date list
            List<String> chosenDate = new ArrayList<>();
            for (int i : indexList) {
                chosenDate.add(dateStringWithTime.get(i));
            }

            Collections.sort(chosenDate);

            StringBuilder newDate = fileArrays[0].removeCharacter(date, '/');
            File file = new File("Resource/Project2_Collection2/output/" + newDate + ".txt");
            FileWriter fw;
            try {
                fw = new FileWriter(file.getAbsolutePath());
                for (int i = 0; i < chosenDate.size(); i++) {
                    fw.write(chosenDate.get(i) + "|");
                    fw.write("Ha noi cua toi" + "\n");
                }
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}