package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        DatabaseRunner.fuelDatabase(this);

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
            WordModel wm = new WordModel(wordEntity);
            wm.setStatsModel(new StatsModel(database.statsDao().getStatsByWordId(wordEntity.getId())));
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

}
