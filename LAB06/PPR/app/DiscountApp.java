package app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import rules.Discount;

public class DiscountApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> prices = Arrays.asList(100.0, 200.0, 500.0);

        Discount noDiscount = price -> price;
        Discount tenPercent = price -> price * 0.90;
        Discount twentyPercent = price -> price * 0.80;

        System.out.println("1. No discount");
        System.out.println("2. 10% discount");
        System.out.println("3. 20% discount");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        Discount selected;

        switch (choice) {
            case 1:
                selected = noDiscount;
                break;

            case 2:
                selected = tenPercent;
                break;

            case 3:
                selected = twentyPercent;
                break;

            default:
                System.out.println("Invalid choice");
                sc.close();
                return;
        }

        for (double price : prices) {
            System.out.printf(
                    "Original: %.2f, Final: %.2f%n",
                    price, selected.apply(price)
            );
        }

        sc.close();
    }
}