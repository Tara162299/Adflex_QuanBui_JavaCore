package week1.chapter_3;

class Car {
    String name;
    int price;

    public Car(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Car(String name) {
        this.name = name;
    }

    int getPrice() {
        return price;
    }
}

