package example.codeclan.com.vocabulary_application.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;

@Dao
public interface WordTrainingJoinDao {


    @Query("SELECT * FROM words_trainings_joins WHERE wrd_tra_tra_id = :id")
    public List<WordTrainingJoinEntity> getWordsTrainingJoinEntityBytrainingId(Long id);

    @Insert
    public Long insertWordTrainingJoin(WordTrainingJoinEntity wordTrainingJoinEntity);

    @Update
    public void updateWordTrainingJoin(WordTrainingJoinEntity wordTrainingJoinEntity);

    @Delete
    public void deleteWordTrainingJoin(WordTrainingJoinEntity wordTrainingJoinEntity);


}
