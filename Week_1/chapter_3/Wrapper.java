package chapter_3;
public class Wrapper {
    public static void main(String[] args) {
        // local variables are on stack
        // instance variables and objects are on heap
        Integer a = new Integer(5);
        int b = 5;

        // a is an object, while b is just an instance variable
        if (a instanceof Object) {
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }

        // valueOf method
        Integer c = Integer.valueOf("101011", 2);
        System.out.println(c);

        Float d = Float.valueOf(16.22f);
        System.out.println(d);

        System.out.println("Y" + "O");
        // converted to int because of +
        System.out.println('L' + 'O');



    }
}
