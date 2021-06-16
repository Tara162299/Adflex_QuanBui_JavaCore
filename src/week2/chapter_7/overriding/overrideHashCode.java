package week2.chapter_7.overriding;

public class overrideHashCode {
    public static void main(String[] args) {
        hashCode one = new hashCode(15);
        hashCode two = new hashCode(15);
        Object three = new Object();
        three = 15;

        System.out.println(one.equals(two));
        System.out.println();

        try {
            System.out.println(three.equals(one));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        System.out.println(three instanceof hashCode);
        System.out.println();


        // ClassCastException.
        // if three is not an instance of hashCode => ClassCastException
        try {
            System.out.println(one.equals(three));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class hashCode {
    public int x;

    public hashCode(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    //This equals() method says two objects are equal if they have the same x value,
    // so objects with the same x value will have to return identical hashcodes.

    public boolean equals(Object o) {
        if (((hashCode) o).getX() == this.x && (o instanceof hashCode)) { // this line return exception as the second condition is not executed first
            // => ClassCastException
            return true;
        } else {
            return false;
        }
    }

}

