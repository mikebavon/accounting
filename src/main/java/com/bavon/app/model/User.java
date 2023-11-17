package com.bavon.app.model;

import com.bavon.database.Database;
import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;

import java.io.Serializable;

@DbTable(name = "users")
public class User implements Serializable {

    @DbTableColumn(name = "id", definition = "int")
    private Long id;

    @DbTableColumn(name = "username")
    private String username;

    @DbTableColumn(name = "password")
    private String password;

    private String confirmPassword;

    public User(){}

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
