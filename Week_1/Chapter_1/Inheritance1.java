package Chapter_1;
public class Inheritance1 extends AbstractClass {
    // When a class extends an abstract class, all of the abstract methods must be implemented
    private String type;
    static int test = 0;
    public Inheritance1(String type) {
        this.type = type;
    }

    @Override
    public void isFast() {
        System.out.println(type + "is incredibly fast");
    }

    public String getType() {
        return type;
    }

    @Override
    public void isExpensive() {
        System.out.println(type + " is rather expensive");
    }

}
