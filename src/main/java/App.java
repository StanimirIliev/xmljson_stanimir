public class App {

    static class User {

        private String usernmae;

        public User(String usernmae) {
            this.usernmae = usernmae;
        }
    }

    public static void main(String[] args) {
        String a = "abc";
        String b = new String("abc");
        System.out.println(a == b);
    }
}
