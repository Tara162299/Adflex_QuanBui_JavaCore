package Chapter_3;
public class Chapter_4 {
    public static void main(String[] args) {
        Integer d = new Integer(5);

        // method: instance of
        Integer a = new Integer(5);
        if (a instanceof Object) {
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }

        // conditional operator:
        int b = (a == 5) ? 4 : 3;
        System.out.println(b);

        System.out.println(d instanceof Object);

        //boxing
        Integer number = new Integer(5);    // create an Integer object
        number++;                                 // unwrap, increment, re-wrap

        int i = 5;
        AddBoxing NEW = new AddBoxing();
        NEW.go(i);
    }

    static class AddBoxing {
        void go(Integer x) { System.out.println("Integer"); }
        void go(long x) { System.out.println("long"); }

                  // which go() will be invoked?
        }

}
