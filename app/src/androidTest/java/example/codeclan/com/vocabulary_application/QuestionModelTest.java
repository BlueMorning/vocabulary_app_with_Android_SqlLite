package example.codeclan.com.vocabulary_application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.vocabulary_application.Database.DatabaseRunner;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Enumerations.EnumQuestionType;
import example.codeclan.com.vocabulary_application.Model.QuestionModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;

import static org.junit.Assert.assertEquals;

/**
 * Created by horizon on 03/02/2018.
 */

public class QuestionModelTest {

    private WordsRoomDatabase db;
    private QuestionModel questionModel;
    private WordModel wordModel;

    @Before
    public void Before(){

        Context context = InstrumentationRegistry.getTargetContext();
        db              = Room.inMemoryDatabaseBuilder(context, WordsRoomDatabase.class).build();
        DatabaseRunner.fuelDatabase(db);

        wordModel       = new WordModel(db.wordDao().getAllBySpelling("to deal with").get(0), db);
        wordModel.initializeMeanings();
        questionModel   = new QuestionModel(EnumQuestionType.WORD_DEFINITION,
                wordModel,
                wordModel.getMeaningsList().get(0),
                4,
                db);
    }


    @Test
    public void hasEnumQuestionType(){
        assertEquals(EnumQuestionType.WORD_DEFINITION, questionModel.getEnumQuestionType());
    }

    @Test
    public void hasPropositions(){
        questionModel.initializePropositions();
        assertEquals(4,  questionModel.getPropositions().size());
    }

    @Test
    public void canCheckAnswer__true(){
        assertEquals(true, questionModel.checkAnswer(wordModel.getMeaningsList().get(0)));
    }

    @Test
    public void canCheckAnswer__false(){
        assertEquals(false, questionModel.checkAnswer(wordModel.getMeaningsList().get(1)));
    }


}
