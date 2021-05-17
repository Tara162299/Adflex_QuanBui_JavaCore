package week_2.chapter_5;
public class FlowControl_Exceptions_Assertions {
    public static void main(String[] args) {

        // Switch statement:
        int desiredCase = 6;
        final int CASE = 4;

        switch (desiredCase) {
            case 1:
                System.out.println("Case 1");
            case 2:
                System.out.println("Case 2");
            case 3:
                System.out.println("Case 3");
            case CASE:
                System.out.println("Case 4");
            case 5:
                System.out.println("Case 5");
            case 6:
                System.out.println("Case 6");
                break;              // if there is no break here, case 7 and default case will be printed too
            case 7:
                System.out.println("Case 7");
            default:
                System.out.println("Default");  // if no case match switch stm, default case will be printed out
        }
        System.out.println("");

        // labled statement:
        outer: for (int i = 0; i < 10; i++) {
            inner: for (int j = 10; j > 0; j--) {
                if (i == j) {
                    System.out.println(j);
                    break outer;
                }else{
                    System.out.println(i);
                    continue inner;
                }
            }
        }

        System.out.println();


        // handling exceptions using try-catch statement:
        try {
            Integer f = Integer.valueOf(null);
        } catch (NullPointerException exp1) {
            System.out.println("The integer is null");
        }
        catch (NumberFormatException exp2) {
            System.out.println("The integer is null");
            //throw exp2;                   // use to throw if can not handle the exception
            //exp2.printStackTrace();
        }
        finally {
            System.out.println("The finally statement will always be executed!");
        }

        System.out.println();

        // Assertion: an error and usually dont try and catch the error
        // assert expression;
        // assert expression1 : expression2; //expression 2 has to return a value
        
        int value = 15;
        assert value >= 20 : " Underweight";
        System.out.println("value is " + value);

        System.out.println();

        //Testing handling exceptions using throw
        Test testExpThrow = new Test();
        testExpThrow.throwExp();
    }

    static class Test {
        // use throw exception when there is no catch
        public void throwExp() throws NumberFormatException {
            //throw new NumberFormatException();
            try {
                Integer f = Integer.valueOf(null);
            } catch (NullPointerException|NumberFormatException exp3) {
                System.out.println("The integer is null");
                //throw exp3;
            } finally {
                System.out.println("The finally statement will always be executed!");
            }

        }
    }
}
