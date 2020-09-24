package com.ayata.mypackagesjava.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ayata.mypackagesjava.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ColorsAdapter extends ArrayAdapter<Color> {

    public ColorsAdapter(@NonNull Context context, List<Color> colors) {
        super(context, R.layout.layout_colors, colors);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        myViewHolder holder;
        Color color = getItem(position);
        if (convertView == null) {
            holder = new myViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_colors, parent,false);
            holder.textView = convertView.findViewById(R.id.text_color_name);
            holder.view = convertView.findViewById(R.id.view_color);
            convertView.setTag(holder);
        } else {
            holder = (myViewHolder) convertView.getTag();
        }

        holder.textView.setText(color.getColorName());
        holder.view.setBackgroundColor(android.graphics.Color.parseColor(color.getColor()));
        return convertView;
    }

    private static class myViewHolder {
        TextView textView;
        View view;
    }
}
