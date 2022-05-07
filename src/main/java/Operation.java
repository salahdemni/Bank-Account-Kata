import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation {

    private Date date;
    private BigDecimal amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        if (getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return (new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(getDate()) + ": Withdraw Amount of " + getAmount());
        }
        return (new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(getDate()) + ": Deposit Amount of " + getAmount());

    }
}