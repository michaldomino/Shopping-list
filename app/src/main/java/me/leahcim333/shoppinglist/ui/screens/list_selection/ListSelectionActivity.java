package me.leahcim333.shoppinglist.ui.screens.list_selection;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import me.leahcim333.shoppinglist.R;
import me.leahcim333.shoppinglist.data.database.DBHelper;

public class ListSelectionActivity extends AppCompatActivity implements ListSelectionContract.View {

    ListSelectionContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selection);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DBHelper dbHelper = new DBHelper(this);

        LinearLayout parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        presenter = new ListSelectionPresenter(this, parentLinearLayout, dbHelper);
        presenter.start();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public LayoutInflater getInflater() {
        return (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void onFloatingActionButtonAddClicked(View view) {
    }
}
