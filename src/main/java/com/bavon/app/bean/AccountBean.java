package com.bavon.app.bean;

import com.bavon.app.model.entity.Account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountBean implements AccountBeanI, Serializable {

    public String chartOfAccounts(){

        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account(null, "Asset", null));
        accounts.add(new Account(null, "Cash & Cash Equivalent", null));
        accounts.add(new Account("10001", "Petty Cash", BigDecimal.valueOf(10000)));
        accounts.add(new Account("10002", "KCB Checking Account", BigDecimal.valueOf(1000000)));

        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<table><tr><th>Code</th><th>Account</th><th>Balance</th></tr>");

        for (Account account : accounts)
            trBuilder.append(account.tableRow());

        trBuilder.append("</table>");

        return trBuilder.toString();

    }

    public Account addOrUpdateAccount(Account account) throws Exception{

        return null;
    }

    public void deleteAccount(Account account) {

    }
}
