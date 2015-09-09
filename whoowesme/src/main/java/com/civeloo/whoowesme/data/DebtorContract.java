package com.civeloo.whoowesme.data;

import android.provider.BaseColumns;

/**
 * Created by DeG on 14/04/14.
 */

public final class DebtorContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DebtorContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Debtor implements BaseColumns {

        // TABLE OF DEBTORS
        public static final String TABLE_NAME = "debtor";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_CLIENT = "client";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DEBIT = "debit";
        public static final String COLUMN_NAME_CREDIT = "credit";
        public static final String COLUMN_NAME_NULLABLE = null;

    }

}
