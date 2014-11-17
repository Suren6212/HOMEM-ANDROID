package com.application.smardanyan.myapplication.api;

import java.util.List;

import android.util.Log;

import com.application.smardanyan.myapplication.data.Category;

import retrofit.RestAdapter;
import retrofit.http.GET;

public class Api {
    private static final String API_URL = "http://172.24.20.188:3000/api/v1";

    public static List<Category> Categories;

    public static boolean isConnected;

    public interface CategoryInt {
        @GET("/categories")
        List<Category> Categories();
    }

    public static RestAdapter getRestAdapter () {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_URL)
                .build();
        return restAdapter;
    }

    public static void getCategories() {
        try{
            CategoryInt categoryInt = getRestAdapter().create(CategoryInt.class);
            Categories = categoryInt.Categories();
            isConnected = true;
        }
        catch(Exception e)
        {
            isConnected = false;
            Log.d("!!!!!!!!!!!!!!!","Exception: " + e);
        }

    }
}