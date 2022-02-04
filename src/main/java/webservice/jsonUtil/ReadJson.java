package webservice.jsonUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

@Component
public class ReadJson {
    public JSONObject readJsonFromUrl(String link) throws JSONException {
        try (InputStream input = new URL(link).openStream()) {
            BufferedReader re = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            String text = Read(re);
            JSONObject json = new JSONObject(text);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String Read(Reader re) throws IOException {
        StringBuilder str = new StringBuilder();
        int temp;
        do {
            temp = re.read();
            str.append((char) temp);
        } while (temp != -1);

        return str.toString();
    }
}
