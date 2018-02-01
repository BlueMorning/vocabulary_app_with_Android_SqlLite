package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.vocabulary_application.Dao.StatsDao;
import example.codeclan.com.vocabulary_application.Dao.WordDao;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

import static org.junit.Assert.assertEquals;


public class StatsEntityTest {

    private WordDao wordDao;
    private StatsDao statsDao;
    private WordsRoomDatabase wordsDB;


    @Before
    public void before(){
        Context context = InstrumentationRegistry.getTargetContext();
        wordsDB = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        wordDao = wordsDB.wordDao();
        statsDao = wordsDB.statsDao();
    }

    @After
    public void after(){
        wordsDB.close();
    }

    @Test
    public void canInsertStats(){
        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "rocks");
        wordEntity.setId(wordDao.insertWord(wordEntity));
        assertEquals(1, wordDao.getAll().size());


        // public StatsEntity(Long wordId, int trainingStep, int confidenceLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers)
        StatsEntity statsEntity = new StatsEntity(wordEntity.getId(), 1, EnumMasteryLevel.NEW, 0, 0, 0, 0, 0, 0 );
        statsEntity.setId(statsDao.insertStats(statsEntity));

        assertEquals(statsEntity.getId(), statsDao.getStatsByWordId(wordEntity.getId()).getId());
        assertEquals(statsEntity.getId(), statsDao.getStatsByStatsId(statsEntity.getId()).getId());

    }

    @Test
    public void canUpdateStats(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "rocks");
        wordEntity.setId(wordDao.insertWord(wordEntity));
        assertEquals(1, wordDao.getAll().size());

        // public StatsEntity(Long wordId, int trainingStep, int confidenceLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers)
        StatsEntity statsEntity = new StatsEntity(wordEntity.getId(), 1, EnumMasteryLevel.NEW, 0, 0, 0, 0, 0, 0 );
        statsEntity.setId(statsDao.insertStats(statsEntity));

        assertEquals(statsEntity.getId(), statsDao.getStatsByWordId(wordEntity.getId()).getId());

        statsEntity.setMasteryLevel(EnumMasteryLevel.HIGH);
        statsDao.updateStats(statsEntity);

        assertEquals(EnumMasteryLevel.HIGH, statsDao.getStatsByWordId(wordEntity.getId()).getMasteryLevel());

    }

    @Test
    public void canDeleteStats(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "rocks");
        wordEntity.setId(wordDao.insertWord(wordEntity));
        assertEquals(1, wordDao.getAll().size());

        // public StatsEntity(Long wordId, int trainingStep, int confidenceLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers)
        StatsEntity statsEntity = new StatsEntity(wordEntity.getId(), 1, EnumMasteryLevel.NEW, 0, 0, 0, 0, 0, 0 );
        statsEntity.setId(statsDao.insertStats(statsEntity));

        assertEquals(statsEntity.getId(), statsDao.getStatsByWordId(wordEntity.getId()).getId());

        statsDao.deleteStats(statsEntity);

        assertEquals(null, statsDao.getStatsByStatsId(statsEntity.getId()));
    }

    @Test
    public void canDeleteStatsById(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "rocks");
        wordEntity.setId(wordDao.insertWord(wordEntity));
        assertEquals(1, wordDao.getAll().size());

        // public StatsEntity(Long wordId, int trainingStep, int confidenceLevel, int totalAnswers, int totalCorrectAnswers, int totalIncorrectAnswers, int lastTrainingTotalAnswers, int lastTrainingTotalCorrectAnswers, int lastTrainingTotalIncorrectAnswers)
        StatsEntity statsEntity = new StatsEntity(wordEntity.getId(), 1, EnumMasteryLevel.NEW, 0, 0, 0, 0, 0, 0 );
        statsEntity.setId(statsDao.insertStats(statsEntity));

        assertEquals(statsEntity.getId(), statsDao.getStatsByWordId(wordEntity.getId()).getId());

        statsDao.deleteStatsByStatsId(statsEntity.getId());

        assertEquals(null, statsDao.getStatsByStatsId(statsEntity.getId()));
    }

}
