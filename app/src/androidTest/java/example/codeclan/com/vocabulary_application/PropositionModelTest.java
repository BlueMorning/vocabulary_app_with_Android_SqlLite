package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.vocabulary_application.Database.DatabaseRunner;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Enumerations.EnumQuestionType;
import example.codeclan.com.vocabulary_application.Model.MeaningModel;
import example.codeclan.com.vocabulary_application.Model.PropositionModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;

import static org.junit.Assert.assertEquals;

/**
 * Created by horizon on 04/02/2018.
 */

public class PropositionModelTest {



    private PropositionModel propositionModel;
    private WordModel wordModel;
    private MeaningModel meaningModel;
    private WordsRoomDatabase wordsDB;


    @Before
    public void before(){
        Context context         = InstrumentationRegistry.getTargetContext();
        wordsDB                 = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        DatabaseRunner.fuelDatabase(wordsDB);
        wordModel               = new WordModel(wordsDB.wordDao().getAll().get(0), wordsDB);
        wordModel.initializeMeanings();
        meaningModel            = wordModel.getMeaningsList().get(0);
        this.propositionModel   = new PropositionModel(meaningModel, EnumQuestionType.WORD_DEFINITION);
    }



    @Test
    public void hasProposition(){
        String expectedProposition = meaningModel.getMeaningEntity().getMeaning();
        assertEquals(expectedProposition, this.propositionModel.getProposition());
    }


    @Test
    public void hasMeaningModel(){
        assertEquals(meaningModel, this.propositionModel.getMeaningModel());
    }



}
