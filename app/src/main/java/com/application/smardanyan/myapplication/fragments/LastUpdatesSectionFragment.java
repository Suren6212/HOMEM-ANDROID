package com.application.smardanyan.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.application.smardanyan.myapplication.R;
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

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //TODO need to create new page
                Toast.makeText(getActivity(), v.getContentDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        */

        return rootView;
    }
}
