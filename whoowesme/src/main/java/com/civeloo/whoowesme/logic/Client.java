package com.civeloo.whoowesme.logic;

/**
 * Created by DeG on 3/12/13.
 */
public class Client {
    private String id;
    private String name;
    private String addressbook;
    private String notes;

    public boolean exist() {
        return (this.name != null && this.name != "") ? true : false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressbook() {
        return addressbook;
    }

    public void setAddressbook(String addressbook) {
        this.addressbook = addressbook;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
