package org.BankingSystem.views;

public class UserMenuView {


    public void userMenu() {


        System.out.println("-------Welcome to Banking System Menu------- \n "
        + "--Please Select The Action you want to do-- \n" +
                "-- 1. See current bank info \n"+
                //TODO Shows info like, balance, current financial goal, and transaction history
                "-- 2. Set Financial Goal \n " +
                //TODO Sets the persons Financial goal, and adds it to database, also allows them to change it if one already exists
               "-- 3. Add money to account  \n" +
                //TODO Allows them to add money into a savings account for them to start getting interest or into their normal checking account
                "-- 4. Log Out ");
                //TODO Logs them out and puts them back into the register/login screen
    }
}
