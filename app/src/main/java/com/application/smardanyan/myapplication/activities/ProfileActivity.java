package com.application.smardanyan.myapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.application.smardanyan.myapplication.R;
import com.application.smardanyan.myapplication.adapters.ListAdapter;
import com.application.smardanyan.myapplication.data.Data;
import com.application.smardanyan.myapplication.data.Master;


public class ProfileActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getActionBar().setDisplayShowTitleEnabled(false);

        int master_id = Integer.parseInt(getIntent().getExtras().getString("master_id"));

        Master item = null;
        for (Master master: Data.masters) {
            if (master_id == master.master_id) {
                item = master;
                break;
            }
        }

        if (!item.equals(null)) {

            ImageView imageView = (ImageView) findViewById(R.id.icon);
            imageView.setImageResource(R.drawable.person); //TODO need to check "avatar" value


            TextView full_nameTxt = (TextView) findViewById(R.id.full_name);
            full_nameTxt.setText(item.first_name + " " + item.last_name);

            TextView categoriesTxt = (TextView) findViewById(R.id.categories);
            categoriesTxt.setText(Master.getCategories(ProfileActivity.this,item.master_categories));

            TextView ageTxt = (TextView) findViewById(R.id.age);
            ageTxt.setText(Master.getAge(ProfileActivity.this,item.dob));

            TextView descriptionTxt = (TextView) findViewById(R.id.description);
            descriptionTxt.setText(item.description);

            TextView tagsTxt = (TextView) findViewById(R.id.tags);
            tagsTxt.setText(getResources().getString(R.string.tags) + ": " + item.master_tags);

            TextView typeTxt = (TextView) findViewById(R.id.type);
            typeTxt.setText(getResources().getString(R.string.type) + ": " + item.type);

            TextView phoneTxt = (TextView) findViewById(R.id.phone);
            phoneTxt.setText(getResources().getString(R.string.phone) + ": " + item.master_phones);

            TextView emailTxt = (TextView) findViewById(R.id.email);
            emailTxt.setText(getResources().getString(R.string.email) + ": " + item.email);

            TextView ratingTxt = (TextView) findViewById(R.id.rating);
            ratingTxt.setText(Float.toString(item.rating));

            RatingBar ratingVal = (RatingBar) findViewById(R.id.ratingBar);
            ratingVal.setRating(item.rating);
            LayerDrawable stars = (LayerDrawable) ratingVal.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.rgb(255, 165, 0), PorterDuff.Mode.SRC_ATOP);


        }
    }
}
