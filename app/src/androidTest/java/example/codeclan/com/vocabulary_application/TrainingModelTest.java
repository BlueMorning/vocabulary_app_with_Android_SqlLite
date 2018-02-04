package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Database.DatabaseRunner;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.StatsEntity;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Entity.WordTrainingJoinEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Model.StatsModel;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.junit.Assert.assertEquals;

/**
 * Created by horizon on 01/02/2018.
 */

public class TrainingModelTest {

    private WordsRoomDatabase wordsDB;
    private TrainingModel trainingModel;
    private TrainingEntity trainingEntity;


    @Before
    public void before(){
        Context context = InstrumentationRegistry.getTargetContext();
        wordsDB         = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        DatabaseRunner.fuelDatabase(wordsDB);
        trainingEntity  = new TrainingEntity(EnumTrainingStatus.ONGOING, 1, 3, 1, LocalDate.now());
        trainingEntity.setId(wordsDB.trainingDao().insertTraining(trainingEntity));

        ArrayList<WordEntity> wordsList = new ArrayList<>(wordsDB.wordDao().getAll().subList(0, trainingEntity.getTotalWords()));

        for(int wordIndex = 0; wordIndex < wordsList.size(); wordIndex++){
            wordsDB.wordTrainingJoinDao().insertWordTrainingJoin(
                    new WordTrainingJoinEntity(wordsList.get(wordIndex).getId(), trainingEntity.getId()));
        }

        trainingModel   = new TrainingModel(trainingEntity, wordsDB);
        trainingModel.initializeWordsList();

    }

    @After
    public void after(){
        wordsDB.close();
    }


    @Test
    public void hasNumber(){
        assertEquals(1, trainingModel.getNumber());
    }

    @Test
    public void hasTotalWords(){
        assertEquals(trainingEntity.getTotalWords(), trainingModel.getTotalWords());
        assertEquals(trainingEntity.getTotalWords(), trainingModel.getwordsModelList().size());
    }

    @Test
    public void hasBestNextDate(){
        assertEquals(LocalDate.now().format(ISO_LOCAL_DATE), trainingModel.getBestNextDateLabel());
    }

    @Test
    public void hasStepNumber(){
        assertEquals(1, trainingModel.getStepNumber());
    }




}
