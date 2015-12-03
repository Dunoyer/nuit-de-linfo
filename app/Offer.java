import java.util.ArrayList;

/**
 * Created by Miage on 03/12/2015.
 */
public class Offer {
    private String Id;
    private String Condition;
    private Boolean IsAvailable;
    private String ProductUrl;
    private Number SalePrice;
    private Seller Seller;
    private PriceDetail PriceDetail;
    private ArrayList<Shipping> Shippings;
    private ArrayList<Size> Sizes;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public Boolean getAvailable() {
        return IsAvailable;
    }

    public void setAvailable(Boolean available) {
        IsAvailable = available;
    }

    public String getProductUrl() {
        return ProductUrl;
    }

    public void setProductUrl(String productUrl) {
        ProductUrl = productUrl;
    }

    public Number getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(Number salePrice) {
        SalePrice = salePrice;
    }

    public Seller getSeller() {
        return Seller;
    }

    public void setSeller(Seller seller) {
        Seller = seller;
    }

    public PriceDetail getPriceDetail() {
        return PriceDetail;
    }

    public void setPriceDetail(PriceDetail priceDetail) {
        PriceDetail = priceDetail;
    }

    public ArrayList<Shipping> getShippings() {
        return Shippings;
    }

    public void setShippings(ArrayList<Shipping> shippings) {
        Shippings = shippings;
    }

    public ArrayList<Size> getSizes() {
        return Sizes;
    }

    public void setSizes(ArrayList<Size> sizes) {
        Sizes = sizes;
    }
}
