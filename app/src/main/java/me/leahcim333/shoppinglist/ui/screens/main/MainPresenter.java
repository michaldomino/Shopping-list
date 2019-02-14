package me.leahcim333.shoppinglist.ui.screens.main;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import me.leahcim333.shoppinglist.R;

class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    private LinearLayout parentLinearLayout;

    private EditText bottomEditText = null;

    MainPresenter(MainContract.View view, LinearLayout parentLinearLayout) {
        this.view = view;
        this.parentLinearLayout = parentLinearLayout;
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
        parentLinearLayout.removeViews(1, parentLinearLayout.getChildCount() - 1);
        view.clearFirstRow();
        setBottomEditText();
    }

    @Override
    public void onFloatingActionButtonAddClicked() {
        view.startVoiceRecognizer();
    }

    @Override
    public void addTextFromSpeechRecognizer(Intent data) {
        ArrayList<String> possibleMatches =
                data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String[] wordList = possibleMatches.get(0).split(" i ");
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

}
