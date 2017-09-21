import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public interface MessageCodec {
    String encode(Object value);
    <T> T decode(Type type, InputStream source) throws IOException;
}
