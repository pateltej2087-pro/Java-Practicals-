import java.util.Scanner;
public class MiniBank{
    record BankInfo(String name, String branch){}
    enum MenuOption {
        OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BankInfo bankInfo=new BankInfo("MiniBank", "Petlad Branch");
        System.out.println("=================================");
        System.out.println("      " + bankInfo.name());
        System.out.println("      Branch: " + bankInfo.branch());
        System.out.println("=================================");
        while(true){
            System.out.println("\n------ Main Menu ------");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Open Account - To be implemented in a later lab.");
                    break;
                case 2:
                    System.out.println("Deposit - To be implemented in a later lab.");
                    break;
                case 3:
                    System.out.println("Withdraw - To be implemented in a later lab.");
                    break;
                case 4:
                    System.out.println("Transfer - To be implemented in a later lab.");
                    break;
                case 5:
                    System.out.println("Thank you for using MiniBank.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            if(choice == 5)
                break;
        }
        sc.close();
    }
}