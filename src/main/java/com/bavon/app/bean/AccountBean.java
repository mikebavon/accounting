package com.bavon.app.bean;

import com.bavon.app.model.Account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class AccountBean extends GenericBean<Account> implements AccountBeanI {

    @PostConstruct
    public void init(){
        System.out.println("Bean has bean created!!");
    }

    @Override
    public void addOrUpdate(Account account) {
        if (account.getType() == null)
            throw new RuntimeException("Invalid account type");

        getDao().addOrUpdate(account);

    }

    @PreDestroy
    public void atDestruction(){

    }

}
