package com.bavon.app.model;

import com.bavon.app.view.helper.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payments")
@HtmlTable(addUrl = "./payments?action=add")
@HtmlForm(label = "Payment Voucher", url = "./payments")
public class Payment extends BaseEntity {

    @Column(name = "txn_date")
    @Temporal(TemporalType.DATE)
    @HtmlTableColHeader(header = "Date", dateFormat = "dd/MM/yyy")
    @HtmlFormField(label = "Payment Date", type = HtmlFormFieldType.DATE, required = true)
    private Date txnDate;

    @Column(name = "voucher_no", nullable = false, unique = true)
    @HtmlTableColHeader(header = "Voucher Number")
    private String voucherNo;

    @Column(name = "total")
    @HtmlTableColHeader(header = "Total", numberFormat = "#,###.##")
    @HtmlFormField(label = "Total", required = true)
    private BigDecimal total;

    @Column(name = "narration", columnDefinition = "longtext")
    @HtmlTableColHeader(header = "Narration")
    @HtmlFormField(label = "Narration", required = true)
    private String narration;

    @Transient
    private Journal journal;

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
