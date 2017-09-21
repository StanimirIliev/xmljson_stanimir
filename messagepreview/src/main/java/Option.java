import java.util.Arrays;

public class Option{

    private final String[] args;

    public Option(String... args) {
        this.args = args;
    }

    public boolean parse(){
        if(args.length == 4){
            if(args[1].equals("json") && args[2].endsWith(".json") ||
                    args[1].equals("xml") && args[2].endsWith(".xml")){
                if(args[3].equals("--printAverageStats")){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Arrays.equals(args, option.args);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(args);
    }

    @Override
    public String toString() {
        return Arrays.asList(args).toString();
    }
}