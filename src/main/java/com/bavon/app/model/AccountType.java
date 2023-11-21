package com.bavon.app.model;

public enum AccountType {

    ASSET("Asset"),
    LIABILITY("Liability"),
    OWNERS_EQUITY("Owners Equity"),
    INCOME("Income"),
    EXPENSE("Expense");

    private String name;

    AccountType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
