package com.bavon.app.utility;

import com.bavon.app.bean.*;
import com.bavon.app.model.*;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import java.io.Serializable;
import java.util.List;

@Produces
@ApplicationScoped
public class SelectBoxStore implements Serializable {

    @EJB
    private AccountBeanI accountBean;

    @EJB
    private CustomerBeanI customerBean;

    @EJB
    private JournalBeanI journalBean;

    @EJB
    private InvoiceBeanI invoiceBean;

    @EJB
    private VendorBeanI vendorBean;

    public List<Account> accounts(){
        return accountBean.list(new Account());
    }

    public List<Customer> customers(){
        return customerBean.list(new Customer());
    }

    public List<Journal> journals(){
        return journalBean.list(new Journal());
    }

    public List<Vendor> vendors(){
        return vendorBean.list(new Vendor());
    }

    public List<Invoice> invoices(){
        return invoiceBean.list(new Invoice());
    }

}
