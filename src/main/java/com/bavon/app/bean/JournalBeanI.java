package com.bavon.app.bean;

import com.bavon.app.model.Journal;

import javax.ejb.Remote;

@Remote
public interface JournalBeanI extends GenericBeanI<Journal> {
}
