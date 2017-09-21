import java.io.FileInputStream;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        Option arguments = new Option(args);
        if(!arguments.parse()){
            System.out.println("Example usage: java -jar messagepreview.jar" +
                    " -type json message.json --printAverageStats");
        }
        else{
            MessageCodec codec;
            if(args[1].equals("json")){
                codec = new JSONCodec();
            }
            else{
                codec = new XMLCodec();
            }
            try {
                User[] usersArray = codec.decode(User[].class, new FileInputStream(args[2]));
                UserList users = new UserList(usersArray);
                System.out.println("User count: " + users.count);
                System.out.println("Average age: " + users.averageAge);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

