package controllers;


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
import java.lang.Object;
/**
 * Created by Miage on 03/12/2015.
 */

public class Client {

    public static String UPDATE_URL = "https://api.cdiscount.com/OpenApi/json/GetCart";

    public static String jsonToSend="{\n" +
            "  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\n" +
            "  \"CartRequest\": {}\n" +
            "}";

    public static String keyWORD ="tablette";

    public static String JSONToSendToSearchMethod = "{\r\n  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\r\n  \"SearchRequest\": {\r\n    \"Keyword\": \"" + keyWORD + "\",\r\n    \"SortBy\": \"minprice\",\r\n    \"Pagination\": {\r\n      \"ItemsPerPage\": 1\r\n    },\r\n    \"Filters\": {\r\n      \"Price\": {\r\n        \"Min\": 0\r\n      },\r\n      \"IncludeMarketPlace\": false\r\n    }\r\n  }\r\n}";

    public static String URLToSendToSearchMethod ="https://api.cdiscount.com/OpenApi/json/Search";

    public static void toto(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\r\n  \"SearchRequest\": {\r\n    \"Keyword\": \"" + keyWORD + "\" ,\r\n    \"SortBy\": \"minprice\",\r\n    \"Pagination\": {\r\n      \"ItemsPerPage\": 1\r\n    },\r\n    \"Filters\": {\r\n      \"Price\": {\r\n        \"Min\": 0\r\n      },\r\n      \"IncludeMarketPlace\": false\r\n    }\r\n  }\r\n}");
        Request request = new Request.Builder()
                .url("https://api.cdiscount.com/OpenApi/json/Search")
                .post(body)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "c296c810-8c68-fbba-2c8c-b169fd5f09a0")
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createJsonForSearch(String keyWord, String sortBy){
        return "{\r\n  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\r\n  \"SearchRequest\": {\r\n    \"Keyword\": \"" + keyWord + "\",\r\n    \"SortBy\": \" " + sortBy + "\",\r\n    \"Pagination\": {\r\n      \"ItemsPerPage\": 1\r\n    },\r\n    \"Filters\": {\r\n      \"Price\": {\r\n        \"Min\": 0\r\n      },\r\n      \"IncludeMarketPlace\": false\r\n    }\r\n  }\r\n}";
    }

    public String JSON;
    public static String searchProduct(String keyword, String sortBy)
    {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, createJsonForSearch(keyword, ""));
        Request request = new Request.Builder()
                .url(URLToSendToSearchMethod)
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
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "erreur";
        }
    }

    public static void main(String args[]) {

        System.out.println(createJsonForSearch("tablette", "minprice"));
        //searchProduct("tablette","");

        /*OkHttpClient client = new OkHttpClient();

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
        }*/
    }


}
