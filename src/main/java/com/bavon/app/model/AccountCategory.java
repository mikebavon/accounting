package com.bavon.app.model;

public enum AccountCategory {

    HEADER("Header"),
    POSTING("Posting");

    private String name;

    AccountCategory(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
