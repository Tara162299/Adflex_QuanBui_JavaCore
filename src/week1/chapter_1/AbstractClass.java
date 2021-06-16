package week1.chapter_1;

public abstract class AbstractClass {

    // The first concrete subclass of an abstract class must implement all abstract
    //methods of the superclass.

    String type;

    public String display() {
        return type;
    }

    public abstract void isFast();

    public abstract void isExpensive();

}
