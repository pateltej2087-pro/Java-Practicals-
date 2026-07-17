public class Thermostat {
    private String location;
    private int temperature;
    private static final int min=16;
    private static final int max=30;
    private static int activeCount=0;
    
    public Thermostat(String location, int startTemp){
        this.location=location;
        if(startTemp>=min && startTemp<=max){
            temperature=startTemp;
        }
        else{
            temperature=22;
        }
    activeCount++;
    }
    public Thermostat(String location){
        this(location,22);
    }
    public void raise(){
        if(temperature<max){
            temperature++;
        }
        else{
            System.out.println("ALready at maximum(30)");
        }
    }
    public void lower(){
        if(temperature>min){
            temperature--;
        }
        else{
            System.out.println("Already at minimun(16)");
        }
    }
    public int getTemperature(){
        return temperature;
    }
    public static int getCount(){
        return activeCount;
    }
    public static void main(String[] args){
        Thermostat t1=new Thermostat("Kitchen", 25);
        Thermostat t2=new Thermostat("Living Room");
        System.out.println("Increasing Temperature");
        for (int i=0;i<10;i++) {
            t1.raise();
            System.out.println(t1.getTemperature());
        }
        System.out.println("\nDecreasing Temperature");
        for (int i=0;i<20;i++) {
            t1.lower();
            System.out.println(t1.getTemperature());
        }
        System.out.println("\nActive Thermostats=" + Thermostat.getCount());
    }
}

