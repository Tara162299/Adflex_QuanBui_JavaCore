package week_2.chapter_7;

public class overrideEquals {
    public static void main(String[] args) {
        // if equals method are not override, it will return unequal
        Moof one = new Moof(8);
        Moof two = new Moof(8);
        if (one.equals(two)) {
            System.out.println("one and two are equal");
        } else {
            System.out.println("one and two are unequal");
        }
    }
}

class Moof {
    private int moofValue;

    Moof(int val) {
        moofValue = val;
    }

    public int getMoofValue() {
        return moofValue;
    }

//    @Override
//    public boolean equals(Object o) {
//        if ((o instanceof Moof) && (((Moof) o).getMoofValue()
//                == this.moofValue)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
