package example.codeclan.com.vocabulary_application.ViewAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import example.codeclan.com.vocabulary_application.Enumerations.EnumMasteryLevel;
import example.codeclan.com.vocabulary_application.R;

public class EnumMasteryLevelAdapter extends ArrayAdapter<EnumMasteryLevel> {

    public EnumMasteryLevelAdapter(Context context, ArrayList<EnumMasteryLevel> enumMasteryLevels) {
        super(context, 0, enumMasteryLevels);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.word_mastery_level_adapter, parent, false);
        }

        EnumMasteryLevel enumMasteryLevel = (EnumMasteryLevel)getItem(position);

        TextView masteryLevel = listItemView.findViewById(R.id.masteryLevelAdapterType);
        masteryLevel.setText(enumMasteryLevel.getLabel());


        listItemView.setTag(enumMasteryLevel);

        return listItemView;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
