import java.io.*;

public class FileNavigationIO {
    public static void main(String[] args) throws NullPointerException{

        // Creating Files Using Class File
        try {                                   // warning: exceptions possible
            File file = new File
                    ("fileWrite1.txt");

            System.out.println(file.exists());  // check if the file above exist

            boolean newFile = file.createNewFile();     // Create a file
            //The function returns true if the abstract file path does not exist and a new file is created.
            // It returns false if the filename already exists.

            System.out.println(newFile);        // already there?
            System.out.println(file.exists());  // check again
        } catch (IOException e) {
            System.out.println("The file does not exist");
        }


        // ____________________________________________________________________________________________
        // Using FileWriter and FileReader
        char[] charArray = new char[50];
        int size;
        try {
            File file = new File(
                    "Tara1622.txt");                // create a new non-actual file
            FileWriter fw = new FileWriter(file);                    // create an actual file
            fw.write("howdy\nfolks\n");
            fw.flush();                                      // flush before closing
            fw.close();                                      // close file when done
            FileReader fr = new FileReader(file);                    // create a FileReader
            size = fr.read(charArray);                              // read the whole file!
            System.out.println(size + " ");
            for (char c : charArray)
                System.out.print(c);
            fr.close();                                      // close file when done
        } catch (IOException e) {
            System.out.println("There has been an error");
        }


        // ____________________________________________________________________________________________
        // The java.io.Console Class
        // Used to get input from console. It provides methods to read texts and passwords.
        Console c = System.console();
        System.out.println("Enter password: ");
        String n = c.readLine();
        System.out.println(n);



    }
}

