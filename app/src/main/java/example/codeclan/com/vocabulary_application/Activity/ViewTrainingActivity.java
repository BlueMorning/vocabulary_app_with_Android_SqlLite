package example.codeclan.com.vocabulary_application.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.TrainingEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.ViewAdapter.TrainingWordsListViewAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.WordsListViewAdapter;

public class ViewTrainingActivity extends AppCompatActivity {


    private TrainingModel trainingModel;
    private ListView wordsListViewTraining;
    private TrainingWordsListViewAdapter trainingWordsListViewAdapter;
    private ArrayList<WordModel> wordModels;
    private WordsRoomDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_training);

        database        = WordsRoomDatabase.getDatabase(this);
        wordModels      = new ArrayList<WordModel>();
        trainingModel   = new TrainingModel((TrainingEntity)this.getIntent().getSerializableExtra("trainingEntity"), database);

        initializeViewitems();
        searchTrainingWords();
    }

    public void initializeViewitems(){

        wordsListViewTraining = findViewById(R.id.wordsListViewTraining);

    }

    public void searchTrainingWords(){

        List<WordEntity> wordsEntityList = database.trainingDao().getWordsByTrainingId(trainingModel.getTrainingEntity().getId());

        wordModels = new ArrayList<>(wordsEntityList.stream().map(wordEntity -> {
                    WordModel wm = new WordModel(wordEntity, database);
                    wm.initializeStatsModel();
                    return wm;
                }
        ).collect(Collectors.toList()));

        updateSearchResultsViewItems();
    }

    protected void updateSearchResultsViewItems(){
        trainingWordsListViewAdapter = new TrainingWordsListViewAdapter(this, wordModels);
        wordsListViewTraining.setAdapter(trainingWordsListViewAdapter);
    }

    public void onClickQuitViewTraining(View button){
        this.navigateToTrainingList();
    }

    public void onClickDeleteTraining(View button){

        new AlertDialog.Builder(this)
                .setTitle("Delete training")
                .setMessage(String.format("Do you really want to delete this training ?"))
                .setIcon(R.drawable.delete)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteTraining(button);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void deleteTraining(View button){
        this.trainingModel.delete();
        this.navigateToTrainingList();
    }

    public void onClickReviewWordsViewTraining(View button){

    }

    public void onClickPracticeViewTraining(View button){
        Intent intent = new Intent(this, PracticeTrainingActivity.class);
        intent.putExtra("trainingEntity", trainingModel.getTrainingEntity());
        startActivity(intent);
    }

    public void navigateToTrainingList(){
        Intent intent = new Intent(this, TrainingListActivity.class);
        startActivity(intent);
    }
}
