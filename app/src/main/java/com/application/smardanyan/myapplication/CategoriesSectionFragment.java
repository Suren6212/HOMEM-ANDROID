package com.application.smardanyan.myapplication;



import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;



public class CategoriesSectionFragment extends Fragment {
	private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {   	  	
    	
    	rootView = inflater.inflate(R.layout.fragment_section_categories, container, false);
        
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview); 
        gridview.setAdapter(new ImageAdapter(getActivity())); 
        

        return rootView;
    }
}
