package com.example.sitectestapp.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.sitectestapp.data.room.entities.ResponsesEntity;
import java.util.List;
import io.reactivex.Single;

/**
 * Успешные ответы
 */
@Dao
public interface ResponsesDao {
    @Query("SELECT * FROM responses")
    Single<List<ResponsesEntity>> getResponses();

    @Insert(entity = ResponsesEntity.class, onConflict = OnConflictStrategy.REPLACE)
    Long insertResponses(ResponsesEntity responsesEntity);

    @Update
    void update(ResponsesEntity responsesEntity);
}
