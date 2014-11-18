package com.application.smardanyan.myapplication.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.application.smardanyan.myapplication.R;
import com.application.smardanyan.myapplication.api.Api;
import com.application.smardanyan.myapplication.fragments.CategoriesSectionFragment;
import com.application.smardanyan.myapplication.fragments.LastUpdatesSectionFragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;
    ViewPager mViewPager;

    public class Backend extends AsyncTask<MainActivity,Integer,String> {
        @Override
        protected String doInBackground(MainActivity... params) {
            Api.getData();
            return "done";
        }
        @Override
        protected void onPostExecute(String k)
        {
            if (Api.isConnected) {
                setContentView(R.layout.activity_main);
                // Create the adapter that will return a fragment for each of the three primary sections
                // of the app.
                mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

                // Set up the action bar.
                final ActionBar actionBar = getActionBar();

                // Specify that the Home/Up button should not be enabled, since there is no hierarchical
                // parent.
                actionBar.setHomeButtonEnabled(false);

                // Specify that we will be displaying tabs in the action bar.
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

                // Set up the ViewPager, attaching the adapter and setting up a listener for when the
                // user swipes between sections.
                mViewPager = (ViewPager) findViewById(R.id.pager);
                mViewPager.setAdapter(mAppSectionsPagerAdapter);
                mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between different app sections, select the corresponding tab.
                        // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                        // Tab.
                        actionBar.setSelectedNavigationItem(position);
                    }
                });

                // For each of the sections in the app, add a tab to the action bar.
                for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
                    // Create a tab with text corresponding to the page title defined by the adapter.
                    // Also specify this Activity object, which implements the TabListener interface, as the
                    // listener for when this tab is selected.
                    actionBar.addTab(
                            actionBar.newTab()
                                    .setText( getResources().getString(getResources().getIdentifier((String)mAppSectionsPagerAdapter.getPageTitle(i),"string",getPackageName())) )
                                    .setTabListener(MainActivity.this));
                }
            } else {
                setContentView(R.layout.unable_to_connect);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Backend().execute();
        getActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new CategoriesSectionFragment();
                case 1:
                    return new LastUpdatesSectionFragment();

                default:
                    // The other sections of the app are dummy placeholders.
                    Fragment fragment = new DummySectionFragment();
                    Bundle args = new Bundle();
                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment.setArguments(args);
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "categories";
                case 1:
                    return "last_updates";
                default:
                    return "undefined";
            }
        }

    }

    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_dummy, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
