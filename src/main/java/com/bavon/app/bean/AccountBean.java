package com.bavon.app.bean;

import com.bavon.app.model.entity.Account;
import com.bavon.app.view.html.HtmlComponent;
import com.bavon.database.Database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountBean implements AccountBeanI, Serializable {

    public String chartOfAccounts(){

        return HtmlComponent.table(Database.getDbInstance().getAccounts());

    }

    public Account addOrUpdateAccount(Account account){
        Database database = Database.getDbInstance();

        database.getAccounts().add(account);

        return account;
    }

    public void deleteAccount(Account account) {

    }
}
