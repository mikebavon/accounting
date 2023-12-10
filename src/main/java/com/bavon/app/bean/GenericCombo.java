package com.bavon.app.bean;

import com.bavon.app.model.Account;
import com.bavon.app.model.Customer;
import com.bavon.app.model.CustomerList;
import com.bavon.app.model.Journal;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import java.io.Serializable;
import java.util.List;

@Produces
@ApplicationScoped
public class GenericCombo implements Serializable {

    @EJB
    private AccountBeanI accountBean;

    @EJB
    private CustomerBeanI customerBean;

    @EJB
    private JournalBeanI journalBean;

    public List<Account> accounts(){
        return accountBean.list(new Account());
    }

    public List<Customer> customers(){
        return customerBean.list(new Customer());
    }

    public List<Journal> journals(){
        return journalBean.list(new Journal());
    }

}
