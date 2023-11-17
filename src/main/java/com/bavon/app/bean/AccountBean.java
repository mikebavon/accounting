package com.bavon.app.bean;

import com.bavon.app.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountBean extends GenericBean<Account> implements AccountBeanI {

    public List<Account> chartTree(){

        return new ArrayList<>();
    }

}
