package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.vocabulary_application.Dao.WordDao;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

import static org.junit.Assert.assertEquals;


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

    @Test
    public void updateWord(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "");
        Long word_id = wordDao.insertWord(wordEntity);

        wordEntity = wordDao.getWordByWordId(word_id);
        assertEquals(1, wordDao.getAll().size());

        wordEntity.setSpelling("stone");
        wordDao.updateWord(wordEntity);

        assertEquals("stone", wordDao.getWordByWordId(wordEntity.getId()).getSpelling());

    }

    @Test
    public void deleteWord(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "");
        Long word_id          = wordDao.insertWord(wordEntity);
        wordEntity            = wordDao.getWordByWordId(word_id);
        assertEquals(1, wordDao.getAll().size());

        wordDao.deleteWord(wordEntity);

        assertEquals(0, wordDao.getAll().size());

    }

    @Test
    public void deleteWordByWordId(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "");
        Long word_id          = wordDao.insertWord(wordEntity);
        assertEquals(1, wordDao.getAll().size());

        wordDao.deleteWordByWordId(word_id);
        assertEquals(0, wordDao.getAll().size());
    }


    @Test
    public void getAllWords(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "");
        wordDao.insertWord(wordEntity);

        wordEntity = new WordEntity(EnumWordType.NOUN, "stone", "");
        wordDao.insertWord(wordEntity);

        wordEntity = new WordEntity(EnumWordType.NOUN, "gravel", "");
        wordDao.insertWord(wordEntity);


        assertEquals(3, wordDao.getAll().size());
    }

    @Test
    public void getAllWordsBySpelling(){

        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rOck", "");
        wordDao.insertWord(wordEntity);

        wordEntity = new WordEntity(EnumWordType.NOUN, "stOne", "");
        wordDao.insertWord(wordEntity);

        wordEntity = new WordEntity(EnumWordType.NOUN, "grAvel", "");
        wordDao.insertWord(wordEntity);


        assertEquals(2, wordDao.getAllBySpelling("O").size());
        assertEquals(1, wordDao.getAllBySpelling("a").size());
        assertEquals(2, wordDao.getAllBySpelling("R").size());
    }


}
