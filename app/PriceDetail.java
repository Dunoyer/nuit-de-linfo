/**
 * Created by Miage on 03/12/2015.
 */
public class PriceDetail {
    private Number ReferencePrice;
    private Saving Saving;
    private Discount Discount;

    public Number getReferencePrice() {
        return ReferencePrice;
    }

    public void setReferencePrice(Number referencePrice) {
        ReferencePrice = referencePrice;
    }

    public Saving getSaving() {
        return Saving;
    }

    public void setSaving(Saving saving) {
        Saving = saving;
    }

    public Discount getDiscount() {
        return Discount;
    }

    public void setDiscount(Discount discount) {
        Discount = discount;
    }
}
