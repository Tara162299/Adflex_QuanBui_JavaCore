package Chapter_3;
public class Array {

    public static void main(String[] args) {
        Car[] cars = new Car[3];

        cars[0] = new Car("Ducati");
        cars[1] = new Car("StreetFighter V4", 650);
        cars[2] = new Car("Monster", 550);

        System.out.println(cars[2].getPrice());
        System.out.println(cars[1].getPrice());

    }
}
