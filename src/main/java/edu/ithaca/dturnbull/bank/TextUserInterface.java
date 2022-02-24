package edu.ithaca.dturnbull.bank;

import java.util.Random;
import java.util.Scanner;

public class TextUserInterface {
    public static void main(String[] args){
        
        Scanner myObj = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Create bank customer account:");
        //set up screen
        System.out.println("Please enter your first name:");
        String firstName = myObj.next();
        System.out.println("Please enter your last name:");
        String lastName = myObj.next();
        System.out.println("Please enter your id:");
        int id = myObj.nextInt();
        System.out.println("Please enter your email:");
        String email = myObj.next();

        BankCustomer user = new BankCustomer(firstName, lastName, rand.nextInt(10000000,99999999),email,id);
        System.out.println("User added successfully!");
        
        //add account screen
        System.out.println("Please add a bank account. Choose 1 for checking or 2 for savings:");
        int userSelection = myObj.nextInt();
        while(userSelection != 1 || userSelection != 2){
            System.out.println("Invalid input. Choose 1 for checking or 2 for savings:");
            userSelection = myObj.nextInt();
        }
        if(userSelection==1){
            System.out.println("Please choose a starting balance:");
            double balance = myObj.nextDouble();
            user.addCheckingAccount(new CheckingAccount(balance));
        }
        else if(userSelection==1){
            System.out.println("Please choose a starting balance:");
            double balance = myObj.nextDouble();
            user.addSavingsAccount(new SavingsAccount(0.5,balance));
        }
        System.out.println("Success!");
        
        //main screen
        while (userSelection != 11){
            
            System.out.println("What would you like to do? Please enter your selection:");
            System.out.println("1 - add new checking account");
            System.out.println("2 - add new savings account");
            System.out.println("3 - deposit funds to your checking account");
            System.out.println("4 - deposit funds to your savings account");
            System.out.println("5 - withdraw funds from your checking account");
            System.out.println("6 - withdraw funds from your savings account");
            System.out.println("7 - get balance from checking account");
            System.out.println("8 - get balance from savings account");
            System.out.println("9 - transfer funds from checking to savings account");
            System.out.println("10 - transfer funds from savings to checking account");
            System.out.println("11 - log out");
        }



    }
    
}
