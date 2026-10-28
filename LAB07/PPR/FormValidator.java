import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    @MaxLength(10)
    String username;

    @NotBlank
    @MaxLength(20)
    String password;

    SignupForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class FormValidator {
    static List<String> validate(Object object) throws Exception {
        List<String> errors = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String value = (String) field.get(object);

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (value == null || value.trim().isEmpty()) {
                    errors.add(field.getName() + " cannot be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                int limit = field.getAnnotation(MaxLength.class).value();

                if (value != null && value.length() > limit) {
                    errors.add(field.getName() + " maximum length is " + limit);
                }
            }
        }

        return errors;
    }

    public static void main(String[] args) throws Exception {
        SignupForm form = new SignupForm("", "123456789012345678901");

        List<String> errors = validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}