package com.example.sitectestapp.ui.users;

import com.example.sitectestapp.ui.base.BaseContract;

import java.util.List;

public interface UsersBaseContract {
    interface View extends BaseContract.BaseView {

        /**
         * Вывод текста на экран
         * @param responses - массив строк для вывода на экран
         */
        void showResponses(List<String> responses);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        /**
         * Установка текста с успешными попытками для вывода на экран
         */
        void setText();
    }
}
