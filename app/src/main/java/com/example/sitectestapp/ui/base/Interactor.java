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
        Single<List<UserEntity>> getUsersFromDB();

        /**
         * Получить UID пользователя
         */
        Single<String> getUid(String user);

        /**
         * Авторизация
         */
        Single<AuthResponse> auth(String imei, String uid, String pass);

        /**
         * Добавить запись об успешной авторизации в БД
         *
         * @param responsesEntity ответ при успешной авторизации
         */
        void insertResponse(ResponsesEntity responsesEntity);

        /**
         * Добавить пользователя в БД, если его там нет
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
