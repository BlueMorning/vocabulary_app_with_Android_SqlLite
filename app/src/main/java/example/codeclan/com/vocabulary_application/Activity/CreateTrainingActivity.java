package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingPriority;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingWordCount;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumTrainingPriorityAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumTrainingStatusAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumTrainingsWordsCountAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumWordTypeAdapter;

public class CreateTrainingActivity extends AppCompatActivity {


    private Spinner createTrainingWordCountList;
    private Spinner createTrainingWordTypeList;

    private EnumTrainingsWordsCountAdapter enumTrainingsWordsCountAdapter;
    private EnumWordTypeAdapter enumWordTypeAdapter;

    private WordsRoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);

        db = WordsRoomDatabase.getDatabase(this);

        createTrainingWordCountList = findViewById(R.id.createTrainingWordCountList);
        createTrainingWordTypeList  = findViewById(R.id.createTrainingWordTypeList);

        enumTrainingsWordsCountAdapter = new EnumTrainingsWordsCountAdapter(this, new ArrayList<>(Arrays.asList(EnumTrainingWordCount.values())));
        createTrainingWordCountList.setAdapter(enumTrainingsWordsCountAdapter);

        enumWordTypeAdapter = new EnumWordTypeAdapter(this, new ArrayList<>(Arrays.asList(EnumWordType.values())));
        createTrainingWordTypeList.setAdapter(enumWordTypeAdapter);
    }


    public void onClickCreateTrainingButton(View button){
        TrainingModel.createNewTraining(db, (EnumTrainingWordCount)createTrainingWordCountList.getSelectedItem(),
                                        (EnumWordType)createTrainingWordTypeList.getSelectedItem());

        Intent intent = new Intent(this, TrainingListActivity.class);
        startActivity(intent);
    }

    public void onClickCancelCreateTrainingButton(View button){
        Intent intent = new Intent(this, TrainingListActivity.class);
        startActivity(intent);
    }
}
