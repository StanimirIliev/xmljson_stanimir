import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JSONCodecTest {

    @Test
    public void decodeWhatWasEncoded(){
        JSONCodec json = new JSONCodec();
        Message[] msg = json.decode(Message[].class, JSONCodecTest.class.getResourceAsStream("jsonFile.json"));
        assertThat(msg, is(equalTo(new Message[] {
                new Message("089", "087", "Hi!"),
                new Message("089", "087", "My name is.."),
                new Message("087", "089", "What?")
        })));
    }

    @Test
    public void encode(){
        User john = new User("John", "Whick", 30);
        assertThat("{\"firstName\":\"John\",\"lastName\":\"Whick\",\"age\":30}",
                is(equalTo(new JSONCodec().encode(john))));
    }

    @Test
    public void encodeThenDecodeLargeDataSet(){
        JSONCodec json = new JSONCodec();
        String[] tasks = new String[1000000];
        for(int i = 0; i < 1000000; i++){
            tasks[i] = "test";
        }
        Notes notes = new Notes(tasks);
        String result = json.encode(notes);
        Notes notes1 = json.decode(Notes.class, new ByteArrayInputStream(result.getBytes()));
        assertThat(notes, is(equalTo(notes1)));
    }

    @Test
    public void nullValuesDecode(){
        String content = "{\"firstName\":\"John\",\"age\":30}";
        User john = new JSONCodec().decode(User.class, new ByteArrayInputStream(content.getBytes()));
        assertThat(null, is(equalTo(john.lastName)));
    }

    @Test
    public void nullValuesEncode(){
        User john = new User("John", null, 30);
        assertThat("{\"firstName\":\"John\",\"age\":30}", is(equalTo(new JSONCodec().encode(john))));
    }
}
