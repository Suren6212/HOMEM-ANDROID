package com.application.smardanyan.myapplication.adapters;

/**
 * Created by smardanyan on 17.11.2014.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.smardanyan.myapplication.R;
import com.application.smardanyan.myapplication.data.Category;
import com.application.smardanyan.myapplication.data.Master;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public GridAdapter(Context context, List<Category> categories, List<Master> masters) {
        inflater = LayoutInflater.from(context);

        items.add(new Item(context.getResources().getString(R.string.all), R.drawable.all, 0, masters.size()));
        for (Category category : categories) {
            int textKey = context.getResources().getIdentifier (category.key,"string",context.getPackageName());
            String text;
            if (textKey == 0) {
                text = category.key;
            } else {
                text = context.getResources().getString(textKey);
            }

            int imageKey = context.getResources().getIdentifier(category.key, "drawable", context.getPackageName());
            if (imageKey == 0) {
                imageKey = context.getResources().getIdentifier("no_image", "drawable", context.getPackageName());
            }

            int count = 0;
            for (Master master : masters) {
                if (master.master_categories.indexOf(category.key) != -1 ) {
                    count++;
                }
            }

            items.add(new Item(text, imageKey, category.id, count));
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;
        TextView count;

        if (v == null) {
            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
            v.setTag(R.id.count, v.findViewById(R.id.count));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);
        count = (TextView) v.getTag(R.id.count);

        Item item = (Item) getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);
        count.setText(Integer.toString(item.count));

        v.setContentDescription(Integer.toString(item.id));

        return v;
    }

    private class Item {
        final int id;
        final String name;
        final int drawableId;
        final int count;

        Item(String name, int drawableId, int id, int count) {
            this.id = id;
            this.name = name;
            this.drawableId = drawableId;
            this.count = count;
        }
    }
}
