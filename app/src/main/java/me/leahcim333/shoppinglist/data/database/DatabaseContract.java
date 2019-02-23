package me.leahcim333.shoppinglist.data.database;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {}

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "productTB";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_CHECKED = "CHECKED";
        public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";
        public static final String COLUMN_NAME_LIST_ID = "LIST_ID";
    }

    public static class List implements BaseColumns {
        public static final String TABLE_NAME = "listTB";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_LISTNAME = "LIST_NAME";
    }
}
