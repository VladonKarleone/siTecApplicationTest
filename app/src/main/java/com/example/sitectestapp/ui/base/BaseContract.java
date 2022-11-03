package com.example.sitectestapp.ui.base;

public interface BaseContract {
    interface Presenter<T extends BaseView> {
        void bindView(T view);
    }

    interface BaseView {
        void showToast(String message);
    }
}
