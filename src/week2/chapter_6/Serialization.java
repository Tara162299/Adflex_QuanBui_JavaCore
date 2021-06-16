package week2.chapter_6;
import java.io.*;

public class Serialization {
    public static void main(String[] args) {

        class Cat implements Serializable {        // Cat class implements the Serializable interface.
            String meow;
            int i;
            public Cat(String meow, int i) {
                this.meow = meow;
                this.i = i;
            }
            public int getInt() {
                return i;
            }
            public String getString() {
                return meow;
            }
        }

        Cat newObject = new Cat("Meow Meow", 12);        // new Cat object which is serializable
        try {
            FileOutputStream fos = new FileOutputStream("testSer.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newObject);                      // serialize the Cat object c by invoking the writeObject() method
            oos.close();
            System.out.println("Serialization is successful\n" + oos);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fis = new FileInputStream("testSer.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            newObject = (Cat) ois.readObject();     // de-serialize the Cat object by invoking the readObject() method
            // The readObject() method returns an Object, so we have to cast the deserialized object back to a Cat
            ois.close();
            System.out.println("De-serialization is successful\n" + ois);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

