package com.bavon.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerList implements Serializable {

    public CustomerList(List<Customer> customers){
        this.customers = customers == null? new ArrayList<>() : customers;
    }

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
