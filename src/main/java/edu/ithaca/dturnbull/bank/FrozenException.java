package edu.ithaca.dturnbull.bank;

/**
 * FrozenException
 * This class is used to create a new exception
 * This class helps dealing with the status of an account
 * @author Vaibhav Zaveri
 * @date 2/25/2022
 */

public class FrozenException extends Exception{
    private static final long serialVersionUID = 1L;

    public FrozenException(String s) {
        super(s);
    }

}