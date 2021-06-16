package week1.chapter_3;

public class Wrapper {
    public static void main(String[] args) {

        Integer a = new Integer(5);
        int b = 5;

        // method: instance of to indicate if a is an object
        if (a instanceof Object) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        // valueOf method
        Integer c = Integer.valueOf("101011", 2);
        System.out.println(c);

        Float d = Float.valueOf(16.22f);
        System.out.println(d);

        System.out.println("Y" + "O");
        System.out.println('L' + 'O');      // converted to int because of '+'


        // Conditional Operator:
        int conditionalOperator = (a == 5) ? 4 : 3;
        System.out.println(conditionalOperator);

        // Boxing
        Integer number = new Integer(5);    // create an Integer object
        number++;                                 // unwrap, increment, re-wrap

        int i = 5;
        AddBoxing NEW = new AddBoxing();
        NEW.go(i);
    }

    // Overloading
    static class AddBoxing {
        void go(Integer x) {
            System.out.println("Integer");
        }

        void go(long x) {
            System.out.println("long");
        }

        // which go() will be invoked?

    }
}
