import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class XMLCodecTest {


    @Test
    public void decode(){
        String content = "<User>\n" +
                "  <firstName>John</firstName>\n" +
                "  <lastName>Whick</lastName>\n" +
                "  <age>30</age>\n" +
                "</User>";
        User john = new XMLCodec().decode(User.class, new ByteArrayInputStream(content.getBytes()));
        assertThat("John", is(equalTo(john.firstName)));
        assertThat("Whick", is(equalTo(john.lastName)));
        assertThat(30, is(equalTo(john.age)));
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
    public void encodeThenDecodeLargeDataSet(){
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 1000000; i++){
            users.add(new User("a", "b", 1));
        }
        XMLCodec xml = new XMLCodec();
        List<User> users1 = xml.decode(List.class, new ByteArrayInputStream(xml.encode(users).getBytes()));
        assertThat(users, is(equalTo(users1)));
    }

    @Test
    public void nullValuesDecode(){
        String content = "<User>\n" +
                "  <firstName>John</firstName>\n" +
                "  <age>30</age>\n" +
                "</User>";
        User john = null;
        john = new XMLCodec().decode(User.class, new ByteArrayInputStream(content.getBytes()));
        assertEquals(null, john.lastName);
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
