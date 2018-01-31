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

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Model.MeaningModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;
import example.codeclan.com.vocabulary_application.ViewAdapter.EnumWordTypeAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.MeaningsListAdapter;
import example.codeclan.com.vocabulary_application.ViewAdapter.ViewMeaningsListAdapter;

public class ViewWordActivity extends AppCompatActivity {


    private TextView viewWordSpelling;
    private TextView viewWordType;
    private ListView viewWordMeanings;
    private WordsRoomDatabase database;
    private WordModel wordModel;
    private Button viewWordDeleteButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_word);

        database                = WordsRoomDatabase.getDatabase(this);
        WordEntity wordEntity   = (WordEntity)this.getIntent().getSerializableExtra("wordEntity");
        wordModel               = new WordModel(wordEntity, database);

        viewWordSpelling        = findViewById(R.id.viewWordSpelling);
        viewWordType            = findViewById(R.id.viewWordType);
        viewWordMeanings        = findViewById(R.id.viewWordMeaningsList);
        viewWordDeleteButton    = findViewById(R.id.viewWordDeleteButton);

        viewWordDeleteButton.setTag(wordModel);



        viewWordSpelling.setText(StringUtils.capitalize(wordModel.getWordEntity().getSpelling()));
        viewWordType.setText(wordModel.getWordEntity().getType().getLabel());

        wordModel.initializeMeanings();
        ViewMeaningsListAdapter meaningsListAdapter = new ViewMeaningsListAdapter(this, this.wordModel.getMeaningsList());
        viewWordMeanings.setAdapter(meaningsListAdapter);

    }

    public void onClickViewWordCloseButton(View button){
        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }

    public void onClickViewWordDeleteButton(View button){

        WordModel wordModel = ((WordModel)(button).getTag());
        wordModel.delete();

        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }
}
