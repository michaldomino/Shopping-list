package me.leahcim333.shoppinglist.ui.screens.list_selection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import me.leahcim333.shoppinglist.data.database.DBHelper;
import static me.leahcim333.shoppinglist.data.database.DatabaseContract.List;

class ListSelectionPresenter implements ListSelectionContract.Presenter {

    ListSelectionContract.View view;
    DBHelper dbHelper;

    ListSelectionPresenter(ListSelectionContract.View view, DBHelper dbHelper) {
        this.view = view;
        this.dbHelper = dbHelper;
    }

    @Override
    public void start() {
        loadLists();
    }

    private void loadLists() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {List.COLUMN_NAME_LISTNAME};
        Cursor cursor = db.query(List.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String listName = cursor.getString(0);
            addRow(listName);
        }
    }

    private void addRow(String listName) {
        
    }
}
