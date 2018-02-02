package example.codeclan.com.vocabulary_application.Dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

@Dao
public interface WordDao {

    public final String enumWordTypeAll = EnumWordType.ALL.toString();

    @Query("SELECT * FROM words ORDER BY wrd_spelling LIMIT 100")
    List<WordEntity> getAll();

    @Query("SELECT * FROM words WHERE  lower(words.wrd_spelling) " +
            "LIKE lower('%' || :spelling || '%') ORDER BY wrd_spelling LIMIT 100")
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

    @Query("DELETE FROM words")
    public void deleteAllWords();

    @Query(   " SELECT * FROM words "+
            " INNER JOIN stats ON stats.sta_wrd_id = words.wrd_id " +
            " WHERE  "+
            "     (:spelling          = ''  OR lower(words.wrd_spelling) LIKE lower('%' || :spelling || '%'))"+
            " AND (:enumWordType      = ''  OR words.wrd_type = :enumWordType)"+
            " AND (:enumMasteryLevel  = ''  OR stats.sta_mastery_level = :enumMasteryLevel)"+
            " ORDER BY wrd_spelling LIMIT 100")
    public List<WordEntity> getAllBySpellingAndTypeAndMastery(String spelling, String enumWordType, String enumMasteryLevel);


}
