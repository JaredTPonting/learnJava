import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the command line calculator");
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter an operator (+ - * /): ");
        char operator = scanner.next().charAt(0);
        System.out.print("Enter the second number: ");

        double num2 = scanner.nextDouble();

        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2==0) {
                    System.out.println("Error: Division by Zero is not allowed");
                    scanner.close();
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("Error: invalid operator.");
                scanner.close();
                return;
        }
        System.out.println("Result: " +result);
        scanner.close();
    }
}
