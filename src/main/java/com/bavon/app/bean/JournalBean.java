package com.bavon.app.bean;

import com.bavon.app.model.Journal;
import com.bavon.app.utility.JournalNoGenerator;
import com.bavon.app.utility.JournalValidator;
import com.bavon.app.utility.TransactionNo;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Date;

@Stateless
public class JournalBean extends GenericBean<Journal> implements JournalBeanI{

    @Inject
    @Any
    private Instance<TransactionNoGenerator> txnNoGenerators;

    @Inject
    private JournalValidator journalValidator;

    @Override
    public void addOrUpdate(Journal journal) {
        if (journalValidator.inValid(journal))
            throw new RuntimeException("Invalid journal");

        if (journal.getDate() == null)
            journal.setDate(new Date());

        int count = 0;
        for (TransactionNoGenerator txnNoGenerator : txnNoGenerators){
            if (txnNoGenerator.getClass().isAssignableFrom(JournalNoGenerator.class))
                journal.setJournalNo(txnNoGenerator.generate());
            else
                System.out.println("We wont use " + txnNoGenerator.getClass().getSimpleName());

            count++;
        }

        System.out.println("We looped through " + count + " implementations");

        getDao().addOrUpdate(journal);

    }

    @Inject
    public void logInjection(){
        System.out.println("this method is executed through injection...0");
    }

    @Inject
    public void logInjection1(){
        System.out.println("this method is executed through injection....1");
    }

    @Inject
    public void logInjection2(){
        System.out.println("this method is executed through injection....2");
    }

}
