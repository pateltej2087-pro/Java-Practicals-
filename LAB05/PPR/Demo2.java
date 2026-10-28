abstract class Media{
    String title;
    Media(String title) {
        this.title=title;
    }
    public abstract double lateFee(int daysLate);
}
class Book extends Media{
    Book(String title){
        super(title);
    }
    public double lateFee(int daysLate){
        return daysLate*2;
    }
}
class DVD extends Media{
    DVD(String title){
        super(title);
    }
    public double lateFee(int daysLate){
        return daysLate*5;
    }
}
class Magazine extends Media{
    Magazine(String title){
        super(title);
    }
    public double lateFee(int daysLate){
        return daysLate*1;
    }
}
public class Demo2{
    public static void main(String args[]){
        Media m1[]={
            new Book("Java Programming"),
            new DVD("Avengers"),
            new Magazine("Technology Today"),
            new Book("Data Structures")
        };
        int daysLate[] ={3,2,5,4};
        double totalFee=0.0;
        double calcFee=0.0;
        for (int i=0;i<m1.length;i++){
            calcFee = m1[i].lateFee(daysLate[i]);
            System.out.println(
                "Late Fee of "+
                m1[i].getClass().getSimpleName() +
                " (" + m1[i].title + ") is: " +
                calcFee
            );
            totalFee += calcFee;
        }
        System.out.println("Total Late Fee:" + totalFee);
    }
}