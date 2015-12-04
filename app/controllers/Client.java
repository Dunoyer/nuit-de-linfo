package controllers;


import com.squareup.okhttp.*;
import models.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.produits;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Miage on 03/12/2015.
 */

public class Client extends Controller  {

    public static String URLToSendToSearchMethod ="https://api.cdiscount.com/OpenApi/json/Search";
    public static String URLToSendToGetProduct ="https://api.cdiscount.com/OpenApi/json/GetProduct";
    static List<Map<String,String>> events = new ArrayList<Map<String,String>>();
    static List<Product> products = new ArrayList<Product>();

    private static String createJsonForSearch(String keyWord, String sortBy){
        return "{\r\n  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\r\n  \"SearchRequest\": {\r\n    \"Keyword\": \"" + keyWord + "\",\r\n    \"SortBy\": \"" + sortBy + "\",\r\n    \"Pagination\": {\r\n      \"ItemsPerPage\": 1\r\n    },\r\n    \"Filters\": {\r\n      \"Price\": {\r\n        \"Min\": 0\r\n      },\r\n      \"IncludeMarketPlace\": true\r\n    }\r\n  }\r\n}";
    }

    private static String createJsonForGetProduct(String idProduct){
        return "{\r\n  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\r\n  \"ProductRequest\": {\r\n    \"ProductIdList\": [\r\n      \"" + idProduct + "\"  ],\r\n    \"Scope\": {\r\n      \"Offers\": false,\r\n      \"AssociatedProducts\": false,\r\n      \"Images\": true,\r\n      \"Ean\": false\r\n    }\r\n  }\r\n}";
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


    public static String getProduct(String id)
    {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, createJsonForGetProduct(id));
        Request request = new Request.Builder()
                .url(URLToSendToGetProduct)
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
            System.out.println("an error occurred");
            return "erreur";
        }
    }

    //region JsonStringToListView

    private  Result initListSearch(String keyword, String sortBy){

        try{
            String jsonString = searchProduct(keyword, sortBy);
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Products");

            for(int i = 0; i<jsonMainNode.length();i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String idProduct = jsonChildNode.optString("Id");
                System.out.println(idProduct);
                products.add(initListGetProduct(getProduct(idProduct)));
            }
            return ok(produits.render(products));
        }
        catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private static Product initListGetProduct(String jsonString){

        try{
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Products");

                JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);
                String idProduct =  jsonChildNode.optString("Id");
                String nameProduct = jsonChildNode.optString("Name");
                String descriptionProduct = jsonChildNode.optString("Description");
                String brandProduct = jsonChildNode.optString("Brand");
                String eanProduct = jsonChildNode.optString("Ean");
                String mainImageUrl = jsonChildNode.optString("MainImageUrl");
                int rating = jsonChildNode.optInt("Rating");
                int offersCount = jsonChildNode.optInt("OffersCount");

                Product product = new Product(idProduct,nameProduct,descriptionProduct,eanProduct,brandProduct,mainImageUrl,rating,offersCount);
                return product;

        }
        catch(JSONException e){
            e.printStackTrace();
            System.out.println("Erreur");
            return null;
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
        /*searchProduct("tablette","minprice");
        initListSearch(searchProduct("tablette","minprice"));
*/
        //System.out.println(initListGetProduct(getProduct("90NP0233")).toString());
        System.out.println(initListSearch("tablette","minprice"));
    }

}
