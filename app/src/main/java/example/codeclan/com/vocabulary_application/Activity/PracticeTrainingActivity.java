package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumQuestionType;
import example.codeclan.com.vocabulary_application.Model.QuestionModel;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;
import example.codeclan.com.vocabulary_application.ViewAdapter.PropositionsListAdapter;

/**
 * Created by horizon on 02/02/2018.
 */

public class PracticeTrainingActivity extends AppCompatActivity {

    private TrainingModel trainingModel;
    private WordsRoomDatabase db;
    private Button practiceButtonQuit;
    private ListView propositionsList;
    private TextView practiceTrainingQuestionNumber;
    private int indexCurrentQuestion;
    private TextView practiceTrainingQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_training);
        initializeViewItems();


        db            = WordsRoomDatabase.getDatabase(this);
        trainingModel = new TrainingModel((TrainingEntity)this.getIntent().getSerializableExtra("trainingEntity"), db);


        ArrayList<EnumQuestionType> questionsType = new ArrayList<>();
        questionsType.add(EnumQuestionType.WORD_DEFINITION);
        questionsType.add(EnumQuestionType.EXAMPLE_WORD);
        questionsType.add(EnumQuestionType.DEFINITION_WORD);
        questionsType.add(EnumQuestionType.WORD_EXAMPLE);

        trainingModel.initializeQuestions(questionsType, true);
        indexCurrentQuestion = 0;

        initializeViewItems();
        intializeViewItemsValuesWithQuestion();
    }

    public QuestionModel getCurrentQuestionModel(){
        return trainingModel.getQuestionsModelList().get(indexCurrentQuestion);
    }

    public void initializeViewItems(){
        practiceTrainingQuestionNumber  = findViewById(R.id.practiceTrainingQuestionNumber);
        practiceButtonQuit              = findViewById(R.id.practiceButtonQuit);
        propositionsList                = findViewById(R.id.practiceTrainingPropositionsList);
        practiceTrainingQuestion        = findViewById(R.id.practiceTrainingQuestion);
    }

    public void intializeViewItemsValuesWithQuestion(){

        practiceTrainingQuestionNumber.setText(String.format("Question N°%s", StringUtils.intToString(indexCurrentQuestion+1)));
        practiceTrainingQuestion.setText(this.getCurrentQuestionModel().getQuestionLabel());

        PropositionsListAdapter propositionsListAdapter = new PropositionsListAdapter(this, this.getCurrentQuestionModel().getPropositions());
        propositionsList.setAdapter(propositionsListAdapter);
    }

    public void checkAnswer(View button){

        displayNextQuestion();
    }

    public void displayNextQuestion(){
        if(indexCurrentQuestion+1 < trainingModel.getQuestionsModelList().size()){
            indexCurrentQuestion +=1;
        }
        else{
            trainingModel.shuffleQuestions();
            indexCurrentQuestion = 0;
        }

        intializeViewItemsValuesWithQuestion();
    }

    public void OnClickButtonQuitPracticeTraining(View button){
        Intent intent = new Intent(this, TrainingListActivity.class);
        startActivity(intent);
    }


}
