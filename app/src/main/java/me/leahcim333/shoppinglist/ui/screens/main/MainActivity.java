package me.leahcim333.shoppinglist.ui.screens.main;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import me.leahcim333.shoppinglist.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter presenter;

    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

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
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_clear:
                presenter.onClearOptionsItemSelected();
                return true;
            case R.id.action_load_list:
                presenter.onLoadListOptionsItemSelected();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFloatingActionButtonAddClicked(View view) {
        presenter.onFloatingActionButtonAddClicked();
    }

    public void onDeleteButtonClicked(View view) {
        presenter.onDeleteButtonClicked(view);
    }

    @Override
    public LayoutInflater getInflater() {
        return (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void clearFirstRow() {
        EditText rowEditText = findViewById(R.id.row_edit_text);
        rowEditText.setText("");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startVoiceRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getResources().getString(R.string.voice_recognizer_message));
        try {
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e) {
            showToast(e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    presenter.addTextFromSpeechRecognizer(data);
                    break;
                default:
                    break;
            }
        }
    }
}
