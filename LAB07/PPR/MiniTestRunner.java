import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {
    @Run
    public void testOne() {
        System.out.println("Test one executed");
    }

    @Run
    public void testTwo() {
        System.out.println("Test two executed");
    }

    public void normalMethod() {
        System.out.println("Normal method executed");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) throws Exception {
        MyTests tests = new MyTests();
        int count = 0;

        for (Method method : tests.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {
                method.setAccessible(true);
                count++;

                try {
                    method.invoke(tests);
                } catch (InvocationTargetException e) {
                    System.out.println(method.getName() + " failed");
                }
            }
        }

        System.out.println("Total methods ran: " + count);
    }
}