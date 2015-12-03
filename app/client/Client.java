package client;

import com.squareup.okhttp.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miage on 03/12/2015.
 */

public class Client {

    public static String UPDATE_URL = "https://api.cdiscount.com/OpenApi/json/GetCart";

    public static String jsonToSend="{\n" +
            "  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\n" +
            "  \"CartRequest\": {}\n" +
            "}";

    public static void main(String args[]) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonToSend);
        Request request = new Request.Builder()
                .url(UPDATE_URL)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();

        try {

            Response response = client.newCall(request).execute();
            InputStream stream = response.body().byteStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
