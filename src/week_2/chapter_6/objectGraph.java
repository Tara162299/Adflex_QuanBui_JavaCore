package week_2.chapter_6;

import java.io.*;

public class objectGraph {

    public static void main(String[] args) {
        //determine which objects are reachable and which not,
        //all unreachable objects could be made eligible for garbage collection

        Dog dog = new Dog(35, "Fido");
        System.out.println("before: " + dog.name + " "
                + dog.weight);

        try {
            FileOutputStream fos = new FileOutputStream("testSer1.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dog);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream("testSer1.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            dog = (Dog) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("after:  " + dog.name + " "
                + dog.weight);
    }
}

class Dog extends Animal implements Serializable {
    String name;
    //int weight;

    Dog(int w, String name) {
        this.weight = w;                    // inherited from class Animal
        this.name = name;                   // not inherited from class Animal
    }
}

class Animal {                               // not serializable!
    int weight = 42;
    String name = "Kit";
}

//Animal is not serializable,
// so when the Dog was deserialized, the Animal constructor ran and reset the Dog initial weight variable.
// The string "name" was declared in dog class so it keep the value before serialization