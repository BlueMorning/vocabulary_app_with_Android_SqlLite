package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingPriority;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingWordCount;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumTrainingPriorityAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumTrainingStatusAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumTrainingsWordsCountAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.TrainingListAdapter;

public class TrainingListActivity extends AppCompatActivity {

    private ListView    trainingsList;
    private TextView    searchTrainingsCount;
    private Spinner     trainingPriorities;
    private Spinner     trainingWordsCount;
    private Spinner     trainingStatus;
    private Button      createTrainingButton;

    private WordsRoomDatabase db;
    private ArrayList<TrainingModel> trainingModels;
    private TrainingListAdapter trainingListAdapter;
    private EnumTrainingPriorityAdapter enumTrainingPriorityAdapter;
    private EnumTrainingStatusAdapter enumTrainingStatusAdapter;
    private EnumTrainingsWordsCountAdapter enumTrainingsWordsCountAdapter;

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
        trainingPriorities                  = findViewById(R.id.trainingPrioritiesList);
        trainingWordsCount                  = findViewById(R.id.trainingWordsCountList);
        trainingStatus                      = findViewById(R.id.trainingStatusList);

        enumTrainingPriorityAdapter = new EnumTrainingPriorityAdapter(this, new ArrayList<>(Arrays.asList(EnumTrainingPriority.values())));
        trainingPriorities.setAdapter(enumTrainingPriorityAdapter);


        trainingPriorities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchTrainings();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        enumTrainingStatusAdapter = new EnumTrainingStatusAdapter(this, new ArrayList<>(Arrays.asList(EnumTrainingStatus.values())));
        trainingStatus.setAdapter(enumTrainingStatusAdapter);


        trainingStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchTrainings();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        enumTrainingsWordsCountAdapter = new EnumTrainingsWordsCountAdapter(this, new ArrayList<>(Arrays.asList(EnumTrainingWordCount.values())));
        trainingWordsCount.setAdapter(enumTrainingsWordsCountAdapter);


        trainingWordsCount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchTrainings();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    protected void searchTrainings(){

        List<TrainingEntity> trainingsEntityList;


        String enumPriority     = ((EnumTrainingPriority)trainingPriorities.getSelectedItem()).toString();
        int enumWordCount       = ((EnumTrainingWordCount)trainingWordsCount.getSelectedItem()).getWordCount();
        String enumStatus       = ((EnumTrainingStatus)trainingStatus.getSelectedItem()).toString();

        enumPriority            = (EnumTrainingPriority.ALL.toString()      == enumPriority)    ?  "" : enumPriority;
        enumWordCount           = (EnumTrainingWordCount.ALL.getWordCount() == enumWordCount)   ?  0 : enumWordCount;
        enumStatus              = (EnumTrainingStatus.ALL.toString()        == enumStatus)      ?  "" : enumStatus;


        trainingsEntityList = db.trainingDao().getTrainingByPriorityAndWordCountAndStatus(enumPriority, enumWordCount, enumStatus);

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
