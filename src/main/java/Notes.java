import java.util.Arrays;
import java.util.List;

public class Notes {
    public List<String> notes;

    public Notes(String... notes) {
        this.notes = Arrays.asList(notes);
    }
    public Notes(){}

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Notes notes = (Notes) obj;
        if(notes.notes.size() != this.notes.size()){
            return false;
        }
        for(int i = 0; i < this.notes.size(); i++){
            if(!this.notes.get(i).equals(notes.notes.get(i))){
                return false;
            }
        }
        return true;
    }
}
