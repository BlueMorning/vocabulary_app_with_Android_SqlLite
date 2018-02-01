package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.ViewAdapter.TrainingListAdapter;

public class TrainingListActivity extends AppCompatActivity {

    private ListView    trainingsList;
    private TextView    searchTrainingsCount;
    private Spinner     trainingPriorities;
    private Spinner     trainingWordsCount;
    private Spinner     trainingWordsStatus;
    private Button      createTrainingButton;

    private WordsRoomDatabase db;
    private ArrayList<TrainingModel> trainingModels;
    private TrainingListAdapter trainingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_list);

        db = WordsRoomDatabase.getDatabase(this);

        initializeViewItems();

        searchTrainings();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_vocab:
                startActivity(new Intent(this, WordsListActivity.class));
                return true;
            case R.id.nav_training:
                startActivity(new Intent(this, TrainingListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initializeViewItems(){
        trainingsList                       = findViewById(R.id.trainingsList);
        searchTrainingsCount                = findViewById(R.id.searchTrainingsCount);
        createTrainingButton                = findViewById(R.id.createTrainingButton);
        trainingPriorities                  = findViewById(R.id.trainingPriorities);
        trainingWordsCount                  = findViewById(R.id.trainingWordsCount);
        trainingWordsStatus                 = findViewById(R.id.trainingWordsStatus);

    }

    protected void searchTrainings(){

        List<TrainingEntity> trainingsEntityList;

        /*
        spelling         = spelling.trim().isEmpty() ?                              "" : spelling;
        enumWordType     = (EnumWordType.ALL.toString()     == enumWordType) ?      "" : enumWordType;
        enumMasteryLevel = (EnumMasteryLevel.ALL.toString() == enumMasteryLevel) ?  "" : enumMasteryLevel;
        */

        trainingsEntityList = db.trainingDao().getAll();

        trainingModels = new ArrayList<>(trainingsEntityList.stream().map(trainingEntity -> {
                    return new TrainingModel(trainingEntity, db);
                }
        ).collect(Collectors.toList()));

        updateSearchResultsViewItems();
    }

    protected void updateSearchResultsViewItems(){

        trainingListAdapter = new TrainingListAdapter(this, trainingModels);
        trainingsList.setAdapter(trainingListAdapter);

        searchTrainingsCount.setText(String.format("Training : %s (Max. 100)", trainingModels.size()));
    }

    public void onClickCreateTraining(View button){

    }
}
