package example.codeclan.com.vocabulary_application.Activity;

import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.R;

/**
 * Created by horizon on 02/02/2018.
 */

public class PracticeTrainingActivity extends AppCompatActivity {

    private TrainingModel trainingModel;
    private WordsRoomDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_training);

        db            = WordsRoomDatabase.getDatabase(this);
        trainingModel = new TrainingModel((TrainingEntity)this.getIntent().getSerializableExtra("trainingEntity"), db);

    }


}
