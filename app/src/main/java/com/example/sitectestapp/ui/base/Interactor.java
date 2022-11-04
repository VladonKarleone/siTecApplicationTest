package com.example.sitectestapp.ui.base;

import com.example.sitectestapp.data.response.AuthResponse;
import com.example.sitectestapp.data.response.UsersResponse;
import com.example.sitectestapp.data.room.entities.ResponsesEntity;
import com.example.sitectestapp.data.room.entities.UserEntity;

import java.util.List;

import io.reactivex.Single;

public interface Interactor {

    interface MainInteractor {

        /**
         * Получение списка пользователей с сервера
         */
        Single<UsersResponse> getUsersList(String imei);

        /**
         * Получить пользователей из БД
         */
        Single<List<UserEntity>> getUsers();

        /**
         * Получить UID
         */
        Single<String> getUID(String user);

        /**
         * Авторизация
         */
        Single<AuthResponse> auth(String imei, String uid, String pass);

        /**
         * Добавить запись о успешной авторизации в БД
         *
         * @param responsesEntity ответ при успешной авторизации
         */
        void insertResponse(ResponsesEntity responsesEntity);

        /**
         * Добавить запись о пользователе в БД
         *
         * @param userEntity информация о пользователе
         */
        void insertUser(UserEntity userEntity);

    }

    interface UsersInteractor {
        /**
         * Получить все записи из таблицы с успешными авторизациями
         */
        Single<List<ResponsesEntity>> getResponses();
    }
}
