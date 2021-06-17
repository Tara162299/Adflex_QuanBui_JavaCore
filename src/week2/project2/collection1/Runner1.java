package week2.project2.collection1;

import java.io.File;

public class Runner1 {
    public static void main(String[] args) {

        File messageInput = new File("Resource/Project2_Collection1/input/message.txt");
        Process1 message = new Process1(messageInput);
        message.process();
    }
}