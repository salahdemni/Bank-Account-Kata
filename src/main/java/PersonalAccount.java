import exceptions.InsufficientAmountException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PersonalAccount implements Account {

    private int accountId;
    private BigDecimal balance;
    private List<Operation> operations;

    public PersonalAccount(List<Operation> operations) {
        this.operations = operations;
        this.balance = new BigDecimal(0);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    synchronized public void depositToAccount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) >= 0) {
            balance = balance.add(amount);
            Operation deposit = new Operation();
            deposit.setAmount(amount);
            deposit.setDate(new Date());
            this.operations.add(deposit);
        } else System.out.println("Nothing to deposit as the Amount is not a valid value");

    }

    /*
    synchronized block added to prevent issues in case of a multithreading
     or multiple client use the same account
     */
    synchronized public void withDrawFromAccount(BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) >= 0) {
            if (balance.compareTo(amount) >= 0) {
                balance = balance.subtract(amount);
                Operation withdrawal = new Operation();
                withdrawal.setAmount(amount.negate());
                withdrawal.setDate(new Date());
                this.operations.add(withdrawal);
            } else {
                    throw new InsufficientAmountException("You have not enough balance in the Account!");
            }
        } else System.out.println("Nothing to withdraw as the Amount is not a valid value");

    }

    public void displayHistory() {
        //Display operations history
        System.out.println("Bellow is the History of the Account :" + getAccountId());
        for (Operation operation : this.operations) {
            System.out.println(operation);
        }
        System.out.println("The Actual Account Balance is :" + getBalance());
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
