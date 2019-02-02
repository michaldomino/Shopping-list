package me.leahcim333.shoppinglist.ui.screens.main;

class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
