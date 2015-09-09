package com.civeloo.whoowesme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by DeG on 3/12/13.
 */
public class DebtorDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    public String failTitle = "";
    public String failMsg = "";

    /**
     * *************************** PUBLIC METHODS **********************************
     */
    public DebtorDBHelper(Context context) {
    	super(context, DBUtil.DATABASE_NAME, null, DBUtil.DATABASE_VERSION);
    }

    /**
     * CUANDO HAY CAMBIO DE VERSION DE LA DB *
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String erase = ";DROP TABLE IF EXISTS " + DBUtil.TBL_DEB
                + ";"
                //+"DROP TABLE IF EXISTS " + DBUtil.TBL_SER + ";"
                ;
        try {
            // String erase = "DROP TABLE IF EXISTS " + DBUtil.TBL_INV
            // + ";DROP TABLE IF EXISTS " + DBUtil.TBL_SER + ";";
            db.execSQL(erase);
            Log.w(TAG, "LOG: Database erased...");
        } catch (Exception e) {
            failTitle = "Error al borrar base de datos";
            failMsg = e.getMessage();
            e.printStackTrace();
        }
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS " + DBUtil.TBL_DEB
                    + "( "
                    + DBUtil.TDEB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DBUtil.TDEB_CLIENT + " VARCHAR(50),"
                    + DBUtil.TDEB_DATE + " TIMESTAMP,"
                    + DBUtil.TDEB_DEBIT + " DECIMAL(10,3),"
                    + DBUtil.TDEB_CREDIT + " DECIMAL(10,3));"
            );

            Log.w(TAG, "LOG: Database Created...");
        } catch (Exception e) {
            failTitle = "Error al crear base de datos";
            failMsg = e.getMessage();
            e.printStackTrace();
        }
    }

    public SQLiteDatabase open() throws SQLException {
        //return this.getWritableDatabase();
    	return this.getReadableDatabase();
    }

    public void close() {
        this.close();
    }
}