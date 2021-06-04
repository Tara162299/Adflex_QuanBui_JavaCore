package week_2.project_2.collection1;

import java.io.File;
import java.util.ArrayList;


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

        ArrayList<String> HeaderMessageArray = message.getStructinMessage(toStringMessage, 4);

        //System.out.println(HeaderStructArray);
        System.out.println(HeaderMessageArray);
       // checkValidDate(toStringMessage, dateArray, HeaderArray);







    }

}
