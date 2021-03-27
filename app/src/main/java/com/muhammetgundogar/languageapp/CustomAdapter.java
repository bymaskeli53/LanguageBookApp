package com.muhammetgundogar.languageapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Words> {
    ArrayList<Words> wordslist;
    Context context;
    public CustomAdapter(@NonNull Context context,  ArrayList<Words> wordslist) {
        super(context, R.layout.custom_view,wordslist);
        this.context=context;
        this.wordslist=wordslist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View customView = layoutInflater.inflate(R.layout.custom_view,parent,false);
        TextView EnglishTextView=customView.findViewById(R.id.textViewEnglish);
        TextView TurkishTextView=customView.findViewById(R.id.textViewTurkish);
        EnglishTextView.setText(wordslist.get(position).english);
        TurkishTextView.setText(wordslist.get(position).turkish);


        return customView;
    }
}
