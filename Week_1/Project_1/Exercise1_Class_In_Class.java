package Project_1;

import java.util.Scanner;

public class Exercise1_Class_In_Class {
    public static void main(String[] args) {
        // Given String:
        // Bai hat Em oi Ha Noi pho. Ta con em mui hoa sua. \n    Toi 	yeu ha	 noi pho \n    De im ngu ty 	con di  $ \n    Con sap ve 	chua? - Con chua ve   duoc, doi mo   mat!
        System.out.print("Please enter your string: ");
        Scanner sc = new Scanner(System.in);
        String given = sc.nextLine();

        Methods methodObject = new Methods(given);

        methodObject.printUpperCharacter(given);

        methodObject.printUpperCharacter(given);
        methodObject.countingCharacter(given);
        methodObject.convertCharToUpper(given);
        methodObject.removeWhiteSpace(given);

        String string = "Toi \tyeu ha\t noi pho";               // adding this string to the given
        System.out.println(methodObject.addString(given, string));

        methodObject.removeWhiteSpace(methodObject.addString(given, string));
        sc.close();
    }

    static class Methods {
        String string;

        public Methods (String string) {
            this.string = string;
        }

        void printUpperCharacter(String initialString) {
            for (int i = 0; i < initialString.length(); i++) {
                if (Character.isUpperCase(initialString.charAt(i))) {
                    System.out.print(initialString.charAt(i) + " ");
                }
            }
            System.out.println();
        }

        void countingCharacter(String initialString) {
            int countLowerChar = 0;
            int countAllChar = 0;

            for (int a = 0; a < initialString.length(); a++) {
                if (Character.isLowerCase(initialString.charAt(a))) {
                    countLowerChar++;
                }
                if (Character.isLowerCase(initialString.charAt(a)) || Character.isUpperCase(initialString.charAt(a))) {
                    countAllChar++;
                }
            }
            System.out.println("The number of all characters is: " + countAllChar);
            System.out.println("The number of lower case characters is: " + countLowerChar);
        }

        void convertCharToUpper(String initialString) {                 // convert the string s2 to upper case characters
            System.out.println(initialString.toUpperCase());
        }

        void removeWhiteSpace(String initialString) {
            System.out.println(initialString.trim().replaceAll("\\s+", " "));
        }

        String addString(String initialString, String addingString) {
            StringBuilder addedString = new StringBuilder();
            for (int a = 0; a < initialString.length(); a++) {
                if (initialString.charAt(a) != '$') {
                    addedString.append(initialString.charAt(a));
                } else {
                    addedString.append("$");
                    addedString.append(addingString);
                }
            }
            return addedString.toString();
        }
    }
}


