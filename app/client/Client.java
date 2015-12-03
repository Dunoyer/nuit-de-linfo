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
    public static HashMap<String,String> map = new HashMap<String,String>();

    public static String nom;
    public static String lieu;
    public static String date;

    private  String postToURL(DefaultHttpClient httpClient) throws IOException, IllegalStateException, UnsupportedEncodingException, RuntimeException {
        HttpPost postRequest = new HttpPost(UPDATE_URL);

        StringEntity input = new StringEntity(jsonToSend);
        input.setContentType("application/json");
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String output;
        StringBuffer totalOutput = new StringBuffer();
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            totalOutput.append(output);
        }
        return totalOutput.toString();
    }

    public static void main(String args[]) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\r\n  \"SearchRequest\": {\r\n    \"Keyword\": \"Television\",\r\n    \"SortBy\": \"rating\",\r\n    \"Pagination\": {\r\n      \"ItemsPerPage\": 5\r\n    },\r\n    \"Filters\": {\r\n      \"Price\": {\r\n        \"Min\": 0,\r\n        \"Max\": 500\r\n      },\r\n      \"IncludeMarketPlace\": true,\r\n      \"Brands\": [\r\n        \"Samsung\"\r\n      ],\r\n      \"Condition\": \"new\"\r\n    }\r\n  }\r\n}");
        Request request = new Request.Builder()
                .url("https://api.cdiscount.com/OpenApi/json/Search")
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

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public static void setParams(String nom, String lieu, String date) {
        map.put("nom", nom);
        map.put("lieu",lieu);
        map.put("date", date);
    }

}
