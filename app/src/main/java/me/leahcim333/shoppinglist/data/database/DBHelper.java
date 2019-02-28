package me.leahcim333.shoppinglist.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static me.leahcim333.shoppinglist.data.database.DatabaseContract.Entry;
import static me.leahcim333.shoppinglist.data.database.DatabaseContract.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dataBase.db";

    public static final String SQL_CREATE_LISTS =
            "CREATE TABLE " + List.TABLE_NAME + " (" +
                    List.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    List.COLUMN_NAME_LISTNAME + "TEXT NOT NULL)";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                    Entry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Entry.COLUMN_NAME_CHECKED + " INTEGER NOT NULL," +
                    Entry.COLUMN_NAME_PRODUCT_NAME + " TEXT NOT NULL," +
                    Entry.COLUMN_NAME_LIST_ID + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + Entry.COLUMN_NAME_LIST_ID + ") " + "REFERENCES " + List.TABLE_NAME + "(" + List.COLUMN_NAME_ID + ") ON DELETE CASCADE);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;

    private static final String SQL_DELETE_LISTS =
            "DROP TABLE IF EXISTS " + List.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LISTS);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_LISTS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertEntry(Long checked, String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Entry.COLUMN_NAME_CHECKED, checked);
        contentValues.put(Entry.COLUMN_NAME_PRODUCT_NAME, productName);
        long result = db.insert(Entry.TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean insertList(String listName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(List.COLUMN_NAME_LISTNAME, listName);
        long result = db.insert(List.TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
