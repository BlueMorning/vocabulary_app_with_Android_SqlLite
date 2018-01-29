package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.vocabulary_application.Dao.WordDao;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

import static org.junit.Assert.assertEquals;

/**
 * Created by horizon on 29/01/2018.
 */

public class WordEntityTest {

    private WordDao wordDao;
    private WordsRoomDatabase wordsDB;


    @Before
    public void before(){
        Context context = InstrumentationRegistry.getTargetContext();
        wordsDB = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        wordDao = wordsDB.wordDao();
    }

    @After
    public void after(){
        wordsDB.close();
    }


    @Test
    public void createWord(){
        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "");
        wordDao.insertWord(wordEntity);

        assertEquals(1, wordDao.getAll().size());
    }

}
