package com.civeloo.whoowesme.data;

import android.os.Environment;

/**
 * Created by DeG on 3/12/13.
 */
public class DBUtil {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "whoowesme.db";
    //
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String DB_FILE_IMPORT = "import.db";
    public static final String DB_FULLPATH = Environment.getDataDirectory()
            .getPath() + "/data/com.civeloo.whoowesme/databases/";
    public static final String PATH_SDCARD = Environment
            .getExternalStorageDirectory().getPath();// "/mnt/sdcard/";

    // DEFINING OF TABLES
    public static final String TBL_CLI = "client";
    public static final String TBL_CON = "contact";
    public static final String TBL_DEB = "debtor";
    public static final String TBL_SET = "setting";

    // TABLE OF CLIENTS
    public static final String TCLI_ID = "id";
    public static final String TCLI_NAME = "name";
    public static final String TCLI_ADDRESSBOOK = "addressbook";
    public static final String TCLI_NOTES = "notes";

    // INSERT AND UPDATES
    public static final String[] TCLI_COLS = { TCLI_ID, TCLI_NAME, TCLI_ADDRESSBOOK, TCLI_NOTES };

    public static final String TCLI_ALLCOLS = TCLI_ID + "," + TCLI_NAME + "," + TCLI_ADDRESSBOOK + "," + TCLI_NOTES + ","
            ;

    // TABLE OF CONTACTS
    public static final String TCON_CLIENT = "client";
    public static final String TCON_TYPE = "type";
    public static final String TCON_FIRSTNAME = "firstname";
    public static final String TCON_LASTNAME = "lastname";
    public static final String TCON_GENDER = "gender";
    public static final String TCON_COMPANY = "company";
    public static final String TCON_ADDRESS = "address";
    public static final String TCON_SUBURB = "suburb";
    public static final String TCON_POSTCODE = "postcode";
    public static final String TCON_CITY = "city";
    public static final String TCON_STATE = "state";
    public static final String TCON_COUNTRY = "country";
    public static final String TCON_ZONE = "zone";
    public static final String TCON_EMAIL = "email";
    public static final String TCON_TELEPHONE = "telephone";
    public static final String TCON_MOBILE = "mobile";
    public static final String TCON_NOTES = "notes";

    public static final String[] TCON_COLS = { TCON_CLIENT, TCON_TYPE,
            TCON_FIRSTNAME, TCON_LASTNAME, TCON_GENDER, TCON_COMPANY,
            TCON_ADDRESS, TCON_SUBURB, TCON_POSTCODE, TCON_CITY, TCON_STATE,
            TCON_COUNTRY, TCON_ZONE, TCON_EMAIL, TCON_TELEPHONE, TCON_MOBILE,
            TCON_NOTES };

    public static final String TCON_ALLCOLS = TCON_CLIENT + "," + TCON_TYPE + ","
            + TCON_FIRSTNAME + "," + TCON_LASTNAME + "," + TCON_GENDER + ","
            + TCON_COMPANY + "," + TCON_ADDRESS + "," + TCON_SUBURB + ","
            + TCON_POSTCODE + "," + TCON_CITY + "," + TCON_STATE + ","
            + TCON_COUNTRY + "," + TCON_ZONE + "," + TCON_EMAIL + ","
            + TCON_TELEPHONE + "," + TCON_MOBILE + "," + TCON_NOTES;

    // TABLE OF DEBTORS
    public static final String TDEB_ID = "id";
    public static final String TDEB_CLIENT = "client";
    public static final String TDEB_DATE = "date";
    public static final String TDEB_DEBIT = "debit";
    public static final String TDEB_CREDIT = "credit";

    // USADOS EN LOS FIND, INSERT AND UPDATES
    public static final String[] TDEB_COLS = { TDEB_ID, TDEB_CLIENT, TDEB_DATE,
            TDEB_DEBIT, TDEB_CREDIT };

    // TABLE OF SETTINGS FOR APP
    public static final String TSET_URL = "url";
    public static final String TSET_PERMISSION = "permission";
    public static final String[] TSET_COLS = { TSET_URL, TSET_PERMISSION };
    public static final String TSET_ALLCOLS = TSET_URL + "," + TSET_PERMISSION;
}
