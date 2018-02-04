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
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
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
        statsModel      = new StatsModel(statsEntity, wordsDB);
        statsModel.getStatsEntity().setMasteryLevel(EnumMasteryLevel.NEW);
        statsModel.getStatsEntity().setTrainingStep(5);
        statsModel.getStatsEntity().setTotalAnswers(20);
        statsModel.getStatsEntity().setTotalCorrectAnswers(15);
        statsModel.getStatsEntity().setTotalIncorrectAnswers(5);
        statsModel.getStatsEntity().setLastTrainingTotalAnswers(4);
        statsModel.getStatsEntity().setLastTrainingTotalCorrectAnswers(1);
        statsModel.getStatsEntity().setLastTrainingTotalIncorrectAnswers(3);
        wordsDB.statsDao().updateStats(statsModel.getStatsEntity());

        statsEntity     = wordsDB.statsDao().getStatsByWordId(wordsDB.wordDao().getAll().get(0).getId());
        statsModel      = new StatsModel(statsEntity, wordsDB);
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
    public void hasMasteryLevel(){
        assertEquals(EnumMasteryLevel.NEW, statsModel.getMasteryLevel());
    }

    @Test
    public void hasTrainingStep(){
        assertEquals(5, statsModel.getTrainingStep());
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

    @Test
    public void hasLastTrainingCorrectAnswersLabel(){
        assertEquals("1 / 4", statsModel.getLastTrainingCorrectAnswersLabel());
    }

    @Test
    public void hasLastTrainingIncorrectAnswersLabel(){
        assertEquals("3 / 4", statsModel.getLastTrainingIncorrectAnswersLabel());
    }

    @Test
    public void hasTotalCorrectAnswersLabel(){
        assertEquals("15 / 20", statsModel.getTotalCorrectAnswersLabel());
    }

    @Test
    public void hasTotalIncorrectAnswersLabel(){
        assertEquals("5 / 20", statsModel.getTotalIncorrectAnswersLabel());
    }

    @Test
    public void hasLastTrainingTotalCorrectAnswersPercentageLabel(){
        assertEquals("25%", statsModel.getLastTrainingTotalCorrectAnswersPercentageLabel());
    }

    @Test
    public void hasLastTrainingTotalIncorrectAnswersPercentageLabel(){
        assertEquals("75%", statsModel.getLastTrainingTotalIncorrectAnswersPercentageLabel());
    }

    @Test
    public void hasTotalCorrectAnswersPercentageLabel(){
        assertEquals("75%", statsModel.getTotalCorrectAnswersPercentageLabel());
    }

    @Test
    public void hasTotalIncorrectAnswersPercentageLabel(){
        assertEquals("25%", statsModel.getTotalIncorrectAnswersPercentageLabel());
    }

    @Test
    public void canResetStats(){
        statsModel.resetStats();
        assertEquals(EnumMasteryLevel.NEW, statsModel.getMasteryLevel());
        assertEquals(0, statsModel.getTrainingStep());
        assertEquals(0, statsModel.getTotalAnswers());
        assertEquals(0, statsModel.getTotalCorrectAnswers());
        assertEquals(0, statsModel.getLastTrainingTotalAnswers());
        assertEquals(0, statsModel.getLastTrainingTotalCorrectAnswers());
        assertEquals(0, statsModel.getLastTrainingTotalIncorrectAnswers());
    }


}
