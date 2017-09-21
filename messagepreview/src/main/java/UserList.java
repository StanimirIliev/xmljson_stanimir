import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserList {

    List<User> users;
    public final int averageAge;
    public final int count;

    public UserList(List<User> users) {
        this.users = users;
        int[] ages = new int[users.size()];
        for(int i = 0; i < ages.length; i++){
            ages[i] = users.get(i).age;
        }
        int temp = 0;
        for(int age : ages){
            temp += age;
        }
        averageAge = temp / ages.length;
        count = users.size();
    }

    public UserList(User... users){
        this.users = Arrays.asList(users);
        int[] ages = new int[users.length];
        for(int i = 0; i < ages.length; i++){
            ages[i] = users[i].age;
        }
        int temp = 0;
        for(int age : ages){
            temp += age;
        }
        averageAge = temp / ages.length;
        count = users.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserList userList = (UserList) o;
        return averageAge == userList.averageAge &&
                count == userList.count &&
                Objects.equals(users, userList.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, averageAge, count);
    }
}
