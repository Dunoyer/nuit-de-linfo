/**
 * Created by Miage on 03/12/2015.
 */
public class CartItem {
    private String Condition;
    private String OfferId;
    private Number Price;
    private String ProductId;
    private Integer Quantity;
    private Integer SellerId;
    private Integer SizeId;

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getOfferId() {
        return OfferId;
    }

    public void setOfferId(String offerId) {
        OfferId = offerId;
    }

    public Number getPrice() {
        return Price;
    }

    public void setPrice(Number price) {
        Price = price;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getSellerId() {
        return SellerId;
    }

    public void setSellerId(Integer sellerId) {
        SellerId = sellerId;
    }

    public Integer getSizeId() {
        return SizeId;
    }

    public void setSizeId(Integer sizeId) {
        SizeId = sizeId;
    }
}
