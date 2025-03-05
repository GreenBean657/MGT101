package org.BankingSystem.views;

import org.BankingSystem.models.Human;

import java.util.Scanner;

public class BankInfoView {

    public void run(Human human) {

        Object[][] data = human.getData();


        System.out.println(String.format("""
                --[BANKING INFORMATION]--
                Name: %s
                Balance: $%.2f
                
                Banking History:
                %s
                
                Set Goals:
                %s
                
                """, data[0][2], (float)data[0][1], data[1][0], data[2][0]));


        /*
            public Object[][] getData() {
        return new Object[][]{
                {position,(balance *= 1.35f), name, password},
                history,
                setGoals
        };
    }
         */

        new Scanner(System.in).nextLine();

    }
}
