package com.example.sitectestapp.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.sitectestapp.data.room.entities.UserEntity;
import java.util.List;
import io.reactivex.Single;

/**
 * Пользователи
 */
@Dao
public interface UsersDao {

    @Query("SELECT * FROM users")
    Single<List<UserEntity>> getUsers();

    @Insert(entity = UserEntity.class, onConflict = OnConflictStrategy.REPLACE)
    Long insertUsers(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE uid=:uid")
    Single<List<UserEntity>> getUserByUid(String uid);

    @Update
    void update(UserEntity userEntity);

    @Query("SELECT `uid` FROM users WHERE user=:user")
    Single<String> getUidByUser(String user);
}
