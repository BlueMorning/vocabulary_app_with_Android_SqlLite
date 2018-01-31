package example.codeclan.com.vocabulary_application.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Model.MeaningModel;
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
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.meaning_model_adapter, parent, false);
        }

        MeaningModel currentMeaningModel = (MeaningModel)getItem(position);

        TextView meaning = listItemView.findViewById(R.id.meaningAdapterMeaning);
        meaning.setText(currentMeaningModel.getMeaningEntity().getDefinition());

        TextView example = listItemView.findViewById(R.id.meaningAdapterExample);
        example.setText(currentMeaningModel.getMeaningEntity().getExample());

        TextView synomyms = listItemView.findViewById(R.id.meaningAdapterSynonyms);
        synomyms.setText(currentMeaningModel.getMeaningEntity().getSynonyms());

        TextView antonyms = listItemView.findViewById(R.id.meaningAdapterAntonyms);
        antonyms.setText(currentMeaningModel.getMeaningEntity().getAntonyms());

        listItemView.setTag(currentMeaningModel);

        return listItemView;

    }
}
