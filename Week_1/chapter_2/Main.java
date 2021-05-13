package chapter_2;
public class Main {
    public static void main(String[] args) {
        Encapsulation obj1 = new Encapsulation("Ducati", 650, true);
        Encapsulation obj2 = new Encapsulation("BMW", 350, false);

        System.out.println(obj1.getPrice());
        System.out.println(obj1.getType());
        obj1.Type();

        obj2.isWorthIt();

        //Downcasting
        Encapsulation objDown = new DownCasting("Monster", 420);
        DownCasting down = (DownCasting) objDown;
        ((DownCasting) objDown).display();                       //Downcasting
        objDown.isWorthIt();
        down.isWorthIt();

        ((DownCasting) objDown).PrintCheck();


        //Upcasting
        UpCasting Up = new UpCasting("StreetFighter V4");
        UpCasting Up1 = new UpCasting("StreetFighter V4 S");
        Encapsulation objUp = Up;                               //upcast ok with no explicit cast
        Encapsulation objUp1 = (Encapsulation) Up1;             //upcast ok with an explicit cast

        Up.Print();

    }
}
