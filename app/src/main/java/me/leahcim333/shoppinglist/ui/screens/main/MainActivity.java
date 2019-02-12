package me.leahcim333.shoppinglist.ui.screens.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import me.leahcim333.shoppinglist.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter presenter;

    private LinearLayout parentLinearLayout;

    private EditText bottomEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setVisibility(View.INVISIBLE);
        bottomEditText = (EditText) findViewById(R.id.first_edit_text);

        bottomEditText.addTextChangedListener(bottomEditTextWatcher);

        presenter = new MainPresenter(this);
        presenter.start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFloatingActionButtonClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void onDeleteButtonClicked(View view) {
        parentLinearLayout.removeView((View) view.getParent());
    }

    private TextWatcher bottomEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String entry = s.toString();

            if (!entry.isEmpty()) {
                bottomEditText.removeTextChangedListener(bottomEditTextWatcher);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.field, null);
                // Add the new row before the add field button.
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
                bottomEditText = parentLinearLayout
                        .getChildAt(parentLinearLayout.getChildCount() - 1)
                        .findViewById(R.id.b_edit_text);
                bottomEditText.addTextChangedListener(bottomEditTextWatcher);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
