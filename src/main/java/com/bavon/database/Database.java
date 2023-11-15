package com.bavon.database;

import com.bavon.app.model.Account;
import com.bavon.app.model.Customer;
import com.bavon.app.model.Journal;
import com.bavon.app.model.User;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Database implements Serializable {

    private String databaseCreateAt;

    private List<User> users = new ArrayList<>();

    private List<Account> accounts = new ArrayList<>();

    private List<Journal> journals = new ArrayList<>();

    private List<Customer> customers = new ArrayList<>();

    private static Database dbInstance;

    private Database(){}

    public static Database getDbInstance(){
        if (dbInstance == null) {
            dbInstance = new Database();
            dbInstance.databaseCreateAt = DateFormat.getDateTimeInstance().format(new Date());
            System.out.println("Database created at " + dbInstance.getDatabaseCreateAt());
        }

        return dbInstance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getDatabaseCreateAt() {
        return databaseCreateAt;
    }
}
