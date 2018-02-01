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
import android.widget.Spinner;
import android.widget.TextView;

import example.codeclan.com.vocabulary_application.R;

public class TrainingListActivity extends AppCompatActivity {

    private ListView trainingsList;
    private TextView searchTrainingsCount;
    private Spinner  trainingPriorities;
    private Spinner  trainingWordsCount;
    private Spinner  trainingWordsStatus;
    private Button   createTrainingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_list);
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
        trainingsList           = findViewById(R.id.trainingsList);
        searchTrainingsCount    = findViewById(R.id.searchTrainingsCount);
        createTrainingButton     = findViewById(R.id.createTrainingButton);
        trainingPriorities      = findViewById(R.id.trainingPriorities);
        trainingWordsCount      = findViewById(R.id.trainingWordsCount);
        trainingWordsStatus     = findViewById(R.id.trainingWordsStatus);
    }

    public void onClickCreateTraining(View button){

    }
}
