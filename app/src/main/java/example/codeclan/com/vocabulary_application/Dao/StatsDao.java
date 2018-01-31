package example.codeclan.com.vocabulary_application.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;

@Dao
public interface StatsDao {

    @Query("SELECT * FROM stats WHERE sta_id = :id")
    StatsEntity getStatsByStatsId(Long id);

    @Query("SELECT * FROM stats WHERE sta_wrd_id = :id")
    StatsEntity getStatsByWordId(Long id);

    @Insert
    public Long insertStats(StatsEntity statsEntity);

    @Update
    public void updateStats(StatsEntity stats);

    @Delete
    public void deleteStats(StatsEntity stats);

    @Query("DELETE FROM stats WHERE sta_id = :id")
    public void deleteStatsByStatsId(Long id);

    @Query("DELETE FROM stats")
    public void deleteAllStats();

    @Query("DELETE FROM stats WHERE sta_wrd_id = :id")
    public void deleteStatsByWordId(Long id);
}
