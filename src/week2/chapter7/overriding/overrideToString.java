package week2.chapter7.overriding;

public class overrideToString {
    public static void main(String[] args) {

        Override test = new Override(22, "Puppy");

        test.toString();
    }
}

class Override {
    int age;
    String name;

    public Override(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "My girlfriend nickname is: " + name + " and she is: " + age;
    }
}
