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
        statsModel.getStatsEntity().setTrainingStep(5);
        statsModel.getStatsEntity().setTotalAnswers(20);
        statsModel.getStatsEntity().setTotalCorrectAnswers(15);
        statsModel.getStatsEntity().setLastTrainingTotalAnswers(4);
        statsModel.getStatsEntity().setLastTrainingTotalCorrectAnswers(1);
        statsModel.getStatsEntity().setLastTrainingTotalIncorrectAnswers(3);
        wordsDB.statsDao().updateStats(statsModel.getStatsEntity());
    }

    @After
    public void after(){
        wordsDB.close();
    }


    @Test
    public void hasStatsEntity(){
        assertEquals(StatsEntity.class, statsModel.getStatsEntity().getClass());
    }

    @Test
    public void hasTrainingStepLabel(){
        assertEquals("5 / 10", statsModel.getTrainingStepLabel());
    }

    @Test
    public void hasTotalAnswers(){
        assertEquals(20, statsModel.getTotalAnswers());
    }

    @Test
    public void hasTotalCorrectAnswers(){
        assertEquals(15, statsModel.getTotalCorrectAnswers());
    }

    @Test
    public void hasgetTotalCorrectAnswersPercentage(){
        assertEquals(0.75, statsModel.getTotalCorrectAnswersPercentage(), 0);
    }

    @Test
    public void hasLastTrainingTotalAnswers(){
        assertEquals(4, statsModel.getLastTrainingTotalAnswers());
    }

    @Test
    public void hasLastTrainingTotalCorrectAnswers(){
        assertEquals(1, statsModel.getLastTrainingTotalCorrectAnswers());
    }

    @Test
    public void hasLastTrainingTotalCorrectAnswersPercentage(){
        assertEquals(0.25, statsModel.getLastTrainingTotalCorrectAnswersPercentage(), 0);
    }

    @Test
    public void hasLastTrainingTotalIncorrectAnswers(){
        assertEquals(3, statsModel.getLastTrainingTotalIncorrectAnswers(), 0);
    }

    @Test
    public void hasLastTrainingTotalIncorrectAnswersPercentage(){
        assertEquals(0.75, statsModel.getLastTrainingTotalIncorrectAnswersPercentage(), 0);
    }

}
