package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AdminTest {

    @Test
    public void freezeAccountTest() throws InsufficientFundsException, FrozenException {
        BankCustomer customer1 = new BankCustomer("Natsu", "Dragneel", 67890, "b123@gmail.com", 567585);
        SavingsAccount savings1 = new SavingsAccount(1.2, 9000);
        customer1.addSavingsAccount(savings1);

        
        Admin.freezeAccount(customer1);
        assertThrows(FrozenException.class, ()->customer1.withdrawSavings(8000));

        BankCustomer customer2 = new BankCustomer("Natsu", "Dragneel", 67890, "b123@gmail.com", 567585);
        SavingsAccount savings2 = new SavingsAccount(1.2, 9000);
        customer2.addSavingsAccount(savings2);

        Admin.freezeAccount(customer2);
        assertThrows(FrozenException.class, ()->customer2.withdrawSavings(8000));
       // assertThrows(FrozenException.class, ()->savings1.transfer(1000, customer2));
        assertThrows(FrozenException.class, ()->customer2.depositSavings(1000));

        
        

    }
    
}
