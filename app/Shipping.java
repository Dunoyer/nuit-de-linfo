import org.joda.time.DateTime;

/**
 * Created by Miage on 03/12/2015.
 */
public class Shipping {
    private String Name;
    private String DelayToDisplay;
    private Number Price;
    private DateTime MaxDeliveryDate;
    private DateTime MinDeliveryDate;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDelayToDisplay() {
        return DelayToDisplay;
    }

    public void setDelayToDisplay(String delayToDisplay) {
        DelayToDisplay = delayToDisplay;
    }

    public Number getPrice() {
        return Price;
    }

    public void setPrice(Number price) {
        Price = price;
    }

    public DateTime getMaxDeliveryDate() {
        return MaxDeliveryDate;
    }

    public void setMaxDeliveryDate(DateTime maxDeliveryDate) {
        MaxDeliveryDate = maxDeliveryDate;
    }

    public DateTime getMinDeliveryDate() {
        return MinDeliveryDate;
    }

    public void setMinDeliveryDate(DateTime minDeliveryDate) {
        MinDeliveryDate = minDeliveryDate;
    }
}
