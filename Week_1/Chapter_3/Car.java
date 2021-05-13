package Chapter_3;
public class Car {
    int price;
    String type;

    public Car (String type, int price) {
        this.price = price;
        this.type = type;
    }

    public Car (String type) {
        this.type = type;
    }


    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void display() {
        System.out.println("The name of the car is: " + type);
        System.out.println("The price of the car is: " + price);

    }
}
