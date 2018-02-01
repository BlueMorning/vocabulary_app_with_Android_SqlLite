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

import example.codeclan.com.vocabulary_application.Model.StatsModel;
import example.codeclan.com.vocabulary_application.Model.TrainingModel;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;
import example.codeclan.com.vocabulary_application.Utils.StringUtils;

/**
 * Created by horizon on 01/02/2018.
 */

public class TrainingsListAdapter extends ArrayAdapter<TrainingModel> {


    public TrainingsListAdapter(Context context, ArrayList<TrainingModel> trainingModels) {
        super(context, 0, trainingModels);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.training_model_adapter, parent, false);
        }

        TrainingModel currentTrainingModel = (TrainingModel)getItem(position);


        TextView    trainingsListNumber         = listItemView.findViewById(R.id.trainingsListNumber);
        TextView    trainingsListTotalWords     = listItemView.findViewById(R.id.trainingsListtotalWords);
        TextView    trainingsListNextBestDate   = listItemView.findViewById(R.id.trainingsListNextBestDate);
        ImageView   trainingsListImageTiming    = listItemView.findViewById(R.id.trainingsListImageTiming);
        TextView    trainingsListStep           = listItemView.findViewById(R.id.trainingsListStep);

        trainingsListNumber.setText(currentTrainingModel.getNumber());
        trainingsListTotalWords.setText(currentTrainingModel.getTotalWords());


        listItemView.setTag(currentTrainingModel);

        return listItemView;

    }
}
