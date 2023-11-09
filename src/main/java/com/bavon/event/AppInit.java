package com.bavon.event;

import com.bavon.app.model.Account;
import com.bavon.app.model.User;
import com.bavon.database.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("*************** Initializing database *************");

        Database database = Database.getDbInstance();
        database.getUsers().add(new User(0L, "john.snow@test.com", "12345"));
        database.getUsers().add(new User(0L, "john.doe@test.com", "54321"));
        database.getUsers().add(new User(0L, "jane.doe@test.com", "00000"));

        database.getAccounts().add(new Account("1", "Asset", null));
        database.getAccounts().add(new Account("2", "Liabilities", null));
        database.getAccounts().add(new Account("3", "Owners Equity", null));
        database.getAccounts().add(new Account("4", "Income", null));
        database.getAccounts().add(new Account("5", "Expense", null));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Application is undeployed or destroyed");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }

}
