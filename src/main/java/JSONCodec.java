import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class JSONCodec implements MessageCodec {
    @Override
    public String encode(Object value) {
        return new GsonBuilder().create().toJson(value, value.getClass());
    }

    @Override
    public <T> T decode(Type type, InputStream source){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(new InputStreamReader(source), type);
    }
}
