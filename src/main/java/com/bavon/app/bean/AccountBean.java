package com.bavon.app.bean;

import com.bavon.app.model.Account;


public class AccountBean extends GenericBean<Account> implements AccountBeanI {

    @Override
    public void addOrUpdate(Account account) {
        if (account.getType() == null)
            throw new RuntimeException("Invalid account type");

        getDao().addOrUpdate(account);

    }

}
