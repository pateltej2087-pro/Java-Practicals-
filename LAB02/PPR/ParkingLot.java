import java.util.Scanner;

public class ParkingLot {
    private int twoWheelers;
    private int fourWheelers;
    private final int twoCap;
    private final int fourCap;
    private static long revenue=0;

    public ParkingLot(int twoCap, int fourCap){
        this.twoCap=twoCap;
        this.fourCap=fourCap;
    }

    public void park(String type){
        if(type.equals("two")){
            if(twoWheelers<twoCap){
                twoWheelers++;
                revenue+=20;
                System.out.print("Two wheeler parked successfully\n");
            }
            else{
                System.out.print("Two wheeter section full\n");
            }
        }
        else if(type.equals("four")){
            if(fourWheelers<fourCap){
                fourWheelers++;
                revenue+=40;
                System.out.print("Four wheeler parked successfully\n");
            }
            else{
                System.out.print("Four wheeler section full\n");
            }
        }
    }
    public void leave(String type){
        System.out.println("Leaving: " + type);
        if(type.equals("two")){
            if (twoWheelers>0){
                twoWheelers--;
            }
        }
        else if(type.equals("four")){
                if(fourWheelers>0){
                    fourWheelers--;
                }
            }
        }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Two Wheeler Capacity: ");
        int twoCapacity=sc.nextInt();
        System.out.print("Enter Four Wheeler Capacity: ");
        int fourCapacity=sc.nextInt();

        ParkingLot lot=new ParkingLot(twoCapacity, fourCapacity);

        lot.park("two");
        lot.park("two");
        lot.park("two");
        lot.park("four");
        lot.park("four");
        lot.park("four");
        lot.leave("two");
        lot.leave("four");

        System.out.println("\nFinal Status");
        System.out.println("Two Wheelers : " + lot.twoWheelers);
        System.out.println("Four Wheelers : " + lot.fourWheelers);
        System.out.println("Revenue :" + revenue);
        sc.close();
    }
}

