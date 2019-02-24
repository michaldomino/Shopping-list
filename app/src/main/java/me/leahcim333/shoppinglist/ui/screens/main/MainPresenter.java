package me.leahcim333.shoppinglist.ui.screens.main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import me.leahcim333.shoppinglist.R;
import me.leahcim333.shoppinglist.data.database.DBHelper;
import me.leahcim333.shoppinglist.ui.screens.list_selection.ListSelectionActivity;

import static me.leahcim333.shoppinglist.data.database.DatabaseContract.Entry;

class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    private LinearLayout parentLinearLayout;

    private EditText bottomEditText = null;

    DBHelper dbHelper;

    MainPresenter(MainContract.View view, LinearLayout parentLinearLayout, DBHelper db) {
        this.view = view;
        this.parentLinearLayout = parentLinearLayout;
        this.dbHelper = db;
    }

    @Override
    public void start() {
        addRow();
    }

    @Override
    public void onDeleteButtonClicked(View view) {
        removeRow(view);
        setBottomEditText();
    }

    @Override
    public void onClearOptionsItemSelected() {
        clearScreen();
    }

    @Override
    public void onSaveListOptionsItemSelected() {
        saveListToDatabase();
    }

    @Override
    public void onLoadListOptionsItemSelected() {
//        loadListFromDatabase();
        view.startNewActivity(ListSelectionActivity.class);
    }

    @Override
    public void onFloatingActionButtonAddClicked() {
        view.startVoiceRecognizer();
    }

    @Override
    public void addTextFromSpeechRecognizer(Intent data) {
        ArrayList<String> possibleMatches =
                data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String[] productList = possibleMatches.get(0).split(view.getString(R.string.products_separator));
        for (String productName : productList) {
            addRow(false, productName);
        }
    }

    private TextWatcher bottomEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String entry = s.toString();

            if (!entry.isEmpty()) {
                addRow();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void addRow() {
        if (bottomEditText != null)
            bottomEditText.removeTextChangedListener(bottomEditTextWatcher);
        LayoutInflater inflater = view.getInflater();
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
        setBottomEditText();
    }

    private void addRow(boolean checked, String productName) {
        getBottomCheckbox().setChecked(checked);
        bottomEditText.setText(productName);
        setBottomEditText();
    }

    private void clearScreen() {
        parentLinearLayout.removeViews(1, parentLinearLayout.getChildCount() - 1);
        view.clearFirstRow();
        setBottomEditText();
    }

    private void removeRow(View view) {
        parentLinearLayout.removeView((View) view.getParent());
    }

    private void setBottomEditText() {
        if (bottomEditText != null)
            getBottomDeleteButton().setVisibility(View.VISIBLE);
        bottomEditText = parentLinearLayout
                .getChildAt(parentLinearLayout.getChildCount() - 1)
                .findViewById(R.id.row_edit_text);
        bottomEditText.removeTextChangedListener(bottomEditTextWatcher);
        bottomEditText.addTextChangedListener(bottomEditTextWatcher);
        getBottomDeleteButton().setVisibility(View.INVISIBLE);
    }

    private Button getBottomDeleteButton() {
        LinearLayout linearLayout = (LinearLayout) bottomEditText.getParent();
        return linearLayout.findViewById(R.id.delete_button);
    }

    private CheckBox getBottomCheckbox() {
        LinearLayout linearLayout = (LinearLayout) bottomEditText.getParent();
        return linearLayout.findViewById(R.id.row_checkbox);
    }

    private void saveListToDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Entry.TABLE_NAME, null, null);
        boolean saveSuccessful = true;
        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
            LinearLayout linearLayout = (LinearLayout) parentLinearLayout.getChildAt(i);
            CheckBox checkBox = linearLayout.findViewById(R.id.row_checkbox);
            EditText editText = linearLayout.findViewById(R.id.row_edit_text);
            boolean isInserted = dbHelper.insertData((long) (checkBox.isChecked() ? 1 : 0), editText.getText().toString());
            if (!isInserted)
                saveSuccessful = false;
        }
        if (saveSuccessful)
            view.showToast(view.getString(R.string.save_successful));
        else
            view.showToast(view.getString(R.string.save_unsuccessful));
    }

    private void loadListFromDatabase() {
        clearScreen();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {Entry.COLUMN_NAME_CHECKED, Entry.COLUMN_NAME_PRODUCT_NAME};
        Cursor cursor = db.query(Entry.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            boolean checked = cursor.getLong(0) == 1;
            String productName = cursor.getString(1);
            addRow(checked, productName);
        }
    }
}
