package me.leahcim333.shoppinglist.ui.screens.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import me.leahcim333.shoppinglist.ui.base.BasePresenter;
import me.leahcim333.shoppinglist.ui.base.BaseView;

interface MainContract {
    interface View extends BaseView {

        LayoutInflater getInflater();

        void clearFirstRow();

        void startVoiceRecognizer();
    }

    interface Presenter extends BasePresenter {

        void onDeleteButtonClicked(android.view.View view);

        void onClearOptionsItemSelected();

        void onFloatingActionButtonAddClicked();

        void addTextFromSpeechRecognizer(Intent data);
    }

}
