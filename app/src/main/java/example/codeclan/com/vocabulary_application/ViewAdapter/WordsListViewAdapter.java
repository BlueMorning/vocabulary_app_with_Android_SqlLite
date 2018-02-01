package example.codeclan.com.vocabulary_application.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.StatsModel;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;

import static example.codeclan.com.vocabulary_application.Utils.StringUtils.capitalize;


public class WordsListViewAdapter extends ArrayAdapter<WordModel> {


    public WordsListViewAdapter(Context context, ArrayList<WordModel> wordModels) {
        super(context, 0, wordModels);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.word_model_adapter, parent, false);
        }

        WordModel currentWordModel = (WordModel)getItem(position);

        TextView wordSpelling = listItemView.findViewById(R.id.listWordsWordSpelling);
        wordSpelling.setText(StringUtils.capitalize(currentWordModel.getWordEntity().getSpelling()));

        TextView wordType = listItemView.findViewById(R.id.listWordsWordType);
        wordType.setText(currentWordModel.getWordEntity().getType().getShortLabel());

        ImageView imageMasteryLevel = listItemView.findViewById(R.id.imageMasteryLevel);
        imageMasteryLevel.setImageResource(currentWordModel.getStatsModel().getMasteryLevelDrawableId());

        ProgressBar progressBar = listItemView.findViewById(R.id.listWordsWordTrainingStep);
        progressBar.setMax(TrainingModel.totalTrainingStep);
        progressBar.setMin(0);
        progressBar.setProgress(currentWordModel.getStatsModel().getStatsEntity().getTrainingStep());

        Button buttonModifyWord = listItemView.findViewById(R.id.listWordsButtonModify);
        buttonModifyWord.setTag(currentWordModel);

        Button buttonViewWord = listItemView.findViewById(R.id.listWordsButtonView);
        buttonViewWord.setTag(currentWordModel);

        listItemView.setTag(currentWordModel);

        return listItemView;

    }
}
