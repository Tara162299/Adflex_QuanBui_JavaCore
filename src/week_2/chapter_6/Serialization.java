import java.io.*;

public class Serialization {
    public static void main(String[] args) {

        class Cat implements Serializable {             // declare that the Cat class implements the Serializable interface.
            final String mew;
            final int i;
            public Cat(String mew, int i) {
                this.mew = mew;
                this.i = i;
            }
        }

        Cat d = new Cat("Mew", 12);              // make a new Cat object, which is serializable
        try {
            FileOutputStream fs = new FileOutputStream("testSer.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(d);          // serialize the Cat object c by invoking the writeObject() method
            os.close();
            System.out.println("Serialization is successful");
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            FileInputStream fis = new FileInputStream("testSer.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            d = (Cat) ois.readObject(); // de-serialize the Cat object by invoking the readObject() method
            // The readObject() method returns an Object, so we have to cast the deserialized object back to a Cat
            ois.close();
            System.out.println("De-serialization is successful");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

