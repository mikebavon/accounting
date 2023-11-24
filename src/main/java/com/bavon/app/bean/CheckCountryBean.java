package com.bavon.app.bean;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.Serializable;

@Singleton
public class CheckCountryBean implements Serializable {

    @PostConstruct
    public void init(){
        System.out.println("Executed 2nd.... must be in kenya");
    }
}
