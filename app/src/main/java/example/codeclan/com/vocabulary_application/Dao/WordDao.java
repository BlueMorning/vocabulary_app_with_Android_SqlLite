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
public interface WordDao {


    @Query("SELECT * FROM words LIMIT 100")
    List<WordEntity> getAll();

    @Query("SELECT * FROM words WHERE  lower(words.wrd_spelling) LIKE lower('%' || :spelling || '%') LIMIT 100")
    List<WordEntity> getAllBySpelling(String spelling);

    @Query("SELECT * FROM words WHERE wrd_id = :id")
    WordEntity getWordByWordId(Long id);

    @Query("SELECT * FROM stats WHERE sta_wrd_id = :id")
    StatsEntity getWordStatsByWordId(Long id);

    @Query("SELECT * FROM meanings WHERE mig_wrd_id = :id")
    List<MeaningEntity> getWordMeaningsByWordId(Long id);

    @Insert
    public Long insertWord(WordEntity word);

    @Update
    public void updateWord(WordEntity word);

    @Delete
    public void deleteWord(WordEntity word);

    @Query("DELETE FROM words WHERE wrd_id = :id")
    public void deleteWordByWordId(Long id);


}
