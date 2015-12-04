package controllers;

import com.squareup.okhttp.*;
import models.CartItem;
import models.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.produits;
import views.html.pushToCart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miage on 03/12/2015.
 */
public class CartController extends Controller {
    public String URLToSendToPushToCart ="https://api.cdiscount.com/OpenApi/json/PushToCart";

    public String cartId="";

    public List<CartItem> products = new ArrayList<CartItem>();

    private  String createJsonForPushToCart(String idProduct, String quantite,String cartId){


        /*if(cartId.isEmpty()) {
            return "{\n" +
                    "  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\n" +
                    "  \"PushToCartRequest\": {\n" +
                    "    \"OfferId\": "+ idProduct +"\",\n" +
                    "    \"ProductId\":"+ idProduct +"\",\n" +
                    "    \"Quantity\": 1,\n" +
                    "    \"SellerId\": \"0\"\n" +
                    "  }\n" +
                    "}";
        }
        else
        {*/
        String toto = "{\n" +
                "  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\n" +
                "  \"PushToCartRequest\": {\n" +
                " \"CartGUID\":\""+ cartId +"\",\n"+
                "    \"ProductId\": \""+ idProduct +"\",\n" +
                "    \"Quantity\": 1 \n" +
                "  }\n" +
                "}";
        System.out.println(toto);
            return toto;

       // }
    }

    public String pushToCart(String idProduct, String quantite,String cartId)
    {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, createJsonForPushToCart(idProduct, quantite,cartId));
        Request request = new Request.Builder()
                .url(URLToSendToPushToCart)
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

    public Result addToCart(String idProduct, String quantite,String cartId) {

        try{
            String jsonString = pushToCart(idProduct,quantite, cartId);
            JSONObject jsonResponse = new JSONObject(jsonString);
            this.cartId = jsonResponse.optString("CartGUID");
            String checkoutUrl = jsonResponse.optString("CheckoutUrl");
            return ok(pushToCart.render(this.cartId,checkoutUrl));
        }
        catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }

}