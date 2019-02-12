package me.leahcim333.shoppinglist.ui.screens.main;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import me.leahcim333.shoppinglist.R;

class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    private LinearLayout parentLinearLayout;

    private EditText bottomEditText;

    MainPresenter(MainContract.View view, LinearLayout parentLinearLayout) {
        this.view = view;
        this.parentLinearLayout = parentLinearLayout;
    }

    @Override
    public void start() {
        bottomEditText = parentLinearLayout.findViewById(R.id.row_edit_text);
        bottomEditText.addTextChangedListener(bottomEditTextWatcher);
    }

    @Override
    public void onDeleteButtonClicked(View view) {
        parentLinearLayout.removeView((View) view.getParent());
        setBottomEditText();
    }

    @Override
    public void onFloatingActionButtonClearClicked() {
        for (int i = 0; i < parentLinearLayout.getChildCount(); i++) {
            view.clearRow((LinearLayout) parentLinearLayout.getChildAt(i));
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
        bottomEditText.removeTextChangedListener(bottomEditTextWatcher);
        LayoutInflater inflater = view.getInflater();
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
        setBottomEditText();
    }

    private void setBottomEditText() {
        bottomEditText = parentLinearLayout
                .getChildAt(parentLinearLayout.getChildCount() - 1)
                .findViewById(R.id.row_edit_text);
        bottomEditText.removeTextChangedListener(bottomEditTextWatcher);
        bottomEditText.addTextChangedListener(bottomEditTextWatcher);
    }
}
