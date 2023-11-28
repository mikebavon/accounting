package com.bavon.app.model;

import com.bavon.app.view.helper.*;
import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;

import java.math.BigDecimal;
import java.util.Date;

@DbTable(name = "payments")
@HtmlTable(addUrl = "./payments?action=add")
@HtmlForm(label = "Payment Voucher", url = "./payments")
public class Payment extends BaseEntity {

    @HtmlTableColHeader(header = "Date", dateFormat = "dd/MM/yyy")
    @HtmlFormField(label = "Payment Date", type = HtmlFormFieldType.DATE, required = true)
    private Date txnDate;

    @DbTableColumn(name = "voucher_no")
    @HtmlTableColHeader(header = "Voucher Number")
    private String voucherNo;

    @DbTableColumn(name = "total", definition = "decimal(10,2)")
    @HtmlTableColHeader(header = "Total", numberFormat = "#,###.##")
    @HtmlFormField(label = "Total", required = true)
    private BigDecimal total;

    @DbTableColumn(name = "narration")
    @HtmlTableColHeader(header = "Narration")
    @HtmlFormField(label = "Narration", required = true)
    private String narration;

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
