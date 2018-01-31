package example.codeclan.com.vocabulary_application.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Model.MeaningModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;

/**
 * Created by horizon on 31/01/2018.
 */

public class MeaningsListAdapter extends ArrayAdapter<MeaningModel> {

    public MeaningsListAdapter(Context context, ArrayList<MeaningModel> meaningModels) {
        super(context, 0, meaningModels);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.word_model_adapter, parent, false);
        }

        WordModel currentWordModel = (WordModel)getItem(position);

        TextView wordSpelling = listItemView.findViewById(R.id.listWordsWordSpelling);
        wordSpelling.setText(currentWordModel.getWordEntity().getSpelling());

        TextView wordType = listItemView.findViewById(R.id.listWordsWordType);
        wordType.setText(currentWordModel.getWordEntity().getType().getShortLabel());

        ImageView imageMasteryLevel = listItemView.findViewById(R.id.imageMasteryLevel);
        imageMasteryLevel.setImageResource(currentWordModel.getMasteryLevelDrawableId());

        Button buttonModifyWord = listItemView.findViewById(R.id.listWordsButtonModify);
        buttonModifyWord.setTag(currentWordModel);

        listItemView.setTag(currentWordModel);

        return listItemView;

    }
}
