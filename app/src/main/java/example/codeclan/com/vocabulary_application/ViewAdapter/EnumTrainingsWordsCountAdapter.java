package example.codeclan.com.vocabulary_application.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingPriority;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingWordCount;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;

/**
 * Created by horizon on 01/02/2018.
 */

public class EnumTrainingsWordsCountAdapter extends ArrayAdapter<EnumTrainingWordCount> {

    public EnumTrainingsWordsCountAdapter(Context context, ArrayList<EnumTrainingWordCount> enumTrainingWordCount) {
        super(context, 0, enumTrainingWordCount);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.training_words_count_adapter, parent, false);
        }

        EnumTrainingWordCount enumTrainingWordCount = (EnumTrainingWordCount) getItem(position);

        TextView trainingWordCount = listItemView.findViewById(R.id.trainingListAdapterWordsCount);
        trainingWordCount.setText(enumTrainingWordCount.getWordCountLabel());

        listItemView.setTag(enumTrainingWordCount);

        return listItemView;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}



