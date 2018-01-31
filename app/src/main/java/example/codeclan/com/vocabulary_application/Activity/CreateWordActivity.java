package example.codeclan.com.vocabulary_application.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumWordTypeAdapter;

public class CreateWordActivity extends AppCompatActivity {


    private TextView wordSpellingTextView;
    private Spinner listWordTypesSpinner;
    private Button addMeaningButton;
    private ListView wordMeaningsListView;
    private Button saveWordButton;
    private Button cancelWordButton;

    private WordsRoomDatabase database;

    private EnumWordTypeAdapter enumWordTypeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_word);

        database = WordsRoomDatabase.getDatabase(this);

        wordSpellingTextView = findViewById(R.id.createWordSpelling);
        listWordTypesSpinner = findViewById(R.id.createWordTypesList);
        addMeaningButton     = findViewById(R.id.createWordAddMeaning);
        wordMeaningsListView = findViewById(R.id.createWordMeaningsList);
        saveWordButton       = findViewById(R.id.createWordSaveButton);
        cancelWordButton     = findViewById(R.id.createWordCancelButton);


        enumWordTypeAdapter = new EnumWordTypeAdapter(this, new ArrayList<>(Arrays.asList(EnumWordType.values())));
        listWordTypesSpinner.setAdapter(enumWordTypeAdapter);


    }
}
