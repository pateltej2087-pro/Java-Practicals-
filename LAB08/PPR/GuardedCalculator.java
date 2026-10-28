import java.util.Scanner;

class DivideByZeroException extends Exception {
    DivideByZeroException() {
        super("Cannot divide by zero");
    }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean success = false;

        while (!success) {
            try {
                System.out.print("Enter first number: ");
                double a = Double.parseDouble(sc.nextLine());

                System.out.print("Enter second number: ");
                double b = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                String operator = sc.nextLine();

                double result;

                switch (operator) {
                    case "+":
                        result = a + b;
                        break;

                    case "-":
                        result = a - b;
                        break;

                    case "*":
                        result = a * b;
                        break;

                    case "/":
                        if (b == 0) {
                            throw new DivideByZeroException();
                        }
                        result = a / b;
                        break;

                    default:
                        System.out.println("Invalid operator");
                        continue;
                }

                System.out.println("Result: " + result);
                success = true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Enter a numeric value.");

            } catch (DivideByZeroException e) {
                System.out.println(e.getMessage());

            } finally {
                System.out.println("Attempt completed");
            }
        }

        sc.close();
    }
}