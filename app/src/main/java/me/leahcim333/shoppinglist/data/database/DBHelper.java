package me.leahcim333.shoppinglist.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dataBase.db";
    public static final String TABLE_NAME = "productTB";

    public static final String COLUMN_NAME_ID = "ID";
    public static final String COLUMN_NAME_CHECKED = "CHECKED";
    public static final String COLUMN_NAME_PRODUCT_NAME = "PRODUCT_NAME";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CHECKED + " INTEGER," +
                    COLUMN_NAME_PRODUCT_NAME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public boolean insertData(Long checked, String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CHECKED, checked);
        contentValues.put(COLUMN_NAME_PRODUCT_NAME, productName);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
