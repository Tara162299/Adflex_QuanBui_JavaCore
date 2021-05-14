package chapter_3;
public class Array {

    public static void main(String[] args) {
        Car [] cars = new Car[3];

        cars[0] = new Car("Ducati");
        cars[1] = new Car("StreetFighter V4", 650);
        cars[2] = new Car("Monster", 550);

        System.out.println(cars[2].getPrice());
        System.out.println(cars[1].getPrice());

    }

    static class Car {
        String name;
        int price;
        public Car (String name, int price) {
            this.name = name;
            this.price = price;
        }
        public Car (String name) {
            this.name = name;
        }

        int getPrice() {
            return price;
        }
    }
}
