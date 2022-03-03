package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AdminTest {

    @Test
    public void freezeAccountTest() throws InsufficientFundsException, FrozenException {
        // integration tests
        BankCustomer customer1 = new BankCustomer("Natsu", "Dragneel", 67890, "b123@gmail.com", 567585);
        SavingsAccount savings1 = new SavingsAccount(1.2, 9000);
        customer1.addSavingsAccount(savings1);

        Admin.freezeAccount(savings1);
        assertThrows(FrozenException.class, ()->customer1.withdrawSavings(8000));
        
        BankCustomer customer2 = new BankCustomer("Natsu", "Dragneel", 67890, "b123@gmail.com", 567585);
        SavingsAccount savings2 = new SavingsAccount(1.2, 9000);
        CheckingAccount checking = new CheckingAccount(5000);
        customer2.addSavingsAccount(savings2);
        customer2.addCheckingAccount(checking);

        Admin.freezeAccount(savings2);
        //tests that methods called on frozen account returns error
        assertThrows(FrozenException.class, ()->customer2.withdrawSavings(8000)); 
        assertThrows(FrozenException.class, ()->savings2.transfer(1000, checking)); 
        assertThrows(FrozenException.class, ()->customer2.depositSavings(1000)); 

        Admin.unfreezeAccount(savings2);
        //unfreezing an account allows for the methods to execute
        customer2.withdrawSavings(8000);
        assertEquals(1000, customer2.getSavingsBalance()); 
        savings2.transfer(1000, checking);
        assertEquals(0, customer2.getSavingsBalance()); 
        assertEquals(6000, customer2.getCheckingBalance()); 
        customer2.depositSavings(1000);
        assertEquals(1000, customer2.getSavingsBalance()); 

        

        
        

    }
    
}
