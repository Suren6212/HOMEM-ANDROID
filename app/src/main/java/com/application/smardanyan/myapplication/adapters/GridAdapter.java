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

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public GridAdapter(Context context, List<Category> categories) {
        inflater = LayoutInflater.from(context);

        items.add(new Item(context.getResources().getString(R.string.all), R.drawable.all));
        for (Category category : categories) {
            int imageKey = context.getResources().getIdentifier(category.key, "drawable", context.getPackageName());
            if (imageKey == 0) {
                imageKey = context.getResources().getIdentifier("no_image", "drawable", context.getPackageName());
            }
            int textKey = context.getResources().getIdentifier (category.key,"string",context.getPackageName());
            String text;
            if (textKey == 0) {
                text = category.key;
            } else {
                text = context.getResources().getString(textKey);
            }
            items.add(new Item(text, imageKey));
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

        if (v == null) {
            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = (Item) getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        v.setContentDescription(item.name);

        return v;
    }

    private class Item {
        final String name;
        final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}
