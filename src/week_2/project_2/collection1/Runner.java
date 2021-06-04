package week_2.project_2.collection1;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;


public class Runner {
    public static void main(String[] args) {

        File messageInput = new File("Resource/Project2_Collection1/message.txt");
        File structInput = new File("Resource/Project2_Collection1/struct.txt");

        Method message = new Method(messageInput);
        Method struct = new Method(structInput);

        ArrayList<String> toStringStruct = struct.changeToString();
        // extract all the syntax of the String struct to an arraylist
        ArrayList<String> HeaderStructArray = struct.returnStructHeader(1, toStringStruct, 4);


        // change message file input to String
        ArrayList<String> toStringMessage = message.changeToString();
        // extract all the date of the String message to an arraylist (checked for future date)
        ArrayList<String> dateArray = message.checkFutureDate(toStringMessage);

        // choosing numChar base on the syntax that has the most character.
        ArrayList<String> SyntaxMessageArray = message.getSyntaxinMessage(toStringMessage, 5);
        ArrayList<String> SyntaxMessageArray_Test = message.getDiffSyntaxMessage();




        System.out.println(dateArray);
        System.out.println(SyntaxMessageArray);
        System.out.println(SyntaxMessageArray_Test);
        // checkValidDate(toStringMessage, dateArray, HeaderArray);







    }

}
