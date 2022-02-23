package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AdminTest {

    @Test
    public void freezeAccountTest() throws InsufficientFundsException, FrozenException {
        BankCustomer customer1 = new BankCustomer("Natsu", "Dragneel", 67890, "b123@gmail.com", 567585, false);
        SavingsAccount savings1 = new SavingsAccount(1.2, 9000);
        customer1.addSavingsAccount(savings1);

        
        Admin.freezeAccount(customer1);
        assertThrows(FrozenException.class, ()->savings1.withdraw(8000, customer1));

        BankCustomer customer2 = new BankCustomer("Natsu", "Dragneel", 67890, "b123@gmail.com", 567585, true);
        SavingsAccount savings2 = new SavingsAccount(1.2, 9000);
        customer1.addSavingsAccount(savings2);

        Admin.freezeAccount(customer2);
        assertThrows(FrozenException.class, ()->savings2.withdraw(8000, customer2));
       // assertThrows(FrozenException.class, ()->savings1.transfer(1000, customer2));
        assertThrows(FrozenException.class, ()->savings2.deposit(1000, customer2));

        
        

    }
    
}
