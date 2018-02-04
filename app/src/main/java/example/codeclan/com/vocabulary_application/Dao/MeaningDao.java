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
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

@Dao
public interface MeaningDao {

    @Query("SELECT * FROM meanings WHERE mig_id = :id")
    MeaningEntity getMeaningByMeaningId(Long id);

    @Query("SELECT * FROM meanings WHERE mig_wrd_id = :id")
    List<MeaningEntity> getMeaningsByWordId(Long id);

    @Insert
    public Long insertMeaning(MeaningEntity meaningEntity);

    @Update
    public void updateMeaning(MeaningEntity meaning);

    @Delete
    public void deleteMeaning(MeaningEntity meaning);

    @Query("DELETE FROM meanings WHERE mig_id = :id")
    public void deleteMeaningByMeaningId(Long id);

    @Query("DELETE FROM meanings")
    public void deleteAllMeanings();

    @Query("DELETE FROM meanings WHERE mig_wrd_id = :id")
    void deleteMeaningByWordId(Long id);

    @Query( "SELECT * FROM meanings " +
            "INNER JOIN words ON words.wrd_id = meanings.mig_wrd_id "+
            "INNER JOIN stats ON stats.sta_wrd_id = words.wrd_id " +
            "WHERE " +
            "(:enumMasteryLevelMax = 0 OR stats.sta_mastery_level != :enumMasteryLevelMax )" +
            "and meanings.mig_wrd_id != :meaningAnswerId " +
            "and words.wrd_type = :enumWordType " +
            "ORDER BY RANDOM() LIMIT :propositionsCounter")
    List<MeaningEntity> getAnswerPropositions(Long meaningAnswerId,
                                              EnumMasteryLevel enumMasteryLevelMax,
                                              EnumWordType enumWordType,
                                              int propositionsCounter);
}
