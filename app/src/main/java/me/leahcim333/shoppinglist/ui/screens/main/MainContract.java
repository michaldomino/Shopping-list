package me.leahcim333.shoppinglist.ui.screens.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import me.leahcim333.shoppinglist.ui.base.BasePresenter;
import me.leahcim333.shoppinglist.ui.base.BaseView;
import me.leahcim333.shoppinglist.ui.screens.list_selection.ListSelectionActivity;

interface MainContract {
    interface View extends BaseView {

        LayoutInflater getInflater();

        void clearFirstRow();

        void startVoiceRecognizer();

        String getString(int id);

        void startNewActivity(Class cls);
    }

    interface Presenter extends BasePresenter {

        void onDeleteButtonClicked(android.view.View view);

        void onClearOptionsItemSelected();

        void onFloatingActionButtonAddClicked();

        void addTextFromSpeechRecognizer(Intent data);

        void onSaveListOptionsItemSelected();

        void onLoadListOptionsItemSelected();
    }

}
