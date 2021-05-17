package week_1.chapter_1;
public class StartApp {
    // enum
        enum Type {DUCATI, BMW}
        Type type;


    public static void main(String[] args) {
        //ENUM
        StartApp type3  = new StartApp();
        type3.type = Type.DUCATI;

        StartApp type4 = new StartApp();
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
