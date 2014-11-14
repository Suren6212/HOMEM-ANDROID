package com.application.smardanyan.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
   private Context mContext;

   // Constructor
   public ImageAdapter(Context c) {
      mContext = c;
   }

   public int getCount() {
      return mThumbIds.length;
   }

   public Object getItem(int position) {
      return null;
   }

   public long getItemId(int position) {
      return 0;
   }

   // create a new ImageView for each item referenced by the Adapter
   public View getView(int position, View convertView, ViewGroup parent) {
      ImageView imageView;
      if (convertView == null) {
	      imageView = new ImageView(mContext);
	      imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
	      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	      imageView.setPadding(8, 8, 8, 8);
      } else {
    	  imageView = (ImageView) convertView;
      }

      imageView.setImageResource(mThumbIds[position]);
      return imageView;
   }

   // Keep all Images in array
   public Integer[] mThumbIds = {
      R.drawable.all,
      R.drawable.central_heating_fixing,
      R.drawable.electrician_service, 	
      R.drawable.foreman,
      R.drawable.household_fixing, 		
      R.drawable.labor_force,
      R.drawable.plumber_service
   };
}
