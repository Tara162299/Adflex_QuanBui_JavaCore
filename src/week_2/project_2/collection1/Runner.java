package week_2.project_2.collection1;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {

        File messageInput = new File("Resource/Project2_Collection1/input/message.txt");
        helperMethods message = new helperMethods(messageInput);

        // change message file input to String
        List<String> toStringMessage = message.changeToString();
        // extract all the date of the String message to a list (checked for future date)
        List<String> dateArray = message.originalDateList(toStringMessage);

        // choosing numChar base on the syntax that has the most character.
        List<List<String>> SyntaxMessageList = message.getSyntaxinMessage(toStringMessage, 5);
        message.getDiffSyntaxMessage();


        // map each syntax with its number of valid appearances
        message.countAppearanceEachSyntax();

        message.getValidSyntax();

        Map<List<String>, List<Date>> syntaxDateMap = message.mapSyntaxDates();

        for (List<String> syntaxChecking : syntaxDateMap.keySet()) {
            // creating new output file of the valid syntaxs
            StringBuilder strbul = new StringBuilder();

            for (String str : syntaxChecking) {
                strbul.append(str);
            }

            // check and write valid message to the corresponding output file

            for (List<String> messageSyntaxChecking : SyntaxMessageList) {

                // checking if the syntax of mapSyntaxDates is the same as original syntax list
                if (syntaxChecking.equals(messageSyntaxChecking)) {
                    List<Date> dateChecking = syntaxDateMap.get(syntaxChecking);
                    int indexSyntaxMessage = SyntaxMessageList.indexOf(messageSyntaxChecking);

                    for (int i = 0; i < dateChecking.size(); i++) {
                        // if the date from original date list = date from map
                        if (dateArray.get(indexSyntaxMessage).equals(message.getDateToString(dateChecking).get(i))) {

                            String messageChosen = toStringMessage.get(indexSyntaxMessage);
                            try {
                                FileWriter fw = new FileWriter("Resource/Project2_Collection1/output/" + strbul + ".txt");
                                fw.write(messageChosen);
                                fw.flush();
                                fw.close();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}