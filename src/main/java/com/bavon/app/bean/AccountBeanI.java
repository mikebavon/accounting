package com.bavon.app.bean;

import com.bavon.app.model.Account;

import java.util.List;

public interface AccountBeanI {

    List<Account> list();

    Account addOrUpdateAccount(Account account);

    void deleteAccount(Account account);
}
