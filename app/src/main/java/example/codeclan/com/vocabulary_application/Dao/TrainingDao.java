package example.codeclan.com.vocabulary_application.Dao;


import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;

public interface TrainingDao {


    @Query("SELECT * FROM trainings LIMIT 100")
    List<TrainingEntity> getAll();

    @Query("SELECT * FROM trainings WHERE tra_id = :id")
    List<TrainingEntity> getTrainingById(int id);

    @Query(   " SELECT * FROM words "
            + " INNER JOIN words_trainings_join ON words_trainings_join.wrd_tra_wrd_id = words.wrd_id"
            + " INNER JOIN trainings            ON training.tra_id = words_trainings_join.wrd_tra_tra_id"
            + " WHERE tra_id = :id ")
    List<WordEntity> getWordsByTrainingId();


    @Update
    public void updateTraining(TrainingEntity training);

    @Delete
    public void deleteTraining(TrainingEntity training);



}
