package com.civeloo.whoowesme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.civeloo.whoowesme.data.DebtorContract.*;

/**
 * Created by DeG on 23/04/14.
 */
public class DebtorDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 4;
        public static final String DATABASE_NAME = "whoowesme.db";

        private static final String TIMESTAMP_TYPE = " TIMESTAMP";
        private static final String TEXT_TYPE = " TEXT";
        private static final String DECIMAL_TYPE = " DECIMAL(10,3)";
        private static final String PRIMARY_KEY_TYPE = " INTEGER PRIMARY KEY";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + Debtor.TABLE_NAME
                        + "( "
                        + Debtor.COLUMN_NAME_ID + PRIMARY_KEY_TYPE + COMMA_SEP
                        + Debtor.COLUMN_NAME_CLIENT + TEXT_TYPE + COMMA_SEP
                        + Debtor.COLUMN_NAME_DATE + TIMESTAMP_TYPE + COMMA_SEP
                        + Debtor.COLUMN_NAME_DEBIT + DECIMAL_TYPE + COMMA_SEP
                        + Debtor.COLUMN_NAME_CREDIT + DECIMAL_TYPE +
                        ")";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Debtor.TABLE_NAME;

        public DebtorDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
