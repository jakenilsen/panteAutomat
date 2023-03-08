package com.jacob.panteautomat.model;

import java.util.Date;

public class VoucherHistory {

    private String voucherText;

    private Date date;

    public VoucherHistory(String voucherText, Date date) {
        this.voucherText = voucherText;
        this.date = date;
    }

    public String getVoucherText() {
        return voucherText;
    }

    public Date getDate() {
        return date;
    }
}
