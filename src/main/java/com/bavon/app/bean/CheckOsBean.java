package com.bavon.app.bean;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.Serializable;

@Singleton
public class CheckOsBean implements Serializable {

    @PostConstruct
    public void init(){
        System.out.println("Executed 1st.... in window get a curtain.");
    }
}
