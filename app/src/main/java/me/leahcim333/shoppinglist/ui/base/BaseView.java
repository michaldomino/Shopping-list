package me.leahcim333.shoppinglist.ui.base;

import android.view.LayoutInflater;

public interface BaseView {

    void showToast(String message);

    LayoutInflater getInflater();

    String getString(int save_successful);
}
