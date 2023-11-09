package com.bavon.app.bean;

import com.bavon.app.model.Account;
import com.bavon.app.view.helper.HtmlCmpRender;
import com.bavon.database.Database;

import java.io.Serializable;

public class AccountBean implements AccountBeanI, Serializable {

    public String chartOfAccounts(){

        return HtmlCmpRender.table(Database.getDbInstance().getAccounts());

    }

    public Account addOrUpdateAccount(Account account){
        Database database = Database.getDbInstance();

        database.getAccounts().add(account);

        return account;
    }

    public void deleteAccount(Account account) {

    }
}
