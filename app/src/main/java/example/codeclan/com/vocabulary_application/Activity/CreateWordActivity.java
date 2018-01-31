package example.codeclan.com.vocabulary_application.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumWordTypeAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.MeaningsListAdapter;

public class CreateWordActivity extends AppCompatActivity {


    private TextView wordSpellingTextView;
    private Spinner listWordTypesSpinner;
    private Button addMeaningButton;
    private ListView wordMeaningsListView;
    private Button saveWordButton;
    private Button cancelWordButton;
    private FrameLayout meaningListFrame;


    private WordsRoomDatabase database;
    private WordModel wordModel;

    private EnumWordTypeAdapter enumWordTypeAdapter;


    /* Add/Modify a meaning for a word */
    private FrameLayout addMeaningFrame;
    private TextView addMeaningMeaning;
    private TextView addMeaningExample;
    private TextView addMeaningSynonyms;
    private TextView addMeaningAntonyms;
    private Button addMeaningCancelButton;
    private Button addMeaningSaveButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_word);

        database = WordsRoomDatabase.getDatabase(this);

        wordSpellingTextView = findViewById(R.id.createWordSpelling);
        listWordTypesSpinner = findViewById(R.id.createWordTypesList);
        addMeaningButton     = findViewById(R.id.createWordAddMeaningButton);
        wordMeaningsListView = findViewById(R.id.createWordMeaningsList);
        saveWordButton       = findViewById(R.id.createWordSaveButton);
        cancelWordButton     = findViewById(R.id.createWordCancelButton);
        meaningListFrame     = findViewById(R.id.meaningListFrame);
        meaningListFrame.setVisibility(View.VISIBLE);

        enumWordTypeAdapter = new EnumWordTypeAdapter(this, new ArrayList<>(Arrays.asList(EnumWordType.values())));
        listWordTypesSpinner.setAdapter(enumWordTypeAdapter);

        /* Add/Modify a meaning for a word */
        addMeaningMeaning           = findViewById(R.id.meaningAdapterMeaning);
        addMeaningExample           = findViewById(R.id.meaningAdapterExample);
        addMeaningSynonyms          = findViewById(R.id.meaningAdapterSynonyms);
        addMeaningAntonyms          = findViewById(R.id.addMeaningAntonyms);
        addMeaningCancelButton      = findViewById(R.id.addMeaningCancelButton);
        addMeaningSaveButton        = findViewById(R.id.addMeaningSaveButton);
        addMeaningFrame             = findViewById(R.id.addMeaningFrame);

        addMeaningFrame.setVisibility(View.INVISIBLE);


        wordModel = new WordModel(database);
        wordModel.createWordEntity(wordSpellingTextView.getText().toString(),
                                  ((EnumWordType)listWordTypesSpinner.getSelectedItem()));

    }

    public void onClickCreateWordAddMeaningButton(View button){
        this.resetMeaningFields();
        meaningListFrame.setVisibility(View.INVISIBLE);
        addMeaningFrame.setVisibility(View.VISIBLE);
    }

    public void onClickCreateWordSaveButton(View button){

        wordModel.getWordEntity().setSpelling(wordSpellingTextView.getText().toString());
        wordModel.getWordEntity().setType((EnumWordType)listWordTypesSpinner.getSelectedItem());
        wordModel.saveWord();

        startWordsListActivity();
    }

    public void onclickCreateWordCancelButton(View button){
        startWordsListActivity();
    }

    public void startWordsListActivity(){
        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }

    public void onClickAddMeaningSaveButton(View button){
        wordModel.addMeaning(addMeaningMeaning.getText().toString(),
                             addMeaningExample.getText().toString(),
                             addMeaningSynonyms.getText().toString(),
                             addMeaningAntonyms.getText().toString());

        updateMeaningsList();

        meaningListFrame.setVisibility(View.VISIBLE);
        addMeaningFrame.setVisibility(View.INVISIBLE);
    }

    public void onClickAddMeaningCancelButton(View button){
        meaningListFrame.setVisibility(View.VISIBLE);
        addMeaningFrame.setVisibility(View.INVISIBLE);
    }

    public void updateMeaningsList(){
        MeaningsListAdapter meaningsListAdapter = new MeaningsListAdapter(this, this.wordModel.getMeaningsList());
        wordMeaningsListView.setAdapter(meaningsListAdapter);
    }

    public void resetMeaningFields(){
        addMeaningMeaning.setText("");
        addMeaningExample.setText("");
        addMeaningSynonyms.setText("");
        addMeaningAntonyms.setText("");
    }


}
