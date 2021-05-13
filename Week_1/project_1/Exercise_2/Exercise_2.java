package project_1.Exercise_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise_2 {
    public static void main(String[] args) {

        System.out.print("Please enter the date in the format: yyyy/mm/dd: ");
        Scanner scan = new Scanner(System.in);
        String date = scan.next();
        System.out.println("The date entered is: " + date);
        System.out.print("\n");


        String day = "";
        day = day + date.charAt(6) + date.charAt(7);
        String month = "";
        month = month + date.charAt(4) + date.charAt(5);
        String year = "";
        year = year + date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);


        int dateLength = date.length();
        if (dateLength != 8) {
            System.out.println("The date format is not correct! Please enter the date again!");
        } else if (date.equals("20151106") || date.equals("20151107") || date.equals("20151108")) {
            List<String> inputList = new ArrayList<>();
            List<String> outputList = new ArrayList<>();

            List<String> inputListFinal = new ArrayList<>();
            List<String> outputListFinal = new ArrayList<>();

            int count1 = 0;
            int count2 = 0;

            String s = "_8x56_";

            for (int x = 1; x < 8; x++) {
                try {
                    File myObj = new File("/Users/martinbui/Desktop/input/" + date + "/cdr_" + date + s + x + ".txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        boolean isPhoneValid = data.length() == 10 && data.charAt(0) == '8' && data.charAt(1) == '4' && data.charAt(2) == '9';
                        if (isPhoneValid) {
                            inputList.add(data);
                            count1++;
                        }
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("There is no such input file as " + "cdr_" + date + s + x + ".txt!");
                }

                try {
                    File myObj = new File("/Users/martinbui/Desktop/output/" + date + "/" + date + s + x + ".txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if (data.length() == 10 && data.charAt(0) == '8' && data.charAt(1) == '4' && data.charAt(2) == '9') {
                            outputList.add(data);
                            count2++;
                        }
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("There is no such output file as " + date + s + x + ".txt!");
                    System.out.println("_________________________________________________________________________________");
                    continue;
                    //e.printStackTrace();
                }

                for (int a = 0; a < count1; a++) {
                    int tempCount = 0;
                    for (int b = 0; b < count2; b++) {
                        if (inputList.get(a).equals(outputList.get(b))) {
                            tempCount++;
                        }
                    }
                    if (tempCount == 0) {
                        inputListFinal.add(inputList.get(a));
                    }
                }
                if (!inputListFinal.isEmpty()) {
                    System.out.println("On " + day + "-" + month + "-" + year + ", the phone numbers that input file cdr_" + date + s + x + ".txt has exclusively are: ");
                    for (String value : inputListFinal) {
                        System.out.println(value);
                    }
                }

                for (int a = 0; a < count2; a++) {
                    int tempCount = 0;
                    for (int b = 0; b < count1; b++) {
                        if (outputList.get(a).equals(inputList.get(b))) {
                            tempCount++;
                        }
                    }
                    if (tempCount == 0) {
                        outputListFinal.add(outputList.get(a));
                    }
                }
                if (outputListFinal.isEmpty()) {
                    System.out.println("On " + day + "-" + month + "-" + year + ", all the phone numbers from input file: cdr_" + date + s + x + ".txt" + " and output file: " + date + "_8x56_" + x + ".txt are the same");
                } else {
                    System.out.println();
                    System.out.println("On " + day + "-" + month + "-" + year + ", the phone numbers that output file " + date + s + x + ".txt has exclusively are: ");
                    for (String value : outputListFinal) {
                        System.out.println(value);
                    }
                }
                System.out.println("_________________________________________________________________________________");
            }
        } else {
            System.out.println("There is no such date in the datebase");
        }

    }
}