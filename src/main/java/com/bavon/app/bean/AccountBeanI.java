package com.bavon.app.bean;

import com.bavon.app.model.Account;

public interface AccountBeanI {

    String chartOfAccounts();

    Account addOrUpdateAccount(Account account) throws Exception;

    void deleteAccount(Account account);
}
