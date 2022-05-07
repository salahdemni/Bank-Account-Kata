import exceptions.InsufficientAmountException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

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
    public void testWithDrawFromAccount() throws Exception {
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
    public void testWithDrawAllTheAmount() throws Exception {
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
    @Test(expectedExceptions = InsufficientAmountException.class)
    public void testWithDrawBeyondAccountBalance() throws Exception {
        List<Operation> operations = new ArrayList<>();
        Account account = new PersonalAccount(operations);
        //Add some money to the PersonalAccount
        BigDecimal deposit = new BigDecimal(100);
        account.depositToAccount(deposit);
        //withdraw some money
        BigDecimal withDraw = new BigDecimal(120);
        account.withDrawFromAccount(withDraw);
    }
/*
    Test Display History of the operation performed in the Personal Account
     */

    @Test
    public void testDisplayHistory() throws Exception {
        Account account = mock(PersonalAccount.class);
        //Add some money to the PersonalAccount
        BigDecimal deposit = new BigDecimal(100);
        account.depositToAccount(deposit);
        //withdraw some money
        BigDecimal withDraw = new BigDecimal(100);
        account.withDrawFromAccount(withDraw);
        account.displayHistory();
        verify(account, times(1)).displayHistory();
    }

}
