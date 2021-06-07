package week_2.project_2.collection1;

import java.io.File;
import java.util.*;
// nen khai bao interface, dung list thay vi arraylist
// nguyen tac solid
// output must be specific
// always khai bao modifier

public class Runner {
    public static void main(String[] args) {

        File messageInput = new File("Resource/Project2_Collection1/message.txt");
        File structInput = new File("Resource/Project2_Collection1/struct.txt");

        Method message = new Method(messageInput);
        Method struct = new Method(structInput);

        List<String> toStringStruct = struct.changeToString();
        // extract all the syntax of the String struct to an arraylist
        List<List<String>> HeaderStructArray = struct.returnStructSyntax(1, toStringStruct, 4);


        // change message file input to String
        List<String> toStringMessage = message.changeToString();
        // extract all the date of the String message to an arraylist (checked for future date)
        List<String> dateArray = message.checkFutureDate(toStringMessage);

        // choosing numChar base on the syntax that has the most character.
        List<List<String>> SyntaxMessageArray = message.getSyntaxinMessage(toStringMessage, 5);
        HashSet<List<String>> SyntaxMessageArray_Test = message.getDiffSyntaxMessage();

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

    }

}
