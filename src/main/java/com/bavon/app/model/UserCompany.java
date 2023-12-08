package com.bavon.app.model;

import javax.persistence.*;

@Entity
@Table(name = "user_companies")
public class UserCompany extends BaseEntity{

    @ManyToOne
    private User user;

    @ManyToOne
    private Company company;

    @Column(name = "journal_access")
    private boolean journalAccess;

    @Column(name = "invoice_access")
    private boolean invoiceAccess;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isJournalAccess() {
        return journalAccess;
    }

    public void setJournalAccess(boolean journalAccess) {
        this.journalAccess = journalAccess;
    }

    public boolean isInvoiceAccess() {
        return invoiceAccess;
    }

    public void setInvoiceAccess(boolean invoiceAccess) {
        this.invoiceAccess = invoiceAccess;
    }
}
