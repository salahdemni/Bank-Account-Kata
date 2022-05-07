import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccountKataTest {


    /*
    Test Deposit to an PersonalAccount (save money)
     */
    @Test
    public void testDepositToAccount() {
        List<Operation> operations = new ArrayList<>();
        Account account = new PersonalAccount(operations);
        //Add some money to the PersonalAccount
        BigDecimal deposit = new BigDecimal(200);
        account.depositToAccount(deposit);
        Assert.assertEquals(account.getBalance(), BigDecimal.valueOf(200));
        Assert.assertEquals(operations.get(0).getAmount(), BigDecimal.valueOf(200));
    }

    /*
     Test Withdrawal from an PersonalAccount (retrieve some money)
      */
    @Test
    public void testWithDrawFromAccount() {
        List<Operation> operations = new ArrayList<>();
        Account account = new PersonalAccount(operations);
        //Add some money to the PersonalAccount
        BigDecimal deposit = new BigDecimal(200);
        account.depositToAccount(deposit);
        //withdraw some money
        BigDecimal withDraw = new BigDecimal(100);
        account.withDrawFromAccount(withDraw);
        Assert.assertEquals(account.getBalance(), BigDecimal.valueOf(100));
        Assert.assertEquals(operations.get(0).getAmount(), BigDecimal.valueOf(200));
        Assert.assertEquals(operations.get(1).getAmount(), BigDecimal.valueOf(-100));
    }

    @Test
    public void testWithDrawAllTheAmount() {
        List<Operation> operations = new ArrayList<>();
        Account account = new PersonalAccount(operations);
        //Add some money to the PersonalAccount
        BigDecimal deposit = new BigDecimal(100);
        account.depositToAccount(deposit);
        //withdraw some money
        BigDecimal withDraw = new BigDecimal(100);
        account.withDrawFromAccount(withDraw);
        Assert.assertEquals(account.getBalance(), BigDecimal.ZERO);
        Assert.assertEquals(operations.get(0).getAmount(), BigDecimal.valueOf(100));
        Assert.assertEquals(operations.get(1).getAmount(), BigDecimal.valueOf(-100));

    }

    /*
    Test Withdrawal beyond the Amount in the PersonalAccount
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithDrawBeyondAccountBalance() {
        Account account = null;
        //Add some money to the PersonalAccount
        BigDecimal deposit = new BigDecimal(100);
        account.depositToAccount(deposit);
        //withdraw some money
        BigDecimal withDraw = new BigDecimal(120);
        account.withDrawFromAccount(withDraw);
    }

}
