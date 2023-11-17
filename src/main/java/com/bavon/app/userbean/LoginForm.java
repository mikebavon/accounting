package com.bavon.app.userbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class LoginForm implements Serializable {

    private String usernamePlaceHolder = "Enter Username";

    private String passwordPlaceHolder = "Enter Password";

    public String getUsernamePlaceHolder() {
        return usernamePlaceHolder;
    }

    public void setUsernamePlaceHolder(String usernamePlaceHolder) {
        this.usernamePlaceHolder = usernamePlaceHolder;
    }

    public String getPasswordPlaceHolder() {
        return passwordPlaceHolder;
    }

    public void setPasswordPlaceHolder(String passwordPlaceHolder) {
        this.passwordPlaceHolder = passwordPlaceHolder;
    }
}
