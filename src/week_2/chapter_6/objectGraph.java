import java.io.*;

public class objectGraph {

    public static void main(String[] args) {
        //is used to determine which objects are reachable and which not,
        // so that all unreachable objects could be made eligible for garbage collection

        Dog f = new Dog(35, "Fido");
        System.out.println("before: " + f.name + " "
                + f.weight);
        try {
            FileOutputStream fs = new FileOutputStream("testSer.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(f);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream("testSer.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            f = (Dog) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("after:  " + f.name + " "
                + f.weight);
    }
}

class Dog extends Animal implements Serializable {
    String name;
    //int weight;

    Dog(int w, String n) {
        weight = w;                 // inherited
        this.name = n;              // not inherited
    }
}

class Animal {                      // not serializable !
    int weight = 42;
    String name = "Kit";
}

// The key here is that because Animal is not serializable,
// when the Dog was deserialized, the Animal constructor ran and reset the Dog initial weight variable.
// The name in animal class was not inherited and not serializable => the original name was kept as it declared in Dog class