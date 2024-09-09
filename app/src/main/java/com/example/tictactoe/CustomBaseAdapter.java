package com.example.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String[] langNames;
    int[] flags;
    LayoutInflater inflater;
    public CustomBaseAdapter(Context context,String [] langNames,int[] flags){
        this.context=context;
        this.langNames=langNames;
        this.flags=flags;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return langNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView name=view.findViewById(R.id.langText);
        ImageView image=view.findViewById(R.id.flagImage);
        name.setText(langNames[i]);
        image.setImageResource(flags[i]);
        return view;
    }
}
