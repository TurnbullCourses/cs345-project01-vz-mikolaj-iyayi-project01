package edu.ithaca.dturnbull.bank;

/**
 * TextUserInterface
 * This class helps a customer go through the banking journey
 * @author Mikolaj
 * @date 2/25/2022
 */

import java.util.Scanner;

public class TextUserInterface {
    public static void main(String[] args){
        
        //login
        Scanner myObj = new Scanner(System.in);
        BankCustomer client = new BankCustomer("Benedict Reginald","McDonald",12345678,"reggi89@gmail.com",1231422);
        SavingsAccount savings = new SavingsAccount(0.5, 1500);
        CheckingAccount checking = new CheckingAccount(500);
        client.addCheckingAccount(checking);
        client.addSavingsAccount(savings);
        System.out.println("Welcome back, Benedict Reginald McDonald!");
        

        //main screen
        int userSelection = 0;
        while(userSelection!=3){
            System.out.println("Welcome to the main screen. Please enter your selection:");
            System.out.println("1 - Access your checking account");
            System.out.println("2 - Access your savings account");
            System.out.println("3 - Log out");
            userSelection = myObj.nextInt();
            while(userSelection>3 || userSelection<0){
                System.out.println("Please re-enter your selection.");
                userSelection = myObj.nextInt(); 
            }
            //checking account screen   
            if(userSelection == 1){
                while(userSelection != 5){
                    System.out.println("Welcome to the checking account screen. Please enter your selection:");
                    System.out.println("1 - Check current balance");
                    System.out.println("2 - Deposit money");
                    System.out.println("3 - Withdraw funds");
                    System.out.println("4 - Transfer funds to savings account");
                    System.out.println("5 - Return to main screen");
                    userSelection = myObj.nextInt();

                    while(userSelection>5 || userSelection<0){
                        System.out.println("Please re-enter your selection.");
                        userSelection = myObj.nextInt(); 
                    }

                    if(userSelection == 1){
                        System.out.println("Your current checking balance is $" + client.getCheckingBalance());
                    }
                    if(userSelection == 2){
                        System.out.println("Please enter amount to be deposited:");
                        double amount = myObj.nextDouble();
                        try{
                            client.depositChecking(amount);
                            System.out.println("Success!");
                        }
                        catch(IllegalArgumentException error){
                            System.out.println(error);
                        }
                        catch(FrozenException error){
                            System.out.println(error);
                        }  
                    }
                    if(userSelection == 3){
                        System.out.println("Please enter amount to be withdrawn:");
                        double amount = myObj.nextDouble();
                        try{
                            client.withdrawChecking(amount);
                            System.out.println("Success!");
                        }
                        catch(IllegalArgumentException error){
                            System.out.println(error);
                        }
                        catch(FrozenException error){
                            System.out.println(error);
                        }
                        catch(InsufficientFundsException error){
                            System.out.println(error);
                        }
                    }
                    if(userSelection == 4){
                        System.out.println("Please enter amount to be transferred:");
                        double amount = myObj.nextDouble();
                        try{
                            client.transferChecking(amount, savings);
                            System.out.println("Success!");
                        }
                        catch(IllegalArgumentException error){
                            System.out.println(error);
                        }
                        catch(FrozenException error){
                            System.out.println(error);
                        }
                        catch(InsufficientFundsException error){
                            System.out.println(error);
                        }
                    }
                    
                }


            }
            //savings account screen
            else if(userSelection == 2){
                while(userSelection != 5){
                    System.out.println("Welcome to the savings account screen. Please enter your selection:");
                    System.out.println("1 - Check current balance");
                    System.out.println("2 - Deposit money");
                    System.out.println("3 - Withdraw funds");
                    System.out.println("4 - Transfer funds to checking account");
                    System.out.println("5 - Return to main screen");
                    userSelection = myObj.nextInt();

                    while(userSelection>5 || userSelection<0){
                        System.out.println("Please re-enter your selection.");
                        userSelection = myObj.nextInt(); 
                    }

                    if(userSelection == 1){
                        System.out.println("Your current savings balance is $" + client.getSavingsBalance());
                    }
                    if(userSelection == 2){
                        System.out.println("Please enter amount to be deposited:");
                        double amount = myObj.nextDouble();
                        try{
                            client.depositSavings(amount);
                            System.out.println("Success!");
                        }
                        catch(IllegalArgumentException error){
                            System.out.println(error);
                        }
                        catch(FrozenException error){
                            System.out.println(error);
                        }  
                    }
                    if(userSelection == 3){
                        System.out.println("Please enter amount to be withdrawn:");
                        double amount = myObj.nextDouble();
                        try{
                            client.withdrawSavings(amount);
                            System.out.println("Success!");
                        }
                        catch(IllegalArgumentException error){
                            System.out.println(error);
                        }
                        catch(FrozenException error){
                            System.out.println(error);
                        }
                        catch(InsufficientFundsException error){
                            System.out.println(error);
                        }
                    }
                    if(userSelection == 4){
                        System.out.println("Please enter amount to be transferred:");
                        double amount = myObj.nextDouble();
                        try{
                            client.transferSavings(amount, checking);
                            System.out.println("Success!");
                        }
                        catch(IllegalArgumentException error){
                            System.out.println(error);
                        }
                        catch(FrozenException error){
                            System.out.println(error);
                        }
                        catch(InsufficientFundsException error){
                            System.out.println(error);
                        }
                    } 
                }

                
            }
        }

        
        System.out.println("Have a good day!");
        myObj.close();
    
        

        

            

        



    }
    
}

