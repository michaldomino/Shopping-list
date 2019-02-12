package me.leahcim333.shoppinglist.ui.screens.main;

import android.view.LayoutInflater;

import me.leahcim333.shoppinglist.ui.base.BasePresenter;
import me.leahcim333.shoppinglist.ui.base.BaseView;

interface MainContract {
    interface View extends BaseView {

        LayoutInflater getInflater();
    }

    interface Presenter extends BasePresenter {

        void onDeleteButtonClicked(android.view.View view);
    }

}
