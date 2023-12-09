package com.bavon.app.bean;

import com.bavon.app.model.Customer;
import com.bavon.app.model.CustomerList;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import java.io.Serializable;
import java.util.List;

@Produces
@ApplicationScoped
public class GenericCombo implements Serializable {

    @EJB
    private CustomerBeanI customerBean;

    public List<Customer> customers(){
        return customerBean.list(new Customer());
    }

}
