package week2.chapter5;

import java.util.*;

public class MyException {
    public static void main(String[] args) {
        String food1 = "Pizza";
        String food2 = "KFC";

        try {
            checkFood(food1);
            checkFood(food2);
        } catch (BadFoodException exp) {
            exp.printStackTrace();
        }
    }

    static void checkFood(String food) throws BadFoodException {
        List<String> lst = new ArrayList<String>();
        lst.add("Pizza");
        lst.add("Fish sauce");
        lst.add("Fried rice");

        for (int a = 0; a < lst.size(); a++) {
            if (food.equals(lst.get(a))) {
                throw new BadFoodException("This food sucks!");
            }
        }
    }
}
