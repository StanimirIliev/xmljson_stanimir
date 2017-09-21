import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Notes {
    public List<String> notes;

    public Notes(String... notes) {
        this.notes = Arrays.asList(notes);
    }
    public Notes(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes1 = (Notes) o;
        return Objects.equals(notes, notes1.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes);
    }
}
