import java.util.StringTokenizer;
import java.util.Scanner;

final class driver {
    /**
     * driver program for hw5.
     * @param args .
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = "";
        System.out.println("Welcome!");
        boolean keepgoing = true;
        while (keepgoing) {
            System.out.println("Enter a prefix expression:");
            str = input.nextLine();
            StringTokenizer token = new StringTokenizer(str);
            str = token.nextToken();
            TreeRoot<String> tree = new TreeRoot<String>(str);
            boolean validity = true;
            while (validity  && token.hasMoreTokens()) {

                str = token.nextToken();

                if (!tree.addChild(str)) {
                    validity = false;
                }

            }

            if (!tree.checkValidity()) {
                validity = false;
            }
            if (!validity) {
                System.out.println("ERROR: expression is not properly formed");
            }
            if (validity) {
                System.out.print("postfix version: ");
                tree.postOrder();
                System.out.println();
                System.out.print("infix version: ");
                tree.infix();
                System.out.println();
                System.out.println("value: " + tree.value());
            }
            System.out.println("Do you want to continue," + " type in "
                + "any button to conutiue, " + "if you want to quit,press 'n'");
            str = input.nextLine();
            if (str.equals("n")) {
                keepgoing = false;
            }
        }
        System.out.println("Thank you and have a nice day!");

    }

}

