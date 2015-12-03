package controllers;


import com.squareup.okhttp.*;
import play.mvc.Controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Object;


/**
 * Created by Miage on 03/12/2015.
 */

public class Client extends Controller  {

    public static String URLToSendToSearchMethod ="https://api.cdiscount.com/OpenApi/json/Search";
    static List<Map<String,String>> events = new ArrayList<Map<String,String>>();
    static List<Product> products = new ArrayList<Product>();

    private static String createJsonForSearch(String keyWord, String sortBy){
        return "{\r\n  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\r\n  \"SearchRequest\": {\r\n    \"Keyword\": \"" + keyWord + "\",\r\n    \"SortBy\": \"" + sortBy + "\",\r\n    \"Pagination\": {\r\n      \"ItemsPerPage\": 1\r\n    },\r\n    \"Filters\": {\r\n      \"Price\": {\r\n        \"Min\": 0\r\n      },\r\n      \"IncludeMarketPlace\": true\r\n    }\r\n  }\r\n}";
    }

    public static String searchProduct(String keyword, String sortBy)
    {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, createJsonForSearch(keyword, sortBy));
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
            System.out.println("an error occur");
            return "erreur";
        }
    }


    //region JsonStringToListView

    private static void initList(String jsonString){

        try{
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Products");

            for(int i = 0; i<jsonMainNode.length();i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                JSONObject jsonChildNode2 = (JSONObject) jsonChildNode.get("BestOffer");
                JSONObject idProduct = (JSONObject) jsonChildNode.get("Id");
                JSONObject descriptionProduct = (JSONObject) jsonChildNode.get("Description");

                //jsonChildNode.get("Id");
                System.out.println(jsonChildNode2.get("Id"));
                //String id = jsonChildNode.optString("BestOffer");
                /*for(int j = 0; j<jsonChildNode.length();j++){
                    JSONObject jsonChild2 = jsonChildNode.getJSONObject(j);
                    JSONObject jsonID = jsonChildNode.optJSONObject("Id");
                    System.out.println(jsonID);
                }
                events.add(createEvents("events", id));
*/
                //System.out.println(id);
                events.add(createEvents("product", (String) jsonChildNode2.get("Id")));
                Product product = new Product();
                product.setId((String) jsonChildNode2.get("Id"));
                products.add(product);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
            System.out.println("Erreur");
        }
    }

    private static HashMap<String, String>createEvents(String name, String number){
        HashMap<String, String> eventsNameNo = new HashMap<String, String>();
        eventsNameNo.put(name, number);
        return eventsNameNo;
    }

    //endregion JsonStringToListView


    public static void main(String args[]) {

        //System.out.println(createJsonForSearch("tablette", "minprice"));
        searchProduct("tablette","minprice");
        initList(searchProduct("tablette","minprice"));
    }


}
