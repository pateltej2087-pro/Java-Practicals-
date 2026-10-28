abstract class Shape{
    public abstract double area();
}
class Circle extends Shape{
    double radius;
    Circle(double radius){
        this.radius = radius;
    }
        public double area(){
            return 3.14*radius*radius;
        }
}
class Rectangle extends Shape{
    double length;
    double breadth;
    Rectangle(double length,double breadth){
        this.length = length;
        this.breadth = breadth;
    }
        public double area(){
            return length*breadth;
        }
}
class Triangle extends Shape{
    double base;
    double height;
    Triangle(double base,double height){
        this.base = base;
        this.height = height;
    }
        public double area(){
            return 0.5*base*height;
        }
}
public class Demo{
    public static void main(String args[]){
    Shape s1[]={
        new Circle(10.0),
        new Rectangle(10.0,20.0),
        new Triangle(10.0,20.0),
        new Circle(20.0)
    };
    double largearea=0.0;
    double runningarea=0.0;
    String largestarea=null;
    double calcarea=0.0;
    for(int i=0;i<s1.length;i++){
        calcarea=s1[i].area();
        System.out.println("Area of "+s1[i].getClass().getSimpleName()+" is: "+calcarea);
        runningarea+=calcarea;
        if(calcarea>largearea){
            largearea=calcarea;
            largestarea=s1[i].getClass().getSimpleName();
        }
    }
    System.out.println("Total Area of all Shapes: "+runningarea);
    System.out.println("Largest Area: "+largearea);
    System.out.println("Shape with Largest Area: "+largestarea);   
}
}
