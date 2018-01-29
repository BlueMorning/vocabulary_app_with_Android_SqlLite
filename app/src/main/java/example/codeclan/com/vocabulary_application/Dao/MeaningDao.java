package example.codeclan.com.vocabulary_application.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;

@Dao
public interface MeaningDao {


    @Query("SELECT * FROM meanings WHERE mid_wrd_id = :id")
    List<MeaningEntity> getMeaningsByWord(int id);

    @Insert
    public Long insertMeaning(MeaningEntity meaningEntity);

    @Update
    public void updateMeaning(MeaningEntity meaning);

    @Delete
    public void deleteMeaning(MeaningEntity meaning);

}
