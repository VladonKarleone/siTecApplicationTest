package com.example.sitectestapp.data.room.repository;

import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.data.room.dao.ResponsesDao;
import com.example.sitectestapp.data.room.dao.UsersDao;

public class DbRepositoryImpl implements DbRepository{
    private DataBaseRoom dataBaseRoom;

    public DbRepositoryImpl(DataBaseRoom dataBaseRoom) {
        this.dataBaseRoom = dataBaseRoom;
    }

    @Override
    public UsersDao getUsers() {
        return dataBaseRoom.usersDao();
    }

    @Override
    public ResponsesDao getResponses() {
        return dataBaseRoom.responsesDao();
    }
}
