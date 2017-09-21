import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JSONCodecTest {

    @Test
    public void decode(){
        try {
            JSONCodec json = new JSONCodec();
            Message[] msg = json.decode(Message[].class, JSONCodecTest.class.getResourceAsStream("jsonFile.json"));
            assertThat(msg, is(equalTo(new Message[] {
                    new Message("089", "087", "Hi!"),
                    new Message("089", "087", "My name is.."),
                    new Message("087", "089", "What?")
            })));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void encode(){
        User john = new User("John", "Whick", 30);
        assertEquals("{\"firstName\":\"John\",\"lastName\":\"Whick\",\"age\":30}", new JSONCodec().encode(john));
    }

    @Test
    public void bigData(){
        JSONCodec json = new JSONCodec();
        String[] tasks = new String[1000000];
        for(int i = 0; i < 1000000; i++){
            tasks[i] = "test";
        }
        Notes notes = new Notes(tasks);
        String result = json.encode(notes);
        try {
            Notes notes1 = json.decode(Notes.class, new ByteArrayInputStream(result.getBytes()));
            assertEquals(notes.notes, notes1.notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullValuesDecode(){
        String content = "{\"firstName\":\"John\",\"age\":30}";
        try {
            User john = new JSONCodec().decode(User.class, new ByteArrayInputStream(content.getBytes()));
            assertEquals(null, john.lastName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullValuesEncode(){
        User john = new User("John", null, 30);
        assertEquals("{\"firstName\":\"John\",\"age\":30}", new JSONCodec().encode(john));
    }
}
