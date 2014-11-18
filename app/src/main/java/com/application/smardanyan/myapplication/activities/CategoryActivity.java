package com.application.smardanyan.myapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.application.smardanyan.myapplication.R;
import com.application.smardanyan.myapplication.adapters.ListAdapter;
import com.application.smardanyan.myapplication.data.Data;


public class CategoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getActionBar().setDisplayShowTitleEnabled(false);

        Intent i = getIntent();
        int category_id = Integer.parseInt(i.getExtras().getString("category_id"));

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ListAdapter(this, Data.masters,category_id));
    }
}
