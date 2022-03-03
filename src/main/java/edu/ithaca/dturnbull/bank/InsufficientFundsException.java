package edu.ithaca.dturnbull.bank;

/**
 * InsufficientFundsException
 * This class is used to create a new exception
 * Helps dealing with amount values
 * @author Mikolaj
 * @date 2/25/2022
 */

public class InsufficientFundsException extends Exception{
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String s) {
        super(s);
    }

}