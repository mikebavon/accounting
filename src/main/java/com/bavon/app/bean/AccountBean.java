package com.bavon.app.bean;

import com.bavon.app.model.Account;
import com.bavon.database.Database;

import java.io.Serializable;
import java.util.List;

public class AccountBean implements AccountBeanI, Serializable {

    public List<Account> list(){
        return Database.getDbInstance().getAccounts();

    }

    public Account addOrUpdateAccount(Account account){
        Database database = Database.getDbInstance();

        database.getAccounts().add(account);

        return account;
    }

    public void deleteAccount(Account account) {

    }
}
