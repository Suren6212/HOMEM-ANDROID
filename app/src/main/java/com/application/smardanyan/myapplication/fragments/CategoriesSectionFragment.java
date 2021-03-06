package com.application.smardanyan.myapplication.fragments;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.application.smardanyan.myapplication.R;
import com.application.smardanyan.myapplication.activities.CategoryActivity;
import com.application.smardanyan.myapplication.adapters.GridAdapter;


import com.application.smardanyan.myapplication.data.Data;


public class CategoriesSectionFragment extends Fragment {
	private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	rootView = inflater.inflate(R.layout.fragment_section_categories, container, false);

        GridView gridView = (GridView)rootView.findViewById(R.id.gridview);
        gridView.setAdapter(new GridAdapter(getActivity(), Data.categories, Data.masters));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(getActivity(), CategoryActivity.class);
                // Pass image index
                i.putExtra("category_id",v.getContentDescription());
                startActivity(i);
            }
        });

        

        return rootView;
    }
}
