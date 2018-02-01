package example.codeclan.com.vocabulary_application.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingPriority;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingStatus;
import example.codeclan.com.vocabulary_application.R;

/**
 * Created by horizon on 01/02/2018.
 */

public class EnumTrainingStatusAdapter extends ArrayAdapter<EnumTrainingStatus> {

    public EnumTrainingStatusAdapter(Context context, ArrayList<EnumTrainingStatus> enumTrainingStatus) {
        super(context, 0, enumTrainingStatus);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.training_status_adapter, parent, false);
        }

        EnumTrainingStatus enumTrainingStatus = (EnumTrainingStatus)getItem(position);

        TextView trainingStatus = listItemView.findViewById(R.id.trainingStatusListAdapterStatus);
        trainingStatus.setText(enumTrainingStatus.getLabel());

        listItemView.setTag(enumTrainingStatus);

        return listItemView;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}