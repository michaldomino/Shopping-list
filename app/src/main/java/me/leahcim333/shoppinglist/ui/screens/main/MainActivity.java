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

        presenter = new MainPresenter(this, parentLinearLayout);
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

    public void onFloatingActionButtonAddClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void onDeleteButtonClicked(View view) {
        presenter.onDeleteButtonClicked(view);
    }

    @Override
    public LayoutInflater getInflater() {
        return  (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void clearFirstRow() {
        EditText rowEditText = findViewById(R.id.row_edit_text);
        rowEditText.setText("");
    }

    public void onFloatingActionButtonClearClicked(View view) {
        presenter.onFloatingActionButtonClearClicked();
    }
}
