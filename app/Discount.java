import org.joda.time.DateTime;

/**
 * Created by Miage on 03/12/2015.
 */
public class Discount {
    private String Type;
    private DateTime StartDate;
    private DateTime EndDate;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public DateTime getStartDate() {
        return StartDate;
    }

    public void setStartDate(DateTime startDate) {
        StartDate = startDate;
    }

    public DateTime getEndDate() {
        return EndDate;
    }

    public void setEndDate(DateTime endDate) {
        EndDate = endDate;
    }
}
