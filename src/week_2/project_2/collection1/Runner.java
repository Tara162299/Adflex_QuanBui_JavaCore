package week_2.project_2.collection1;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
// nen khai bao interface, dung list thay vi arraylist
// nguyen tac solid
// output must be specific
// always khai bao modifier

public class Runner {
    public static void main(String[] args) {

        File messageInput = new File("Resource/Project2_Collection1/message.txt");
        File structInput = new File("Resource/Project2_Collection1/struct.txt");

        helperMethods message = new helperMethods(messageInput);
        helperMethods struct = new helperMethods(structInput);

        List<String> toStringStruct = struct.changeToString();
        // extract all the syntax of the String struct to an arraylist


        // change message file input to String
        List<String> toStringMessage = message.changeToString();
        // extract all the date of the String message to an arraylist (checked for future date)
        List<String> dateArray = message.originalDateList(toStringMessage);

        // choosing numChar base on the syntax that has the most character.
        List<List<String>> SyntaxMessageArray = message.getSyntaxinMessage(toStringMessage, 5);
        message.getDiffSyntaxMessage();

//        Date date = new Date();
//        List<Date> newDate = new ArrayList<>();
//        newDate.add(date);
//        System.out.println(message.getDateToString(newDate));

        System.out.println(dateArray);
        System.out.println(SyntaxMessageArray);
        System.out.println();

        Map<List<String>, Integer> test = message.countAppearanceEachSyntax();
        System.out.println(test);

        System.out.println();
        List<List<String>> mapDateandSyntax = message.getValidSyntax();
        System.out.println(mapDateandSyntax);
        System.out.println();

        Map<List<String>, List<Date>> dateTest = message.mapSyntaxDates();
        System.out.println(dateTest);

        System.out.println();

        File[] outputFiles = message.createOutputFile(toStringMessage);
        for (int i = 0; i < outputFiles.length; i++) {
        //    System.out.println(outputFiles[i].exists());
        }
    }
}
