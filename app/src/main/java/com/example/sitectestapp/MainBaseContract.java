package com.example.sitectestapp;

import com.example.sitectestapp.ui.base.BaseContract;
import java.util.List;

public interface MainBaseContract {
    interface View extends BaseContract.BaseView {

        /**
         * Добавление списка пользователей в спиннер
         * @param users список пользователей
         */
        void addUsersToSpinner(List<String> users);

        /**
         * Открытие экрана со списком успешных авторизаций
         */
        void openUsersActivity();

        /**
         * Получение IMEI
         */
        String getImei();
    }

    interface Presenter extends BaseContract.Presenter<MainBaseContract.View> {

        /**
         * Получение списка пользователей с сервера
         */
        void getUsersList();

        /**
         * Получение списка пользователей из БД
         */
        void getUsersForSpinner();

        /**
         * Получение UID по пользователю из БД и отправка запроса авторизации
         * @param user пользователь
         * @param pass пароль
         */
        void getUidAndAuth(String user, String pass);
    }


}
