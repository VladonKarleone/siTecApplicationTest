package com.example.sitectestapp.data.room.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sitectestapp.data.room.entities.ResponsesEntity;
import com.example.sitectestapp.data.room.entities.UserEntity;

import kotlin.jvm.JvmStatic;


@Database(
        version = 1,
        entities = {
                UserEntity.class,
                ResponsesEntity.class
        },
        exportSchema = true
)
public abstract class DataBaseRoom extends RoomDatabase {

    public abstract UsersDao usersDao();

    public abstract ResponsesDao responsesDao();

    @JvmStatic
    public static DataBaseRoom getInstanceForDagger(Context context) {
        String dbName = "siTecTest.db";
        return Room
                .databaseBuilder(
                        context.getApplicationContext(),
                        DataBaseRoom.class,
                dbName
                )
                .build();
    }
}
