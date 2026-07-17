import java.util.Scanner;
public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked=0;
    public CinemaShow(String title, int capacity){
        this.title=title;
        this.capacity=capacity;
        seatsAvailable=capacity;
    }
    public CinemaShow(String title){
        this(title,100);
    }
    public boolean book(int n){
        if(n<=seatsAvailable){
            seatsAvailable-=n;
            totalBooked+=n;
            return true;
        }
        else{
            return false;
        }
    }
         public void cancel(int n) {
            seatsAvailable+=n;
            if (seatsAvailable>capacity)
                seatsAvailable=capacity;
    }
    public int getSeatsAvailable() {
        return seatsAvailable;
    }
    public static int getTotalBooked() {
        return totalBooked;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Movie Name: ");
        String movie = sc.nextLine();
        System.out.print("Enter Total Seats: ");
        int seats = sc.nextInt();
        CinemaShow show = new CinemaShow(movie, seats);
        System.out.print("Enter Seats to Book: ");
        int bookSeats = sc.nextInt();
        if (show.book(bookSeats))
            System.out.println("Booking Successful");
        else
            System.out.println("Booking Failed");
        System.out.println("Seats Available: " + show.getSeatsAvailable());
        System.out.print("Enter Seats to Cancel: ");
        int cancelSeats = sc.nextInt();
        show.cancel(cancelSeats);
        System.out.println("Seats Available: " + show.getSeatsAvailable());
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());
        sc.close();
}
}