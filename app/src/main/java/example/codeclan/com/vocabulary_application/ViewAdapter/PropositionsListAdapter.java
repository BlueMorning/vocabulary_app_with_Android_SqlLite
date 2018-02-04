package example.codeclan.com.vocabulary_application.ViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Model.PropositionModel;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.R;

/**
 * Created by horizon on 04/02/2018.
 */

public class PropositionsListAdapter extends ArrayAdapter<PropositionModel> {

    public PropositionsListAdapter(Context context, ArrayList<PropositionModel> propositionModels) {
        super(context, 0, propositionModels);
    }

    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.propositions_model_adapter, parent, false);
        }

        PropositionModel currentPropositionModel = (PropositionModel)getItem(position);

        TextView propositionLabel      = listItemView.findViewById(R.id.propositionAdapterPropositionLabel);
        propositionLabel.setText(currentPropositionModel.getProposition());

        Button buttonSelectProposition =  listItemView.findViewById(R.id.propositionAdapterAnswerButton);
        buttonSelectProposition.setTag(currentPropositionModel);

        return listItemView;
    }
}
