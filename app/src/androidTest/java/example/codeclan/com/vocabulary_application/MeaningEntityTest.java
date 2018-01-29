package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.vocabulary_application.Dao.MeaningDao;
import example.codeclan.com.vocabulary_application.Dao.WordDao;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

import static org.junit.Assert.assertEquals;

/**
 * Created by horizon on 29/01/2018.
 */

public class MeaningEntityTest {

    private WordDao wordDao;
    private MeaningDao meaningDao;
    private WordsRoomDatabase wordsDB;


    @Before
    public void before(){
        Context context = InstrumentationRegistry.getTargetContext();
        wordsDB         = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        wordDao         = wordsDB.wordDao();
        meaningDao      = wordsDB.meaningDao();
    }

    @After
    public void after(){
        wordsDB.close();
    }

    @Test
    public void canInsertMeaning(){

        WordEntity wordEntity = new WordEntity(EnumWordType.PHRASAL_VERB, "to pull out", "");
        Long word_id = wordDao.insertWord(wordEntity);
        assertEquals(1, wordDao.getAll().size());

        MeaningEntity meaning = new MeaningEntity(word_id, "to stop being involved in an activity, event, or situation",
                                                   "The firm is pulling out of the personal computer business",
                                                   "to stop, to break off", "to continue");
        Long meaning_id = meaningDao.insertMeaning(meaning);

        assertEquals(1, meaningDao.getMeaningsByWordId(word_id).size());
        assertEquals("to stop being involved in an activity, event, or situation",
                meaningDao.getMeaningByMeaningId(meaning_id).getDefinition());


         meaning = new MeaningEntity(word_id, "if a train pulls out, it leaves a station",
                "the train is pulling out the station",
                "to leave", "to stay");

        meaning_id = meaningDao.insertMeaning(meaning);

        assertEquals(2, meaningDao.getMeaningsByWordId(word_id).size());
        assertEquals("the train is pulling out the station",
                meaningDao.getMeaningByMeaningId(meaning_id).getExample());

    }


    @Test
    public void canUpdateMeaning(){
        
    }


}
