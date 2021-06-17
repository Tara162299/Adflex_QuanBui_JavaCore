package week2.chapter6;

import java.io.*;

public class FileNavigationIO {
    public static void main(String[] args) throws NullPointerException {

        // Creating Files Using Class File
        try {
            File file = new File
                    ("fileWrite1.txt");

            System.out.println(file.exists());  // check if the file above exist

            boolean newFile = file.createNewFile();     // Create a file
            //true if the file path does not exist and a new file is created.
            //false if the filename already exists.

            System.out.println(newFile);        // already there?
            System.out.println(file.exists());  // check again
        } catch (IOException e) {
            System.out.println("The file does not exist");
        }

        System.out.println();


        // ____________________________________________________________________________________________
        // Using FileWriter and FileReader
        char[] charArray = new char[21];
        int size;
        try {
            File file = new File(
                    "Tara1622.txt");                        // create a new non-actual file
            FileWriter fw = new FileWriter(file);                    // create an actual file
            fw.write("This is a test!\nFolks\n");
            fw.flush();                                              // flush before closing
            fw.close();                                              // close file when done

            FileReader fr = new FileReader(file);                    // create a FileReader
            size = fr.read(charArray);                              // read the whole file!
            System.out.println("The size of the array is: " + size);
            for (char c : charArray)
                System.out.print(c);
            fr.close();                                      // close file when done
        } catch (IOException e) {
            System.out.println("There has been an error");
        }

    }
}

