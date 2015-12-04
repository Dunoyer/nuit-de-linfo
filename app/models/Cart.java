package models;

import org.joda.time.DateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miage on 04/12/2015.
 */
public class Cart {
    private List<CartItem> cartItemList = new ArrayList<CartItem>();
    private String cartGUID=null;
    private String checkoutURL=null;
    private DateTime creationDate;
    private int productCount;
    private int totalPrice;
    private int totalQuantity;
    private DateTime updateTime;

    public Cart() {
    }


    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public String getCartGUID() {
        return cartGUID;
    }

    public void setCartGUID(String cartGUID) {
        this.cartGUID = cartGUID;
    }
}
