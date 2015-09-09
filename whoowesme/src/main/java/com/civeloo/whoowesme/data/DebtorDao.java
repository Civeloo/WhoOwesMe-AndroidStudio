package com.civeloo.whoowesme.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.civeloo.whoowesme.logic.Debtor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DeG on 3/12/13.
 */
public class DebtorDao extends Debtor {
    DbHelper data;
    SQLiteDatabase db;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String nowDate = sdf.format(new Date());

    /** CONEXION A LA BASE **/
/*
    public DebtorDao(Context c) {
        // OPEN CONECTION WITH SQLITE3
        try {
            db = (new DbHelper(c)).open();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
    }
*/
    public DebtorDao(Context c) {
    DbHelper mDbHelper = new DbHelper(c);
    SQLiteDatabase db = mDbHelper.getWritableDatabase();
    }
    /** SETEO DE VARIABLES LUEGO DE UNA CONSULTA **/
    private Debtor rowMapper(Cursor c) {
        // NO EXISTE EL CAMPO, NO CONTINUO !
        if (c.getCount() == 0)
            return new Debtor();

        c.moveToNext();
        Debtor row = new Debtor();
        row.setId(c.getInt(0));
        row.setClient(c.getString(1));
        row.setDate(c.getString(2));
        row.setDebit(c.getDouble(3));
        row.setCredit(c.getDouble(4));
        c.close();
        return row;
    }

    /** CONSULTA POR CODIGO **/
    public Debtor findByPrimaryKey(String id, String client) {
        String where = "(" + DBUtil.TDEB_ID + "='" + id + "'" + " OR "
                + DBUtil.TDEB_CLIENT + "='" + client + "'"
                //+ ") and " + DBUtil.TDEB_DATE + "='" + date
                + ")'";
        Cursor c = db.query(DBUtil.TBL_DEB, DBUtil.TDEB_COLS, where, null,
                null, null, null);
        return rowMapper(c);
    }

    /** CONSULTA SIN FILTROS **/
    public Debtor find() {
        Cursor c = db.rawQuery("SELECT * FROM " + DBUtil.TBL_DEB, null);
        return rowMapper(c);
    }

    /** CONSULTA SIN FILTROS (CURSOR) **/
    public Cursor getDataTo() {
        Cursor c = db.rawQuery("SELECT * FROM " + DBUtil.TBL_DEB, null);
        return c;
    }

    /** DEVOLVER CURSOR POR CLAVE **/
    public Cursor getCbPk(String key) {
        String where = "("
                + DBUtil.TDEB_CLIENT + "='" + key + "'"
                + ")";
        Cursor c = db.query(DBUtil.TBL_DEB, DBUtil.TDEB_COLS, where, null, null, null, null);
        return c;
    }

    /** SETEO DE VALORES PARA AGREGAR Y ACTUALIZAR FILAS A LA TABLA **/
    // Create a new map of values, where column names are the keys
    private ContentValues loadObject(Debtor a) {
        ContentValues c = new ContentValues();
        c.put(DBUtil.TDEB_ID,
                (a.getId() != null && a.getId() != 0) ? a.getId() : 0);
        c.put(DBUtil.TDEB_CLIENT,
                (a.getClient() != null && a.getClient() != "") ? a.getClient()
                        : "");
        c.put(DBUtil.TDEB_DATE,
                (a.getDate() != null && a.getDate() != "") ? a.getDate()
                        : nowDate);
        c.put(DBUtil.TDEB_DEBIT,
                (a.getDebit() != null && a.getDebit() != 0) ? a
                        .getDebit() : 0);
        c.put(DBUtil.TDEB_CREDIT,
                (a.getCredit() != null && a.getCredit() != 0) ? a
                        .getCredit() : 0);
        return c;
    }

    /** INSERCION DE FILAS EN UNA TABLA **/

    public void insert(Debtor a) {
        //db.insert(DBUtil.TBL_DEB, null, loadObject(a));
        DbHelper mDbHelper = new DbHelper(getContext());
        // Gets the data repository in write mode
        SQLiteDatabase db1 = mDbHelper.getWritableDatabase();
        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db1.insert(DBUtil.TBL_DEB, null, loadObject(a));
    }




    /** ACTUALIZACION DE CAMPOS DE UNA FILA EN UNA TABLA CORRESPONDIENTE A UN CODIGO **/
    public void update(Debtor a) {
        String where = "(" + DBUtil.TDEB_ID + "='" + a.getId() + "'" + " OR "
                + DBUtil.TDEB_CLIENT + "='" + a.getClient() + "'"
                //+ ") and " + DBUtil.TDEB_DATE + "='" + a.getDate()
                + ")'";
        db.update(DBUtil.TBL_DEB, loadObject(a), where, null);
    }

    /** ELIMINACION DE UNA FILA EN UNA TABLA CORRESPONDIENTE A UN CODIGO **/
    public void delete(String id, String client) {
        String where = "(" + DBUtil.TDEB_ID + "='" + id + "'" + " OR "
                + DBUtil.TDEB_CLIENT + "='" + client + "'"
                //+ ") and " + DBUtil.TDEB_DATE + "='" + location
                + ")'";
        db.delete(DBUtil.TBL_DEB, where, null);
    }

    /** ELIMINACION DE TODOS LOS REGISTROS DE UNA TABLA **/
    public void deleteAll() {
        db.delete(DBUtil.TBL_DEB, null, null);
    }

    /** DEVUELVE LA CONEXION A LA BASE **/
    public SQLiteDatabase getDBConnection() {
        return this.db;
    }
}