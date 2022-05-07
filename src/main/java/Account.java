import java.math.BigDecimal;

public interface Account {

    BigDecimal getBalance();

    void depositToAccount(BigDecimal amount);

    void withDrawFromAccount(BigDecimal amount) throws Exception;

    void displayHistory();
}
