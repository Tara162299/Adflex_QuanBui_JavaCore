package week_1.chapter_2;
public class DownCasting extends Encapsulation implements Interface{
    String type;
    int price;

    public DownCasting(String type, int price) {
        super(type, price);
    }

    public String getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }

    void display() {
        System.out.println("Downcasting is successful");
    }

    void PrintCheck() {
        System.out.println("Checking");
    }
}
