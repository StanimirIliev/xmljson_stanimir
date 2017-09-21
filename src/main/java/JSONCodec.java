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
    public <T> T decode(Type type, InputStream source) throws IOException {
        Gson gson = new GsonBuilder().create();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(source));
        String line = buffReader.readLine();
        StringBuilder content = new StringBuilder();
        while(line != null){
            content.append(line);
            line = buffReader.readLine();
        }
        return gson.fromJson(content.toString(), type);
    }
}
