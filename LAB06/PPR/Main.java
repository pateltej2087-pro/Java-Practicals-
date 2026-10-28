interface Switchable{
    void on();
    void off();
    default void toggle(){
        on();
        off();
    }
}
class Fan implements Switchable{
    public void on(){
        System.out.println("Fan ON");
    }
    public void off(){
        System.out.println("Fan OFF");
    }
}
class Light implements Switchable{
    public void on(){
        System.out.println("Light ON");
    }
    public void off(){
        System.out.println("Light OFF");
    }
}
interface Permission{
    boolean allow(Switchable device,int hour);
}
public class Main{
    public static void main(String[] args){
        Switchable[] devices={new Fan(),new Light()};
        for (Switchable device:devices){
            device.toggle();
        }
        Permission p1=new Permission(){
            public boolean allow(Switchable device, int hour) {
                return hour<22;
            }
        };
        Permission p2=(device,hour)->hour<22;
        System.out.println(p1.allow(devices[0],10));
        System.out.println(p2.allow(devices[1],23));
    }
}