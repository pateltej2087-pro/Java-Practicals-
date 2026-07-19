class Customer {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private static long customerCounter = 101;
    private static String generateCustomerId(){
        return "CUST" + customerCounter++;
    }
    public Customer(String name,String email,String mobile){
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        this.customerId=generateCustomerId();
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getMobile(){
        return mobile;
    }
    public String getCustomerId(){
        return customerId;
    }
}
class Account{
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;
    private static int accountCounter=1;
    private static String generateAccountNumber(){
        return String.format("AC%04d", accountCounter++);
    }
    public Account(String ownerName,long openingBalance){
        this.accountNumber=generateAccountNumber();
        this.ownerName=ownerName;
        this.balance=openingBalance;
        this.active=true;
    }
    public Account(String ownerName){
        this(ownerName, 0);
    }
    public void deposit(long amount){
        if (amount>0) {
            balance+=amount;
        }
    }
    public boolean withdraw(long amount){
        if (amount>0 && amount<=balance){
            balance-=amount;
            return true;
        }
        return false;
    }
    public static void transfer(Account from, Account to, long amount){
        if (from.withdraw(amount)){
            to.deposit(amount);
            System.out.println("Transfer Successful");
        } 
        else{
            System.out.println("Transfer Failed");
        }
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getOwnerName(){
        return ownerName;
    }
    public long getBalance(){
        return balance;
    }
    public boolean isActive(){
        return active;
    }
}
public class MiniBank{
    record BankInfo(String name,String branch){}
    enum MenuOption {
        OPEN_ACCOUNT,DEPOSIT,WITHDRAW,TRANSFER,EXIT
    }
    public static void main(String[] args){
        BankInfo bankInfo = new BankInfo("MiniBank","Petlad Branch");
        System.out.println("==================================");
        System.out.println("        " + bankInfo.name());
        System.out.println("      " + bankInfo.branch());
        System.out.println("==================================");
        Customer c1=new Customer("Rahul", "rahul@gmail.com", "9876543210");
        Customer c2=new Customer("Priya", "priya@gmail.com", "9876501234");
        Customer c3=new Customer("Amit", "amit@gmail.com", "9999988888");
        Account[] accounts=new Account[3];
        accounts[0]=new Account(c1.getName(), 5000);
        accounts[1]=new Account(c2.getName());
        accounts[2]=new Account(c3.getName(), 10000);
        accounts[0].deposit(1000);
        accounts[1].deposit(3000);
        if (accounts[2].withdraw(2500))
            System.out.println("Withdrawal Successful");
        else
            System.out.println("Withdrawal Failed");
        if (accounts[1].withdraw(5000))
            System.out.println("Withdrawal Successful");
        else
            System.out.println("Withdrawal Failed");
        Account.transfer(accounts[0], accounts[1], 1500);
        System.out.println("\n========== Account Details ==========");
        for (Account account : accounts) {
            System.out.println("--------------------------------");
            System.out.println("Account Number : " + account.getAccountNumber());
            System.out.println("Owner Name     : " + account.getOwnerName());
            System.out.println("Balance        : " + account.getBalance());
            System.out.println("Active         : " + account.isActive());
        }
    }
}