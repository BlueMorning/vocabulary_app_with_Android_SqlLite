package example.codeclan.com.vocabulary_application.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.Enumerations.EnumTrainingPriority;
import example.codeclan.com.vocabulary_application.R;

/**
 * Created by horizon on 01/02/2018.
 */

public class EnumTrainingPriorityAdapter extends ArrayAdapter<EnumTrainingPriority> {

    public EnumTrainingPriorityAdapter(Context context, ArrayList<EnumTrainingPriority> enumTrainingPriority) {
        super(context, 0, enumTrainingPriority);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.training_priority_adapter, parent, false);
        }

        EnumTrainingPriority enumTrainingPriority = (EnumTrainingPriority)getItem(position);

        TextView trainingPriority = listItemView.findViewById(R.id.trainingPriorityListAdapterPriority);
        trainingPriority.setText(enumTrainingPriority.getLabel());

        listItemView.setTag(enumTrainingPriority);

        return listItemView;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
