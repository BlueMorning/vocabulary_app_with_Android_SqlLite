package example.codeclan.com.vocabulary_application.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

import example.codeclan.com.vocabulary_application.Database.WordsRoomDatabase;
import example.codeclan.com.vocabulary_application.Entity.WordEntity;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;
import example.codeclan.com.vocabulary_application.ViewAdapter.ViewMeaningsListAdapter;

public class ViewWordActivity extends AppCompatActivity {


    // Tab Item Word
    private TextView viewWordSpelling;
    private TextView viewWordType;
    private ListView viewWordMeanings;
    private WordsRoomDatabase database;
    private WordModel wordModel;
    private Button viewWordDeleteButton;

    // Tab Item Stats
    private TextView wordStatsWordSpelling;
    private TextView wordStatsLatestTestWrongAnswersPercentage;
    private TextView wordStatsLatestTestRightAnswersPercentage;

    private TextView wordStatsAllTestRightAnswersPercentage;
    private TextView wordStatsAllTestWrongAnswersPercentage;

    private TextView wordStatsAllTestWrongAnswers;
    private TextView wordStatsAllTestRightAnswers;

    private TextView wordStatsLatestTestWrongAnswers;
    private TextView wordStatsLatestTestRightAnswers;

    private ProgressBar wordStatsTrainingStepProgressBar;
    private TextView wordStatsTrainingStep;

    private ImageView imageMasteryLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_word);

        database                = WordsRoomDatabase.getDatabase(this);
        WordEntity wordEntity   = (WordEntity)this.getIntent().getSerializableExtra("wordEntity");
        wordModel               = new WordModel(wordEntity, database);

        TabHost mTabHost;
        mTabHost = (TabHost)findViewById(R.id.viewWordTabHost);
        mTabHost.setup();

        TabHost.TabSpec spec1 = mTabHost.newTabSpec("Word");
        spec1.setContent(R.id.viewWordTabWord);
        spec1.setIndicator("Word");
        mTabHost.addTab(spec1);

        TabHost.TabSpec spec2 = mTabHost.newTabSpec("Stats");
        spec2.setContent(R.id.viewWordTabStats);
        spec2.setIndicator("Stats");
        mTabHost.addTab(spec2);

        this.initializeWordItemView();
        this.initializeWordStatsViewItem();

    }

    public void onClickViewWordCloseButton(View button){
        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }

    public void onClickViewWordDeleteButton(View button){

        WordModel wordModel = ((WordModel)(button).getTag());

        new AlertDialog.Builder(this)
                .setTitle("Delete a Word")
                .setMessage(String.format("Are you sure to delete %s ?", wordModel.getWordEntity().getSpelling().toUpperCase()))
                .setIcon(R.drawable.delete)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteWord(button);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void deleteWord(View button){
        WordModel wordModel = ((WordModel)(button).getTag());
        wordModel.delete();
        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }


    public void initializeWordItemView(){

        viewWordSpelling        = findViewById(R.id.viewWordSpelling);
        viewWordType            = findViewById(R.id.viewWordType);
        viewWordMeanings        = findViewById(R.id.viewWordMeaningsList);
        viewWordDeleteButton    = findViewById(R.id.viewWordDeleteButton);

        viewWordDeleteButton.setTag(wordModel);

        viewWordSpelling.setText(StringUtils.capitalize(wordModel.getWordEntity().getSpelling().toUpperCase()));
        viewWordType.setText(wordModel.getWordEntity().getType().getLabel());

        wordModel.initializeMeanings();
        ViewMeaningsListAdapter meaningsListAdapter = new ViewMeaningsListAdapter(this,
                this.wordModel.getMeaningsList());
        viewWordMeanings.setAdapter(meaningsListAdapter);
    }

    public void initializeWordStatsViewItem(){

        wordStatsWordSpelling                       = findViewById(R.id.wordStatsWordSpelling);

        wordStatsLatestTestWrongAnswersPercentage   = findViewById(R.id.wordStatsLatestTestWrongAnswersPercentage);
        wordStatsLatestTestRightAnswersPercentage   = findViewById(R.id.wordStatsLatestTestRightAnswersPercentage);

        wordStatsAllTestRightAnswersPercentage      = findViewById(R.id.wordStatsAllTestRightAnswersPercentage);
        wordStatsAllTestWrongAnswersPercentage      = findViewById(R.id.wordStatsAllTestWrongAnswersPercentage);

        wordStatsAllTestWrongAnswers                = findViewById(R.id.wordStatsAllTestWrongAnswers);
        wordStatsAllTestRightAnswers                = findViewById(R.id.wordStatsAllTestRightAnswers);

        wordStatsLatestTestWrongAnswers             = findViewById(R.id.wordStatsLatestTestWrongAnswers);
        wordStatsLatestTestRightAnswers             = findViewById(R.id.wordStatsLatestTestRightAnswers);

        wordStatsTrainingStepProgressBar            = findViewById(R.id.wordStatsTrainingStepProgressBar);
        wordStatsTrainingStep                       = findViewById(R.id.wordStatsTrainingStep);

        imageMasteryLevel                           = findViewById(R.id.imageMasteryLevel);

        wordStatsWordSpelling.setText(StringUtils.capitalize(wordModel.getWordEntity().getSpelling().toUpperCase()));

        wordStatsLatestTestWrongAnswersPercentage.setText(
                wordModel.getStatsModel().getLastTrainingTotalIncorrectAnswersPercentageLabel());

        wordStatsLatestTestRightAnswersPercentage.setText(
                wordModel.getStatsModel().getLastTrainingTotalCorrectAnswersPercentageLabel());

        wordStatsAllTestRightAnswersPercentage.setText(
                wordModel.getStatsModel().getTotalCorrectAnswersPercentageLabel());

        wordStatsAllTestWrongAnswersPercentage.setText(
                wordModel.getStatsModel().getTotalIncorrectAnswersPercentageLabel());

        wordStatsAllTestWrongAnswers.setText(
                StringUtils.intToString(wordModel.getStatsModel().getTotalIncorrectAnswers()));

        wordStatsAllTestRightAnswers.setText(
                StringUtils.intToString(wordModel.getStatsModel().getTotalCorrectAnswers()));

        wordStatsLatestTestWrongAnswers.setText(
                StringUtils.intToString(wordModel.getStatsModel().getLastTrainingTotalIncorrectAnswers()));

        wordStatsLatestTestRightAnswers.setText(
                StringUtils.intToString(wordModel.getStatsModel().getLastTrainingTotalCorrectAnswers()));

        wordStatsTrainingStepProgressBar.setProgress(
                wordModel.getStatsModel().getTrainingStep());

        wordStatsTrainingStep.setText(
                wordModel.getStatsModel().getTrainingStepLabel());

        imageMasteryLevel.setImageResource(
                wordModel.getStatsModel().getMasteryLevelDrawableId());
    }
}
