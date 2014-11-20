package com.application.smardanyan.myapplication.adapters;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.application.smardanyan.myapplication.R;
import com.application.smardanyan.myapplication.data.Category;
import com.application.smardanyan.myapplication.data.Data;
import com.application.smardanyan.myapplication.data.Master;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private List<Item> items = new ArrayList<Item>();

    public ListAdapter(Activity context, List<Master> masters, int category_id) {
        super(context, R.layout.listview_item);
        this.context=context;

        String category_key="";
        for (Category category: Data.categories) {
            if (category.id == category_id) {
                category_key = category.key;
                break;
            }
        }

        for (Master master : masters) {
            if (category_id == 0 || master.master_categories.indexOf(category_key) != -1) {
                Item item = new Item(
                        master.master_id,
                        master.first_name + " " + master.last_name,
                        Master.getCategories(context, master.master_categories),
                        Master.getAge(master.dob),
                        Master.getShortDescription(master.description),
                        master.master_tags,
                        master.rating
                );
                items.add(item);
            }
        }

    }

    @Override
    public int getCount() {
        return items.size();
    }

    public View getView(int i,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_item, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.person); //TODO need to check "avatar" value

        TextView full_nameTxt = (TextView) rowView.findViewById(R.id.full_name);
        TextView categoriesTxt = (TextView) rowView.findViewById(R.id.categories);
        TextView ageTxt = (TextView) rowView.findViewById(R.id.age);
        TextView descriptionTxt = (TextView) rowView.findViewById(R.id.description);
        TextView tagsTxt = (TextView) rowView.findViewById(R.id.tags);
        TextView ratingTxt = (TextView) rowView.findViewById(R.id.rating);
        RatingBar ratingVal = (RatingBar) rowView.findViewById(R.id.ratingBar);

        Item item = items.get(i);

        full_nameTxt.setText(item.full_name);
        categoriesTxt.setText(item.categories);
        ageTxt.setText(item.age);
        descriptionTxt.setText(item.description);
        tagsTxt.setText(item.tags);
        ratingTxt.setText(Float.toString(item.rating));

        LayerDrawable stars = (LayerDrawable) ratingVal.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.rgb(255,165,0), PorterDuff.Mode.SRC_ATOP);
        ratingVal.setRating(item.rating);

        rowView.setContentDescription(Integer.toString(item.master_id));

        return rowView;

    };

    private class Item {
        final int master_id;
        final String full_name;
        final String categories;
        final String age;
        final String description;
        final String tags;
        final float rating;


        Item(int master_id, String full_name, String categories,String age,String description,String tags, float rating) {
            this.master_id = master_id;
            this.full_name = full_name;
            this.categories = categories;
            this.age = age;
            this.description = description;
            this.tags = tags;
            this.rating = rating;
        }
    }
}
