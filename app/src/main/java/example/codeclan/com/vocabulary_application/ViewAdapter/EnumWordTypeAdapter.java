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

import example.codeclan.com.vocabulary_application.Enumerations.EnumWordType;
import example.codeclan.com.vocabulary_application.Model.WordModel;
import example.codeclan.com.vocabulary_application.R;

public class EnumWordTypeAdapter extends ArrayAdapter<EnumWordType> {

    public EnumWordTypeAdapter(Context context, ArrayList<EnumWordType> enumWordTypes) {
        super(context, 0, enumWordTypes);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.word_type_adapter, parent, false);
        }

        EnumWordType enumWordType = (EnumWordType)getItem(position);

        TextView wordSpelling = listItemView.findViewById(R.id.wordTypeAdapterType);
        wordSpelling.setText(enumWordType.getLabel());


        listItemView.setTag(enumWordType);

        return listItemView;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
