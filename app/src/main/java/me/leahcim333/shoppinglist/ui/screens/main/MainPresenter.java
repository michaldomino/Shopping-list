package me.leahcim333.shoppinglist.ui.screens.main;

class MainPresenter implements MainContract.Preseter {

    MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
