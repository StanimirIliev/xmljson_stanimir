import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class XMLCodecTest {


    @Test
    public void decode(){
        String content = "<User>\n" +
                "  <firstName>John</firstName>\n" +
                "  <lastName>Whick</lastName>\n" +
                "  <age>30</age>\n" +
                "</User>";
        try {
            User john = new XMLCodec().decode(User.class, new ByteArrayInputStream(content.getBytes()));
            assertEquals(john.firstName, "John");
            assertEquals(john.lastName, "Whick");
            assertEquals(john.age, 30);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void encode(){
        assertEquals("<User>\n" +
                "  <firstName>John</firstName>\n" +
                "  <lastName>Whick</lastName>\n" +
                "  <age>30</age>\n" +
                "</User>", new XMLCodec().encode(new User("John", "Whick", 30)));
    }


    @Test
    public void bigData(){
        String[] tasks = new String[1000000];
        for(int i = 0; i < 1000000; i++){
            tasks[i] = "test";
        }
        Notes notes = new Notes(tasks);
        XMLCodec xml = new XMLCodec();
        try {
            Notes notes1 = xml.decode(Notes.class, new ByteArrayInputStream(xml.encode(notes).getBytes()));
            assertEquals(notes.notes.toArray(), notes1.notes.toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullValuesDecode(){
        String content = "<User>\n" +
                "  <firstName>John</firstName>\n" +
                "  <age>30</age>\n" +
                "</User>";
        User john = null;
        try {
            john = new XMLCodec().decode(User.class, new ByteArrayInputStream(content.getBytes()));
            assertEquals(null, john.lastName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullValuesEncode(){
        User john = new User("John", null, 30);
        assertEquals("<User>\n" +
                "  <firstName>John</firstName>\n" +
                "  <age>30</age>\n" +
                "</User>", new XMLCodec().encode(john));
    }
}
