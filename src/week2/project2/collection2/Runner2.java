package week2.project2.collection2;

import java.io.File;

public class Runner2 {
    public static void main(String[] args) {

        File files = new File("Resource/Project2_Collection2/input");

        Process2 process = new Process2(files);
        process.process();
    }
}