package com.belatrix.kotlintemplate.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.belatrix.kotlintemplate.R;
import com.belatrix.kotlintemplate.model.NoteEntity;

import java.util.List;

/**
 * @author : Eduardo Medina
 */
public class NoteAdapter extends BaseAdapter {

    private Context context;
    private List<NoteEntity> lsNoteEntities;

    public NoteAdapter(Context context, List<NoteEntity> lsNoteEntities) {
        this.context = context;
        this.lsNoteEntities = lsNoteEntities;
    }

    @Override
    public int getCount() {
        return lsNoteEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return lsNoteEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.row_note, null);
            ViewHolder holder = new ViewHolder();
            holder.tviName = (TextView)v.findViewById(R.id.tviName);
            holder.imageViewFavourite = v.findViewById(R.id.imageViewFavourite);
            v.setTag(holder);
        }
        NoteEntity entry = lsNoteEntities.get(position);
        if(entry != null) {
            ViewHolder holder = (ViewHolder)v.getTag();
            holder.tviName.setText(entry.getName());
            if(entry.isFavourite()){
                holder.imageViewFavourite.setImageResource(
                        R.drawable.ic_favourite_2);
            }else {
                holder.imageViewFavourite.setImageResource(
                        R.drawable.ic_favourite);
            }
        }

        return v;
    }


    public void reset(List<NoteEntity> notes){
        this.lsNoteEntities= notes;
        notifyDataSetChanged();
    }

    static class ViewHolder
    {
        ImageView iviNote;
        TextView tviName;
        ImageView imageViewFavourite;
    }
}
