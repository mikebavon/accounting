package com.bavon.app.bean;

import com.bavon.app.model.entity.Account;
import com.bavon.database.Database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountBean implements AccountBeanI, Serializable {

    public String chartOfAccounts(){

        List<Account> accounts = Database.getDbInstance().getAccounts();

        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<table><tr><th>Code</th><th>Account</th><th>Balance</th></tr>");

        for (Account account : accounts)
            trBuilder.append(account.tableRow());

        trBuilder.append("</table>");

        return trBuilder.toString();

    }

    public Account addOrUpdateAccount(Account account){
        Database database = Database.getDbInstance();

        database.getAccounts().add(account);

        return account;
    }

    public void deleteAccount(Account account) {

    }
}
