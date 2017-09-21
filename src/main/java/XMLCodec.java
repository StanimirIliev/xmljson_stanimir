import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;


public class XMLCodec implements MessageCodec {

    @Override
    public String encode(Object value) {
        XStream xstream = new XStream();
        return xstream.toXML(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T decode(Type type, InputStream source) throws IOException {
        XStream xstream = new XStream();
        return (T) xstream.fromXML(source);
    }
}
