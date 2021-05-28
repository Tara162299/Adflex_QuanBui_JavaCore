package week_2.chapter_7;

public class overrideToString {
    public static void main(String[] args) {

        Vehicle Ducati = new Vehicle("Ducati", 550);
        System.out.println(Ducati.toString());
    }
}

class Vehicle {
    String name;
    int price;

    public Vehicle(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return ("The toString() has been successfully override " + name + " " + price);
    }
}