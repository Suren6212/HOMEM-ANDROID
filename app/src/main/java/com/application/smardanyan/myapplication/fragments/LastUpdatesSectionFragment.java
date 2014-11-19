package com.application.smardanyan.myapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.application.smardanyan.myapplication.R;
import com.application.smardanyan.myapplication.activities.CategoryActivity;
import com.application.smardanyan.myapplication.activities.ProfileActivity;
import com.application.smardanyan.myapplication.adapters.ListAdapter;
import com.application.smardanyan.myapplication.data.Data;


public class LastUpdatesSectionFragment extends Fragment {
	private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	rootView = inflater.inflate(R.layout.fragment_section_lastupdates, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(new ListAdapter(getActivity(), Data.masters, 0));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                i.putExtra("master_id",v.getContentDescription());
                startActivity(i);
            }
        });

        return rootView;
    }
}
