package com.bavon.app.bean;

import com.bavon.app.model.Account;

import java.util.List;


public interface AccountBeanI extends GenericBeanI<Account> {
    List<Account> chartTree();
}
