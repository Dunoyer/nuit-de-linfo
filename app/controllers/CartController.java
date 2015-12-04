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
    public String checkoutUrl="/attentat";

    public List<CartItem> products = new ArrayList<CartItem>();

    private  String createJsonForPushToCart(String idProduct, String quantite,String cartId){
        String res = "{\n" +
                "  \"ApiKey\": \"93cf730f-a372-4b74-8df3-e64bf9c7a817\",\n" +
                "  \"PushToCartRequest\": {\n" +
                " \"CartGUID\":\""+ cartId +"\",\n"+
                "    \"ProductId\": \""+ idProduct +"\",\n" +
                "    \"Quantity\": 1 \n" +
                "  }\n" +
                "}";
        System.out.println(res);
        return res;
    }

    public String pushToCart(String idProduct)
    {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, createJsonForPushToCart(idProduct, "1",this.cartId));
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

    public Result addToCart(String idProduct) {
        System.out.println(idProduct);
        try{
            String jsonString = pushToCart(idProduct);
            JSONObject jsonResponse = new JSONObject(jsonString);
            this.cartId = jsonResponse.optString("CartGUID");
            this.checkoutUrl = (!jsonResponse.optString("CheckoutUrl").isEmpty()) ? jsonResponse.optString("CheckoutUrl") : this.checkoutUrl ;
            System.out.println(checkoutUrl);
            return redirect("/attentat");
        }
        catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public Result goToCommand(){
        return redirect(this.checkoutUrl);
    }

}
