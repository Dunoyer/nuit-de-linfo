package controllers;


import com.squareup.okhttp.*;
import play.mvc.Controller;

import java.io.*;


/**
 * Created by Miage on 03/12/2015.
 */

public class Client extends Controller  {

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
