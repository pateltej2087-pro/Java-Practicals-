abstract class Employee{
    String name;
    int id;
    Employee(String name,int id){
        this.name=name;
        this.id=id;
    }
    public abstract double monthlySalary();
}
class FullTime extends Employee{
    double salary;
    FullTime(String name,int id,double salary){
        super(name,id);
        this.salary=salary;
    }

    public double monthlySalary(){
        return salary;
    }
}
class PartTime extends Employee{
    int hours;
    float rate;
    PartTime(String name,int id,int hours,float rate){
        super(name,id);
        this.hours=hours;
        this.rate=rate;
    }
    public double monthlySalary(){
        return hours*rate;
    }
}
class Intern extends Employee{
    double stipend;
    Intern(String name, int id,double stipend){
        super(name, id);
        this.stipend=stipend;
    }
    public double monthlySalary(){
        return stipend;
    }
}
public class Demo1{
    public static void main(String args[]){
        Employee e1[] ={
            new FullTime("Raj", 101, 50000),
            new PartTime("Amit", 102, 80, 200),
            new Intern("Neha", 103, 15000),
            new FullTime("Priya", 104, 60000)
        };
        double totalSalary=0.0;
        double calcSalary=0.0;
        for (int i=0;i<e1.length;i++) {
            calcSalary = e1[i].monthlySalary();
            System.out.println("Salary of " + e1[i].getClass().getSimpleName()+"("+e1[i].name + ")is: "+calcSalary
            );
            if(e1[i]instanceof Intern){
                System.out.println("Note:This employee is an Intern.");
            }
            totalSalary+=calcSalary;
        }
        System.out.println("Total Payroll: "+totalSalary);
    }
}