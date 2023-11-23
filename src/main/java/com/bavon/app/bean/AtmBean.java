package com.bavon.app.bean;

import com.bavon.app.model.Atm;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class AtmBean implements AtmBeanI{

    BigDecimal statefulBalance = BigDecimal.valueOf(300000);

    @Override
    public List<Atm> list(Object entity) {
        List<Atm> atm = new ArrayList<>();
        atm.add(new Atm(statefulBalance));

        return atm;
    }

    @Override
    public void addOrUpdate(Atm atm) {
        statefulBalance = statefulBalance.subtract(atm.getWithdrawAmount());
    }

    @Override
    public void delete(Atm entity) {

    }
}
