package chapter_1;
public class Main {
    // enum
        enum Type {DUCATI, BMW}
        Type type;


    public static void main(String[] args) {
        //ENUM
        Main type3  = new Main();
        type3.type = Type.DUCATI;

        Main type4 = new Main();
        type4.type = Type.BMW;

        Inheritance1 type1 = new Inheritance1("Ducati");
        Inheritance2 type2 = new Inheritance2("BMW");

        System.out.println(type1.getType());
        System.out.println(type2.getType());

       type1.isExpensive();
       type2.isExpensive();
       System.out.print(type3.type);


    }
}
