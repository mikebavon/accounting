package com.bavon.app.bean;

import javax.ejb.Stateless;

@Stateless
public class CalculatorBean implements CalculatorBeanI {
    @Override
    public void add() throws RuntimeException {
        System.out.println("This method will add");
    }
}
