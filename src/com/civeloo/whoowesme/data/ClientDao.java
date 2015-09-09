package com.civeloo.whoowesme.data;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.civeloo.whoowesme.logic.Client;

/**
 * Created by DeG on 3/12/13.
 */
public class ClientDao extends Client {
    SQLiteDatabase db;

    /** CONEXION A LA BASE **/
    public ClientDao(Context c) {
        try {
            db = new DBHelperClient(c).open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** SETEO DE VARIABLES LUEGO DE UNA CONSULTA **/
    private Client rowMapper(Cursor c) {
        // NO EXISTE EL CAMPO, NO CONTINUO !
        if (c.getCount() == 0) return new Client();
        Client row = new Client();
        c.moveToNext();
        row.setId(c.getString(0));
        row.setName(c.getString(1));
        row.setAddressbook(c.getString(2));
        row.setNotes(c.getString(3));
        c.close();
        return row;
    }

    /** CONSULTA POR CODIGO **/
    public Client findByPrimaryKey(String id) {
        String where = DBUtil.TCLI_ID + "='" + id + "'";
        Cursor c = db.query(DBUtil.TBL_CLI, DBUtil.TCLI_COLS, where, null,
                null, null, null);
        return rowMapper(c);
    }

    /** INTERNAL JAVA FUNCTIONS FOR BEETWEN **/
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((db == null) ? 0 : db.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClientDao other = (ClientDao) obj;
        if (db == null) {
            if (other.db != null)
                return false;
        } else if (!db.equals(other.db))
            return false;
        return true;
    }

    /** CONSULTA SIN FILTROS **/
    public Client find() {
        Cursor c = db.rawQuery("SELECT * FROM " + DBUtil.TBL_CLI, null);
        return rowMapper(c);
    }

    /** CONSULTA SIN FILTROS QUE DEVUELVE CURSOR **/
    public Cursor getDataTo() {
        Cursor c = db.rawQuery("SELECT * FROM " + DBUtil.TBL_CLI, null);
        return c;
    }

    /** SETEO DE VALORES PARA AGREGAR Y ACTUALIZAR FILAS A LA TABLA **/
    private ContentValues loadObject(Client a) {
        ContentValues c = new ContentValues();
        c.put(DBUtil.TCLI_ID,
                (a.getId() != null && a.getId() != "") ? a.getId() : "");
        c.put(DBUtil.TCLI_NAME,
                (a.getName() != null && a.getName() != "") ? a.getName()
                        : "");
        c.put(DBUtil.TCLI_ADDRESSBOOK,
                (a.getAddressbook() != null && a.getAddressbook() != "") ? a.getAddressbook()
                        : "");
        c.put(DBUtil.TCLI_NOTES,
                (a.getNotes() != null && a.getNotes() != "") ? a.getNotes()
                        : "");
        return c;
    }

    /** INSERCION DE FILAS EN UNA TABLA **/
    public void insert(Client a) {
        db.insert(DBUtil.TBL_CLI, null, loadObject(a));
    }

    /** ACTUALIZACION DE CAMPOS DE UNA FILA EN UNA TABLA CORRESPONDIENTE A UN CODIGO **/
    public void update(Client a) {
        db.update(DBUtil.TBL_CLI, loadObject(a),
                DBUtil.TCLI_ID + "='" + a.getId() + "'", null);
    }

    /** ELIMINACION DE UNA FILA EN UNA TABLA CORRESPONDIENTE A UN CODIGO **/
    public void delete(String id) {
        String where = DBUtil.TCLI_ID + "='" + id + "'" + " OR "
                + DBUtil.TCLI_NAME + "='" + id + "'"
                + "";
        db.delete(DBUtil.TBL_CLI, where, null);
    }

    /** INDICE **/
    public void createIndex() {
        db.execSQL("CREATE UNIQUE INDEX idx_client ON client (id,contact)");

    }
}
