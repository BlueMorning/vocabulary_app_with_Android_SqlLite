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
import example.codeclan.com.vocabulary_application.Entity.MeaningEntity;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.MeaningModel;
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
    private MeaningModel currentMeaningModel;

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

        wordSpellingTextView = findViewById(R.id.viewWordSpelling);
        listWordTypesSpinner = findViewById(R.id.viewWordType);
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


        WordEntity wordEntity = (WordEntity)this.getIntent().getSerializableExtra("wordEntity");

        if(wordEntity == null){
            wordModel = new WordModel(database);
            wordModel.createWordEntity(wordSpellingTextView.getText().toString(),
                                       EnumWordType.ALL);
        }
        else{
            wordModel = new WordModel(wordEntity, database);
        }

        initializeWordFiels();
    }

    public void initializeWordFiels(){
        wordSpellingTextView.setText(wordModel.getWordEntity().getSpelling());

        for(int spinnerItemIndex = 0; spinnerItemIndex < listWordTypesSpinner.getCount(); spinnerItemIndex++){
            if((EnumWordType)(listWordTypesSpinner.getItemAtPosition(spinnerItemIndex)) == ((EnumWordType)wordModel.getWordEntity().getType())){
                listWordTypesSpinner.setSelection(spinnerItemIndex);
                break;
            }
        }

        wordModel.initializeMeanings();
        updateMeaningsList();
    }

    public void onClickCreateWordAddMeaningButton(View button){
        this.resetMeaningFields();
        currentMeaningModel = new MeaningModel(new MeaningEntity(null, "", "", "", ""));
        addMeaningSaveButton.setTag(currentMeaningModel);
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

        currentMeaningModel.getMeaningEntity().setMeaning(addMeaningMeaning.getText().toString());
        currentMeaningModel.getMeaningEntity().setExample(addMeaningExample.getText().toString());
        currentMeaningModel.getMeaningEntity().setSynonyms(addMeaningSynonyms.getText().toString());
        currentMeaningModel.getMeaningEntity().setAntonyms(addMeaningAntonyms.getText().toString());

        wordModel.addMeaning(currentMeaningModel);

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

        currentMeaningModel = null;

        addMeaningMeaning.setText("");
        addMeaningExample.setText("");
        addMeaningSynonyms.setText("");
        addMeaningAntonyms.setText("");
    }

    public void initializeMeaningsFiels(){
        addMeaningMeaning.setText(currentMeaningModel.getMeaningEntity().getMeaning());
        addMeaningExample.setText(currentMeaningModel.getMeaningEntity().getExample());
        addMeaningSynonyms.setText(currentMeaningModel.getMeaningEntity().getSynonyms());
        addMeaningAntonyms.setText(currentMeaningModel.getMeaningEntity().getAntonyms());
    }

    public void onClickMeaningModifyButton(View button){

        currentMeaningModel = ((MeaningModel)button.getTag());

        initializeMeaningsFiels();

        meaningListFrame.setVisibility(View.INVISIBLE);
        addMeaningFrame.setVisibility(View.VISIBLE);
    }

    public void onClickMeaningDeleteButton(View button){

        currentMeaningModel = ((MeaningModel)button.getTag());
        this.wordModel.removeMeaning(currentMeaningModel);
        updateMeaningsList();
    }


}
