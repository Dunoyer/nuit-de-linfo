/**
 * Created by Miage on 03/12/2015.
 */
public class Size {
    private Integer Id;
    private String Name;
    private Number SalePrice = null;
    private Boolean IsAvailable;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Number getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(Number salePrice) {
        SalePrice = salePrice;
    }

    public Boolean getAvailable() {
        return IsAvailable;
    }

    public void setAvailable(Boolean available) {
        IsAvailable = available;
    }
}
