package week2.chapter7.overriding;

public class overrideEquals {
    public static void main(String[] args) {
        // if equals method are not override, it will return unequal
        Test one = new Test(8);
        Test two = new Test(8);
        Test three = new Test(9);
        Integer four = new Integer(8);
        Object five = new Object();
        five = 8;

        if (one.equals(two)) {
            System.out.println("one and two are equal");
        } else {
            System.out.println("one and two are unequal");
        }


        try {
            System.out.println(one.equals(three));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(one.equals(five));
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (one.equals(four)) {
            System.out.println("one and four are equal");
        } else {
            System.out.println("one and four are unequal");
        }

    }
}


class Test {
    private int testValue;

    Test(int val) {
        this.testValue = val;
    }

    public int getTestValue() {
        return testValue;
    }

    public boolean equals(Object o) {
        if ((o instanceof Test) && (((Test) o).getTestValue()   // if one and two are the same type of object and have the same value
                == this.testValue)) {
            return true;
        } else {
            return false;
        }
    }
}
