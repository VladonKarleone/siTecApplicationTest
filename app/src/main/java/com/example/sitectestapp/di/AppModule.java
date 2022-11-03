package com.example.sitectestapp.di;

import android.app.Application;

import com.example.sitectestapp.data.room.dao.DataBaseRoom;
import com.example.sitectestapp.data.room.repository.DbRepository;
import com.example.sitectestapp.data.room.repository.DbRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    DataBaseRoom provideRoomDao(Application application) {
        return DataBaseRoom.getInstanceForDagger(application);
    }

    @Provides
    @Singleton
    DbRepository provideDbRepository(
            DataBaseRoom dataBaseRoom
    ) {
        return new DbRepositoryImpl(dataBaseRoom);
    }

}
