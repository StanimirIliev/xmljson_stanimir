import java.util.HashMap;
import java.util.Objects;

public class User {
    public  String firstName;
    public  String lastName;
    public  int age;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(){}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(age, user.age);
    }


    @Override
    public String toString() {
        return "First name: " + firstName + ", Last name: " + lastName + ", Age: " + age;
    }
}
