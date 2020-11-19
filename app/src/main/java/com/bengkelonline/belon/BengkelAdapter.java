package com.bengkelonline.belon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BengkelAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Bengkel> bengkels;

    void setBengkels(ArrayList<Bengkel> bengkels) {
        this.bengkels = bengkels;
    }

    BengkelAdapter(Context context) {
        this.context = context;
        bengkels = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return bengkels.size();
    }

    @Override
    public Object getItem(int i) {
        return bengkels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_bengkel,
                    viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Bengkel hero = (Bengkel) getItem(i);
        viewHolder.bind(hero);
        return view;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);

        }

        void bind(Bengkel hero) {
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }


}