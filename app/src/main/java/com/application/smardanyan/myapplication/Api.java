package com.application.smardanyan.myapplication;

import java.util.List;

import android.util.Log;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

public class Api {
    private static final String API_URL = "http://172.24.20.188:3000/api/v1";

    public static List<Category> Categories;

    public static boolean isConnected;

    public static class Category {
        int id;
        String key;
        String status;
    }

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
            /*
                for (Category category : Categories) {
                    Log.d("!!!!!!!!!!!!!!!!!!", ": " + category.id + " (" + category.key + ")");
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