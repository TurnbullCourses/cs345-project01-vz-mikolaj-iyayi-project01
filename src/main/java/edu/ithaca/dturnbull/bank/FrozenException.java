package edu.ithaca.dturnbull.bank;

public class FrozenException extends Exception{
    private static final long serialVersionUID = 1L;

    public FrozenException(String s) {
        super(s);
    }

}