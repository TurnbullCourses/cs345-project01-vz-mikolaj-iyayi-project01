package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceEmailTest() {
        BankAccount bankAccount = new CheckingAccount( 200); //balance is non zero, validity of inputs is tested elsewhere as this is just a getter

        assertEquals(200, bankAccount.getBalance(), 0.001);//no need to round floating points as answer should be accurate to 2 decimal places
        

        BankAccount bankAccount2 = new SavingsAccount(0.1, 0); //balance is zero, boundary case
        assertEquals(0, bankAccount2.getBalance(), 0.001);
        assertEquals(200, bankAccount.getBalance(), 0.001);

    }

    @Test
    void withdrawTest() throws InsufficientFundsException, IllegalArgumentException{
        BankAccount bankAccount = new CheckingAccount( 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(101));

        bankAccount.withdraw(99);
        assertEquals(1, bankAccount.getBalance());
        
        bankAccount.withdraw(1);
        assertEquals(0, bankAccount.getBalance());

        BankAccount bankAccount2 = new SavingsAccount(0.05, 200);
        //amount to withdraw must have no more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.withdraw(0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.withdraw(0.0132421));
        bankAccount2.withdraw(0.01); //border case
        bankAccount2.withdraw(0.1);

        //amount to withdraw must be non negative and greater than zero
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.withdraw(-0.01));
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.withdraw(-100));
        bankAccount2.withdraw(0);//border case
        assertEquals(199.89, bankAccount2.getBalance(),0.001);
        bankAccount2.withdraw(50);
        assertEquals(149.89, bankAccount2.getBalance(),0.001);

       
        BankAccount bankAccount3 = new CheckingAccount( 200);
        assertThrows(IllegalArgumentException.class, () -> bankAccount3.withdraw(-300));

        BankAccount bankAccount4 = new SavingsAccount( 0.02, 3000);
        assertThrows(IllegalArgumentException.class, () -> bankAccount4.withdraw(-300.376)); //false case
        assertThrows(IllegalArgumentException.class, () -> bankAccount4.withdraw(300.376)); //false case
    }
    @Test
    void depositTest() throws IllegalArgumentException{ // or insuffieient funds exception ?
         BankAccount bankAccount = new CheckingAccount( 350);
         bankAccount.deposit(100);
         assertEquals(450, bankAccount.getBalance(), 0.001);
         assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-300));

         BankAccount bankAccount2 = new SavingsAccount(0.05, 350);
         //assertThrows(IllegalArgumentException.class, () -> bankAccount2.deposit(60.970));
         assertThrows(IllegalArgumentException.class, ()-> bankAccount2.deposit(60.975));

         //amount deposited must have no more than 2 decimal places
        bankAccount.deposit(0.11);//border case
        assertEquals(450.11, bankAccount.getBalance(),0.001);
        bankAccount.deposit(0.01);//border case
        assertEquals(450.12, bankAccount.getBalance(),0.001);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(0.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(12.21423543));

        //amount deposited must be non negative
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-0.01));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100));
        bankAccount.deposit(0);//border case
        assertEquals(450.12, bankAccount.getBalance(),0.001);
        bankAccount.deposit(100);
        assertEquals(550.12, bankAccount.getBalance(),0.001);
    }

    


    @Test
    void transferTest() throws InsufficientFundsException{
        BankAccount bankAccount = new CheckingAccount(200);
        BankAccount bankAccount2 = new SavingsAccount(0.15, 200);
        
        //amount transferred must be non negative
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer(-0.01, bankAccount2));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer(-100, bankAccount2));
        bankAccount.transfer(0, bankAccount2);//border case
        assertEquals(200, bankAccount.getBalance(),0.001);
        assertEquals(200, bankAccount2.getBalance(),0.001);
        bankAccount.transfer(0.01, bankAccount2);
        assertEquals(199.99, bankAccount.getBalance(),0.001);
        assertEquals(200.01, bankAccount2.getBalance(),0.001);
        bankAccount.transfer(100, bankAccount2);
        assertEquals(99.99, bankAccount.getBalance(),0.001);
        assertEquals(300.01, bankAccount2.getBalance(),0.001);

        //amount transferred must not have more than 2 decimal places
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer(49.999, bankAccount2));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer(49.9239493295, bankAccount2));
        bankAccount.transfer(0.99, bankAccount2);//border case
        assertEquals(99, bankAccount.getBalance(),0.001);
        assertEquals(301, bankAccount2.getBalance(),0.001);
        bankAccount.transfer(50.1, bankAccount2);
        assertEquals(48.9, bankAccount.getBalance(),0.001);
        assertEquals(351.1, bankAccount2.getBalance(),0.001);

        //amount transferred from the transferer cannot exceed their account balance
        assertThrows(InsufficientFundsException.class, ()-> bankAccount.transfer(48.91, bankAccount2));
        assertThrows(InsufficientFundsException.class, ()-> bankAccount.transfer(5000, bankAccount2));
        bankAccount.transfer(48.9, bankAccount2);//border case
        assertEquals(0, bankAccount.getBalance(),0.001);
        assertEquals(400, bankAccount2.getBalance(),0.001);
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new CheckingAccount(200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
        
        


        //input parameters for starting balance must be non negative
        bankAccount = new CheckingAccount(10);
        assertEquals(10 , bankAccount.getBalance(), 0.001);
        bankAccount = new CheckingAccount(0);// border case
        assertEquals(0, bankAccount.getBalance(), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount(-0.01));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount(-100));

        //input parameters for starting balance can't have more than 2 decimal spaces
        bankAccount = new SavingsAccount(0.05, 0.01);// border case
        assertEquals(0.01, bankAccount.getBalance(), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount(0.05,0.001));
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount( 0.05,0.1232213));

        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(0.05,-300.376)); 
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(0.05,300.376)); 

        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(0.05,67.377)); //false case
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount(0.05,-900)); //false case


    }

    @Test
    void isAmountValidTest(){
        assertFalse(BankAccount.isAmountValid(-1)); //amount can't be less than  zero
        assertFalse(BankAccount.isAmountValid(-0.01)); //amount can't be less than zero
        assertTrue(BankAccount.isAmountValid(0)); //amount can't be less than zero, border case
        
        assertTrue(BankAccount.isAmountValid(0.01)); //amount must be positive, border case
        assertTrue(BankAccount.isAmountValid(1)); //amount must be positive
        assertTrue(BankAccount.isAmountValid(100)); //amount must be positive

        assertTrue(BankAccount.isAmountValid(10.1)); //amount must have 2 decimal places or less
        assertTrue(BankAccount.isAmountValid(10.11));//amount must have 2 decimal places or less, border case
        assertFalse(BankAccount.isAmountValid(10.111));//amount must have 2 decimal places or less
        assertFalse(BankAccount.isAmountValid(10.1111111));//amount must have 2 decimal places or less

    }
    
   



    




}