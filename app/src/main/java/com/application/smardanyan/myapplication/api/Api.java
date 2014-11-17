package com.application.smardanyan.myapplication.api;

import java.util.List;

import android.util.Log;

import com.application.smardanyan.myapplication.data.Category;
import com.application.smardanyan.myapplication.data.Data;
import com.application.smardanyan.myapplication.data.Master;

import retrofit.RestAdapter;
import retrofit.http.GET;

public class Api {
    private static final String API_URL = "http://172.24.20.188:3000/api/v1";

    public static boolean isConnected;

    public interface CategoryInt {
        @GET("/categories")
        List<Category> Categories();
    }

    public interface MasterInt {
        @GET("/masters")
        List<Master> Masters();
    }

    public static void getData() {
        try{
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(API_URL)
                    .build();

            CategoryInt categoryInt = restAdapter.create(CategoryInt.class);
            Data.categories = categoryInt.Categories();

            MasterInt masterInt = restAdapter.create(MasterInt.class);
            Data.masters = masterInt.Masters();

            /*
            for (Master master : Data.masters) {
                Log.d("!!!!!!!!!!!!!!!","Master: " + master.email);
            }
            */

            isConnected = true;
        }
        catch(Exception e)
        {
            isConnected = false;
            Log.d("!!!!!!!!!!!!!!!","Exception: " + e);
        }

    }
}