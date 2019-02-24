package me.leahcim333.shoppinglist.ui.screens.list_selection;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import me.leahcim333.shoppinglist.R;

public class ListSelectionActivity extends AppCompatActivity implements ListSelectionContract.View {

    ListSelectionContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selection);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new ListSelectionPresenter(this);
        presenter.start();
    }

    @Override
    public void showToast(String message) {

    }

    public void onFloatingActionButtonAddClicked(View view) {
    }
}
