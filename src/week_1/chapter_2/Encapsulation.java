package week_1.chapter_2;
public class Encapsulation implements Interface{
    // Implementing Interface => either abstract class or implement all abstract methods in interface, and must be public
    // One can have many constructor with different inputs
    private String type;
    private int price;
    private boolean worthIt;
    public Encapsulation(String type, int price, boolean worthIt) {
        this.type = type;
        this.price = price;
        this.worthIt = worthIt;
    }

    public Encapsulation(String type, int price) {
        this.type = type;
        this.price = price;
    }
    public Encapsulation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }

    public void isWorthIt() {
        if ((worthIt)) {
            System.out.println("You have to buy it");
        } else {
            System.out.println("Do not buy it");
        }
    }

    protected void Print() {
        System.out.println("Upcasting is successful");
    }

    @Override
    public void Type() {
        System.out.println(type);
    }

    @Override
    public void Price() {
        System.out.println(price);
    }

    @Override
    public void Color() {
        System.out.println("There is no color");
    }
}
