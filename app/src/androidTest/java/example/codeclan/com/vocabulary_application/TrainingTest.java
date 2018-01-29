package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import example.codeclan.com.vocabulary_application.Dao.TrainingDao;
import example.codeclan.com.vocabulary_application.Dao.WordDao;
import example.codeclan.com.vocabulary_application.Dao.WordTrainingJoinDao;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;

import static org.junit.Assert.assertEquals;


public class TrainingTest {

    private TrainingDao trainingDao;
    private TrainingEntity trainingEntity;

    private WordsRoomDatabase wordsDB;

    private WordDao wordDao;
    private WordTrainingJoinEntity wordTrainingJoinEntity;
    private WordTrainingJoinDao wordTrainingJoinDao;

    @Before
    public void before(){
        Context context         = InstrumentationRegistry.getTargetContext();
        wordsDB                 = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        trainingDao             = wordsDB.trainingDao();
        wordDao                 = wordsDB.wordDao();
        wordTrainingJoinDao     = wordsDB.wordTrainingJoinDao();
    }

    @After
    public void after(){
        wordsDB.close();
    }

    @Test
    public void canInsertTraining(){
        trainingEntity = new TrainingEntity(EnumTrainingStatus.ONGOING,
                                            1,
                                            10,
                                            1,
                                            LocalDate.now());

        trainingEntity.setId(trainingDao.insertTraining(trainingEntity));

        assertEquals(1, trainingDao.getAll().size());
    }

    @Test
    public void canAddWordsToTraining(){
        trainingEntity = new TrainingEntity(EnumTrainingStatus.ONGOING,
                1,
                10,
                1,
                LocalDate.now());

        trainingEntity.setId(trainingDao.insertTraining(trainingEntity));

        assertEquals(1, trainingDao.getAll().size());



        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "rocks");
        wordEntity.setId(wordDao.insertWord(wordEntity));

        wordTrainingJoinEntity = new WordTrainingJoinEntity(trainingEntity.getId(), wordEntity.getId());
        wordTrainingJoinDao.insertWordTrainingJoin(wordTrainingJoinEntity);

        assertEquals(1, wordTrainingJoinDao.getWordsTrainingJoinEntityBytrainingId(trainingEntity.getId()).size());

    }

    @Test
    public void canRemoveWordsFromTraining(){
        trainingEntity = new TrainingEntity(EnumTrainingStatus.ONGOING,
                1,
                10,
                1,
                LocalDate.now());

        trainingEntity.setId(trainingDao.insertTraining(trainingEntity));

        assertEquals(1, trainingDao.getAll().size());



        WordEntity wordEntity = new WordEntity(EnumWordType.NOUN, "rock", "rocks");
        wordEntity.setId(wordDao.insertWord(wordEntity));

        wordTrainingJoinEntity = new WordTrainingJoinEntity(trainingEntity.getId(), wordEntity.getId());
        wordTrainingJoinDao.insertWordTrainingJoin(wordTrainingJoinEntity);

        assertEquals(1, wordTrainingJoinDao.getWordsTrainingJoinEntityBytrainingId(trainingEntity.getId()).size());

        wordTrainingJoinDao.deleteWordTrainingJoin(wordTrainingJoinEntity);

        assertEquals(0, wordTrainingJoinDao.getWordsTrainingJoinEntityBytrainingId(trainingEntity.getId()).size());


    }

    @Test
    public void canUpdateTraining(){
        trainingEntity = new TrainingEntity(EnumTrainingStatus.ONGOING,
                1,
                10,
                1,
                LocalDate.now());

        trainingEntity.setId(trainingDao.insertTraining(trainingEntity));

        assertEquals(1, trainingDao.getAll().size());


        trainingEntity = trainingDao.getTrainingById(trainingEntity.getId());
        trainingEntity.setTotalWords(20);

        trainingDao.updateTraining(trainingEntity);
        trainingEntity = trainingDao.getTrainingById(trainingEntity.getId());


        assertEquals(20, trainingDao.getTrainingById(trainingEntity.getId()).getTotalWords());
    }

    @Test
    public void canDeleteTraining(){

        trainingEntity = new TrainingEntity(EnumTrainingStatus.ONGOING,
                1,
                10,
                1,
                LocalDate.now());

        trainingEntity.setId(trainingDao.insertTraining(trainingEntity));

        assertEquals(1, trainingDao.getAll().size());

        trainingDao.deleteTraining(trainingEntity);

        assertEquals(0, trainingDao.getAll().size());

    }

}
