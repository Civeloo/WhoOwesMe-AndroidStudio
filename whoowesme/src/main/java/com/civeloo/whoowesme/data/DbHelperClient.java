package com.civeloo.whoowesme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by DeG on 3/12/13.
 */
public class DbHelperClient extends SQLiteOpenHelper {
    private static final String TAG = "DbHelperClient";
    public String failTitle = "";
    public String failMsg = "";

    /****************************** PUBLIC METHODS ***********************************/
    public DbHelperClient(Context context) {
        super(context, DBUtil.DB_FILE_IMPORT, null, DBUtil.DATABASE_VERSION);
    }

    /** CUANDO HAY CAMBIO DE VERSION DE LA DB **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            // String erase = "DROP TABLE IF EXISTS " + DBUtil.TBL_ART
            // + ";DROP TABLE IF EXISTS " + DBUtil.TBL_LOC + ";";
            // db.execSQL(erase);
            Log.d(TAG, "[DEBUG]: Database erased....................[ OK ]");
        } catch (Exception e) {
            failTitle = "Error al borrar base de datos";
            failMsg = e.toString();
            e.printStackTrace();
        }
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS " + DBUtil.TBL_CLI
                    + " ( " + DBUtil.TCLI_ID + " VARCHAR(50),"
                    + DBUtil.TCLI_NAME + " VARCHAR(50),"
                    + DBUtil.TCLI_ADDRESSBOOK + " VARCHAR(50),"
                    + DBUtil.TCLI_NOTES + " VARCHAR(255) " + ");");
            database.execSQL("CREATE TABLE IF NOT EXISTS " + DBUtil.TBL_CON
                    + " ( " + DBUtil.TCON_CLIENT + " VARCHAR(50), "
                    + DBUtil.TCON_TYPE + " INTEGER, " + DBUtil.TCON_FIRSTNAME
                    + " VARCHAR(50), " + DBUtil.TCON_LASTNAME
                    + " VARCHAR(50), " + DBUtil.TCON_GENDER + " VARCHAR(50), "
                    + DBUtil.TCON_COMPANY + " VARCHAR(50), "
                    + DBUtil.TCON_ADDRESS + " VARCHAR(50), "
                    + DBUtil.TCON_SUBURB + " VARCHAR(50), "
                    + DBUtil.TCON_POSTCODE + " VARCHAR(50), "
                    + DBUtil.TCON_CITY + " VARCHAR(50), " + DBUtil.TCON_STATE
                    + " VARCHAR(50), " + DBUtil.TCON_COUNTRY + " VARCHAR(50), "
                    + DBUtil.TCON_ZONE + " VARCHAR(50), " + DBUtil.TCON_EMAIL
                    + " VARCHAR(50), " + DBUtil.TCON_TELEPHONE
                    + " VARCHAR(50), " + DBUtil.TCON_MOBILE + " VARCHAR(50), "
                    + DBUtil.TCON_NOTES + " VARCHAR(255) " + ");");
            database.execSQL("CREATE TABLE IF NOT EXISTS " + DBUtil.TBL_SET
                    + " ( " + DBUtil.TSET_URL + " VARCHAR(20), "
                    + DBUtil.TSET_PERMISSION + " INTEGER " + ");");
            Log.d(TAG, "[DEBUG]: Database Created...............[ OK ]");
        } catch (Exception e) {
            failTitle = "Error al borrar base de datos";
            failMsg = e.getMessage();
            Log.e(TAG, "[ERROR]: " + e.getMessage());
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }
}
