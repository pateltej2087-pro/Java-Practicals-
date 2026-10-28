import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "student_name")
    String name;

    @Column(name = "student_age")
    int age;

    @Column(name = "student_city")
    String city;
}

public class ColumnMapper {
    static void populate(Object object, String[] headers, String[] data)
            throws Exception {

        if (headers.length != data.length) {
            throw new IllegalArgumentException("Row lengths must match");
        }

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                String columnName =
                        field.getAnnotation(Column.class).name();

                for (int i = 0; i < headers.length; i++) {
                    if (columnName.equals(headers[i])) {
                        field.setAccessible(true);

                        if (field.getType() == int.class) {
                            field.setInt(object, Integer.parseInt(data[i]));
                        } else if (field.getType() == String.class) {
                            field.set(object, data[i]);
                        }

                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String[] headers = {"student_age", "student_name"};
        String[] data = {"18", "Tej"};

        Student student = new Student();
        populate(student, headers, data);

        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
        System.out.println("City: " + student.city);
    }
}