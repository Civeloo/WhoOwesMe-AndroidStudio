package com.civeloo.whoowesme.logic;

/**
 * Created by DeG on 3/12/13.
 */
public class Debtor {
    private Integer id;// id
    private String client;// id_client
    private String date;// date
    private Double debit;// debit
    private Double credit;// credit

    /**
     * USAR LUEGO DE EJECUTAR EL FINDBYPRYMARYKEY
     *
     * @return boolean
     */

    public boolean exist() {
        return (this.id != null && this.id != 0) ? true : false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
