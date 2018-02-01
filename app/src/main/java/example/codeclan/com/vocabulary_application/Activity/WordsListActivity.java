package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import example.codeclan.com.vocabulary_application.Database.DatabaseRunner;
import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.StatsModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumMasteryLevelAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumWordTypeAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.WordsListViewAdapter;

public class WordsListActivity extends AppCompatActivity {

    private TextView                    wordsSearchBar;
    private Spinner                     wordTypes;
    private Spinner                     wordsMasteryLevels;
    private TextView                    searchResultCount;
    private Button                      createWordButton;
    private ListView                    wordsList;
    private WordsRoomDatabase           database;
    private ArrayList<WordModel>        wordModels;
    private WordsListViewAdapter        wordsListViewAdapter;
    private EnumWordTypeAdapter         enumWordTypeAdapter;
    private EnumMasteryLevelAdapter     enumMasteryLevelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        database                = WordsRoomDatabase.getDatabase(this);

        wordModels              = new ArrayList<>();

        wordsSearchBar          = findViewById(R.id.wordsSearchBar);
        wordTypes               = findViewById(R.id.wordTypes);
        wordsMasteryLevels      = findViewById(R.id.wordsConfidenceLevels);
        searchResultCount       = findViewById(R.id.searchResultCount);
        createWordButton        = findViewById(R.id.createWordButton);
        wordsList               = findViewById(R.id.wordsList);

        wordsSearchBar.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchWords();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        enumWordTypeAdapter = new EnumWordTypeAdapter(this, new ArrayList<>(Arrays.asList(EnumWordType.values())));
        wordTypes.setAdapter(enumWordTypeAdapter);


        wordTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchWords();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        enumMasteryLevelAdapter = new EnumMasteryLevelAdapter(this, new ArrayList<>(Arrays.asList(EnumMasteryLevel.values())));
        wordsMasteryLevels.setAdapter(enumMasteryLevelAdapter);

        wordsMasteryLevels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchWords();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        searchWords();
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

    protected void searchWords(){

        List<WordEntity> wordsEntityList;

        String spelling          = wordsSearchBar.getText().toString();
        String enumWordType      = ((EnumWordType)wordTypes.getSelectedItem()).toString();
        String enumMasteryLevel  = ((EnumMasteryLevel) wordsMasteryLevels.getSelectedItem()).toString();

        spelling         = spelling.trim().isEmpty() ?                              "" : spelling;
        enumWordType     = (EnumWordType.ALL.toString()     == enumWordType) ?      "" : enumWordType;
        enumMasteryLevel = (EnumMasteryLevel.ALL.toString() == enumMasteryLevel) ?  "" : enumMasteryLevel;

        wordsEntityList = database.wordDao().getAllBySpellingAndTypeAndMastery(spelling, enumWordType, enumMasteryLevel);

        wordModels = new ArrayList<>(wordsEntityList.stream().map(wordEntity -> {
            WordModel wm = new WordModel(wordEntity, database);
            wm.initialiseStatsModel();
            return wm;
        }
        ).collect(Collectors.toList()));

        voidUpdateResults();
    }


    protected void voidUpdateResults(){

        wordsListViewAdapter = new WordsListViewAdapter(this, wordModels);
        wordsList.setAdapter(wordsListViewAdapter);

        searchResultCount.setText(String.format("Words : %s (Max. 100)", wordModels.size()));
    }

    public void onClickCreateWord(View button){
        Intent intent = new Intent(this, CreateWordActivity.class);
        startActivity(intent);
    }

    public void onClickModifyWord(View button){
        Intent intent = new Intent(this, CreateWordActivity.class);
        WordEntity wordEntityToModify = ((WordModel)button.getTag()).getWordEntity();
        intent.putExtra("wordEntity", wordEntityToModify);
        startActivity(intent);
    }

    public void onClickViewWord(View button){
        Intent intent = new Intent(this, ViewWordActivity.class);
        WordEntity wordEntityToView = ((WordModel)button.getTag()).getWordEntity();
        intent.putExtra("wordEntity", wordEntityToView);
        startActivity(intent);
    }

}
