package com.example.sitectestapp.data.room.repository;

import com.example.sitectestapp.data.room.dao.ResponsesDao;
import com.example.sitectestapp.data.room.dao.UsersDao;

public interface DbRepository {

    /**
     * Получить информацию о пользователях
     */
    UsersDao getUsers();

    /**
     * Получить информацию о успешных ответах
     */
    ResponsesDao getResponses();
}
