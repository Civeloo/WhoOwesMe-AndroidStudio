package com.civeloo.whoowesme.logic;

/**
 * Created by DeG on 3/12/13.
 */
public class DebtorList {
    String id;
    String client;
    String date;
    String debit;
    String credit;

    public DebtorList(String id, String client, String date, String debit, String credit) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.debit = debit;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
