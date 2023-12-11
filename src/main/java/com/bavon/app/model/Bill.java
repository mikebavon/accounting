package com.bavon.app.model;

import com.bavon.app.view.helper.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;

@NamedQueries({
    @NamedQuery(name = Bill.BillAbove1000, query = "FROM Bill b WHERE b.total>1000"),
    @NamedQuery(name = Bill.BillBelow1000, query = "FROM Bill b WHERE b.total<1000"),
    @NamedQuery(name = Bill.TodaysBill, query = "FROM Bill b WHERE b.billDate=CURDATE()")
})
@Entity
@Table(name = "bills")
@HtmlTable(addUrl = "./bills?action=add")
@HtmlForm(label = "Bill", url = "./bills")
public class Bill extends BaseEntity {

    public static final String BillAbove1000 = "Bill.InvoiceAbove1000";
    public static final String BillBelow1000 = "Bill.InvoiceBelow1000";
    public static final String TodaysBill = "Bill.TodaysInvoice";

    @Column(name = "bill_date")
    @Temporal(TemporalType.DATE)
    @HtmlTableColHeader(header = "Date", dateFormat = "dd/MM/yyy")
    @HtmlFormField(label = "Bill Date", type = HtmlFormFieldType.DATE, required = true)
    private Date billDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @HtmlFormField(label = "Vendor", selectList = "vendors", selectValue = "id", selectValueInSuper=true, selectDisplay = "name")
    @Formula("(vendor_id)")
    private Long vendorId;

    @Column(name = "bill_no")
    @HtmlTableColHeader(header = "Bill Number")
    private String billNo;

    @Column(name = "narration",columnDefinition = "text")
    @HtmlTableColHeader(header = "Narration")
    @HtmlFormField(label = "Narration", required = true)
    private String narration;

    @DecimalMin("1.0")
    @Column(name = "total")
    @HtmlTableColHeader(header = "Total", numberFormat = "#,###.##")
    @HtmlFormField(label = "Bill Total", type = HtmlFormFieldType.NUMBER, required = true)
    private BigDecimal total;

    @HtmlFormField(label = "Debit Account", selectList = "accounts", selectValue = "id", selectValueInSuper=true, selectDisplay = "name")
    @Transient
    private Long debitAccountId;


    @HtmlFormField(label = "Credit Account", selectList = "accounts", selectValue = "id", selectValueInSuper=true, selectDisplay = "name")
    @Transient
    private Long creditAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Journal journal;

    @HtmlTableColHeader(header = "Journal No")
    @Formula("(select j.journal_no from journals j where j.id=journal_id)")
    private String journalNo;

    @HtmlTableColHeader(header = "Vendor")
    @Formula("(select v.vendor_name from vendors v where v.id=vendor_id)")
    private String vendorName;

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    @JsonIgnore
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    @JsonIgnore
    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public String getJournalNo() {
        return journalNo;
    }

    public void setJournalNo(String journalNo) {
        this.journalNo = journalNo;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(Long debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public Long getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(Long creditAccountId) {
        this.creditAccountId = creditAccountId;
    }
}
