package com.civeloo.whoowesme.data;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.civeloo.whoowesme.logic.Contact;

/**
 * Created by DeG on 3/12/13.
 */
public class ContactDao {
    SQLiteDatabase db;

    /** CONEXION A LA BASE **/
    public ContactDao(Context c) {
        try {
            db = (new DBHelperClient(c)).open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** SETEO DE VARIABLES LUEGO DE UNA CONSULTA **/
    private Contact rowMapper(Cursor c) {
        // NO EXISTE EL CAMPO, NO CONTINUO !
        if (c.getCount() == 0)
            return new Contact();
        Contact row = new Contact();
        c.moveToNext();
        row.setId(c.getInt(0));
        row.setType(c.getInt(1));
        row.setGender(c.getString(2));
        row.setCompany(c.getString(3));
        row.setFirstname(c.getString(4));
        row.setLastname(c.getString(5));
        row.setAddress(c.getString(6));
        row.setSuburb(c.getString(7));
        row.setPostcode(c.getString(8));
        row.setCity(c.getString(9));
        row.setState(c.getString(10));
        row.setCountry(c.getString(11));
        row.setZone(c.getString(12));
        row.setEmail(c.getString(13));
        row.setTelephone(c.getString(14));
        row.setMobile(c.getString(15));
        row.setNotes(c.getString(16));
        c.close();
        return row;
    }

    /** CONSULTA POR CODIGO **/
    public Contact findByPrimaryKey(String id) {
        String where = DBUtil.TCON_CLIENT + "= '" + id + "'";
        Cursor c = db.query(DBUtil.TBL_CON, DBUtil.TCON_COLS, where, null,
                null, null, null);
        return rowMapper(c);
    }

    /** CONSULTA SIN FILTROS **/
    public Contact find() {
        Cursor c = db.rawQuery("SELECT * FROM " + DBUtil.TBL_CON, null);
        return rowMapper(c);
    }

    /** CONSULTA SIN FILTROS QUE DEVUELVE CURSOR **/
    public Cursor getDataToSpinner() {
        Cursor c = db.rawQuery("SELECT * FROM " + DBUtil.TBL_CON, null);
        return c;
    }

    /** SETEO DE VALORES PARA AGREGAR Y ACTUALIZAR FILAS A LA TABLA **/
    private ContentValues loadObject(Contact con) {
        ContentValues c = new ContentValues();
        c.put(DBUtil.TCON_CLIENT,
                (con.getId() != null && con.getId() != 0) ? con.getId() : 0);
        c.put(DBUtil.TCON_TYPE,
                (con.getType() != null && con.getType() != 0) ? con.getType()
                        : 0);
        c.put(DBUtil.TCON_GENDER,
                (con.getGender() != null && con.getGender() != "") ? con
                        .getGender() : "");
        c.put(DBUtil.TCON_COMPANY, (con.getCompany() != null && con
                .getCompany() != "") ? con.getCompany() : "");
        c.put(DBUtil.TCON_FIRSTNAME, (con.getFirstname() != null && con
                .getFirstname() != "") ? con.getFirstname() : "");
        c.put(DBUtil.TCON_LASTNAME, (con.getLastname() != null && con
                .getLastname() != "") ? con.getLastname() : "");
        c.put(DBUtil.TCON_ADDRESS, (con.getAddress() != null && con
                .getAddress() != "") ? con.getAddress() : "");
        c.put(DBUtil.TCON_SUBURB,
                (con.getSuburb() != null && con.getSuburb() != "") ? con
                        .getSuburb() : "");
        c.put(DBUtil.TCON_POSTCODE, (con.getPostcode() != null && con
                .getPostcode() != "") ? con.getPostcode() : "");
        c.put(DBUtil.TCON_CITY,
                (con.getCity() != null && con.getCity() != "") ? con.getCity()
                        : "");
        c.put(DBUtil.TCON_STATE,
                (con.getState() != null && con.getState() != "") ? con
                        .getState() : "");
        c.put(DBUtil.TCON_COUNTRY, (con.getCountry() != null && con
                .getCountry() != "") ? con.getCountry() : "");
        c.put(DBUtil.TCON_ZONE,
                (con.getZone() != null && con.getZone() != "") ? con.getZone()
                        : "");
        c.put(DBUtil.TCON_EMAIL,
                (con.getEmail() != null && con.getEmail() != "") ? con
                        .getEmail() : "");
        c.put(DBUtil.TCON_TELEPHONE, (con.getTelephone() != null && con
                .getTelephone() != "") ? con.getTelephone() : "");
        c.put(DBUtil.TCON_MOBILE,
                (con.getMobile() != null && con.getMobile() != "") ? con
                        .getMobile() : "");
        c.put(DBUtil.TCON_NOTES,
                (con.getNotes() != null && con.getNotes() != "") ? con
                        .getNotes() : "");

        return c;
    }

    /** INSERCION DE FILAS EN UNA TABLA **/
    public void insert(Contact id) {
        db.insert(DBUtil.TBL_CON, null, loadObject(id));
    }

    /** ACTUALIZACION DE CAMPOS DE UNA FILA EN UNA TABLA CORRESPONDIENTE A UN CODIGO **/
    public void update(Contact id) {
        db.update(DBUtil.TBL_CON, loadObject(id),
                DBUtil.TCON_CLIENT + "= '" + id.getId() + "'", null);
    }

    /** ELIMINACION DE UNA FILA EN UNA TABLA CORRESPONDIENTE A UN CODIGO **/
    public void delete(String id) {
        String where = DBUtil.TCON_CLIENT + "= '" + id + "'";
        db.delete(DBUtil.TBL_CON, where, null);
    }
}
