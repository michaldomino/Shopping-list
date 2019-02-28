package me.leahcim333.shoppinglist.ui.screens.list_selection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import me.leahcim333.shoppinglist.R;
import me.leahcim333.shoppinglist.data.database.DBHelper;

import static me.leahcim333.shoppinglist.data.database.DatabaseContract.List;

class ListSelectionPresenter implements ListSelectionContract.Presenter {

    private ListSelectionContract.View view;

    private DBHelper dbHelper;

    private LinearLayout parentLinearLayout;

    ListSelectionPresenter(ListSelectionContract.View view, LinearLayout parentLinearLayout, DBHelper dbHelper) {
        this.view = view;
        this.parentLinearLayout = parentLinearLayout;
        this.dbHelper = dbHelper;
    }

    @Override
    public void start() {
//        loadLists();
        saveLists();
    }

    private void saveLists() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete(DatabaseContract.Entry.TABLE_NAME, null, null);
        boolean saveSuccessful = dbHelper.insertList("abc");
//        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
//            LinearLayout linearLayout = (LinearLayout) parentLinearLayout.getChildAt(i);
//            CheckBox checkBox = linearLayout.findViewById(R.id.row_checkbox);
//            EditText editText = linearLayout.findViewById(R.id.row_edit_text);
//            boolean isInserted = dbHelper.insertEntry((long) (checkBox.isChecked() ? 1 : 0), editText.getText().toString());
//            if (!isInserted)
//                saveSuccessful = false;
//        }
        if (saveSuccessful)
            view.showToast(view.getString(R.string.save_successful));
        else
            view.showToast(view.getString(R.string.save_unsuccessful));
    }

    private void loadLists() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {List.COLUMN_NAME_ID, List.COLUMN_NAME_LISTNAME};
        Cursor cursor = db.query(List.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String listName = cursor.getString(0);
            addRow(listName);
        }
    }

    private void addRow(String listName) {
        LayoutInflater inflater = view.getInflater();
        final View rowView = inflater.inflate(R.layout.list_selection_row, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
    }
}
