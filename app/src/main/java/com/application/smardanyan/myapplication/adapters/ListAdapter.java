package com.application.smardanyan.myapplication.adapters;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        // TODO Auto-generated constructor stub

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
                        master.first_name + " " + master.last_name,
                        getCategories(master.master_categories),
                        getAge(master.dob),
                        getDescription(master.description),
                        "Tags: " + master.master_tags
                );
                items.add(item);
            }
        }

    }

    private String getDescription(String full_desc) {
        String descriptionStr[] = full_desc.split(" ");
        String short_desc = "";
        if (descriptionStr.length > 5) {
            for (int i = 0; i < 5; i++)
                short_desc += descriptionStr[i] + " ";
            short_desc += "...";
        } else {
            short_desc = full_desc;
        }
        return short_desc;
    }

    private String getAge(String birth_date) {
        String bdStr[] = birth_date.split("-");
        String age;
        if (bdStr.length > 0) {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int birthYear = Integer.parseInt(bdStr[0]);
            age = Integer.toString(currentYear - birthYear);
        } else {
            age = "-";
        }
        return "Age: " + age;
    }

    private String getCategories(String categories) {
        String categoriesStr[] = categories.split(",");
        String formattedTxt = "";
        if (categoriesStr.length == 0) {
            int textKey = context.getResources().getIdentifier (categories,"string",context.getPackageName());
            if (textKey == 0) {
                formattedTxt = categories;
            } else {
                formattedTxt = context.getResources().getString(textKey);
            }
        } else {
            for (int i=0; i<categoriesStr.length; i++) {
                int textKey = context.getResources().getIdentifier (categoriesStr[i],"string",context.getPackageName());
                if (textKey == 0) {
                    formattedTxt += categoriesStr[i];
                } else {
                    formattedTxt += context.getResources().getString(textKey);
                }
                if (i != categoriesStr.length-1) {
                    formattedTxt += ", ";
                }
            }

        }
        return formattedTxt;
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

        Item item = items.get(i);

        full_nameTxt.setText(item.full_name);
        categoriesTxt.setText(item.categories);
        ageTxt.setText(item.age);
        descriptionTxt.setText(item.description);
        tagsTxt.setText(item.tags);

        return rowView;

    };

    private class Item {
        final String full_name;
        final String categories;
        final String age;
        final String description;
        final String tags;

        Item(String full_name, String categories,String age,String description,String tags) {
            this.full_name = full_name;
            this.categories = categories;
            this.age = age;
            this.description = description;
            this.tags = tags;
        }
    }
}
