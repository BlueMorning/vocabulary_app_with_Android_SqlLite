package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.vocabulary_application.Dao.MeaningDao;
import example.codeclan.com.vocabulary_application.Dao.WordDao;
import example.codeclan.com.vocabulary_application.Database.DatabaseRunner;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Model.StatsModel;

import static org.junit.Assert.assertEquals;

/**
 * Created by horizon on 01/02/2018.
 */

public class StatsModelTest {


    private WordsRoomDatabase wordsDB;
    private StatsModel statsModel;
    private StatsEntity statsEntity;

    @Before
    public void before(){
        Context context = InstrumentationRegistry.getTargetContext();
        wordsDB         = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        DatabaseRunner.fuelDatabase(wordsDB);
        statsEntity     = wordsDB.statsDao().getStatsByWordId(wordsDB.wordDao().getAll().get(0).getId());
        statsModel      = new StatsModel(statsEntity);
    }

    @After
    public void after(){
        wordsDB.close();
    }


    @Test
    public void hasStatsEntity(){
        assertEquals(StatsEntity.class, statsModel.getStatsEntity().getClass());
    }





}