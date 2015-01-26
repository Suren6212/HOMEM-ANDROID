package com.application.smardanyan.myapplication.data;

import android.app.Activity;
import android.util.Log;

import com.application.smardanyan.myapplication.R;

import java.util.Calendar;

/**
 * Created by smardanyan on 17.11.2014.
 */
public class Master {
    public int id;
    public int master_id;
    public String email;
    public String first_name;
    public String last_name;
    public String dob;
    public String type;
    public int city_id;
    public int country_id;
    public int completion_percentage;
    public String description;
    public String status;
    public float rating;
    public String avatar;  // ???
    public String city_key;
    public String country_key;
    public String master_categories;
    public String master_category_ids;
    public String master_tags;
    public String master_phones;


    public static String getShortDescription(String full_desc) {
        String descriptionStr[] = full_desc.split(" ");
        String short_desc = "";
        if (descriptionStr.length > 5) {
            for (int i = 0; i < 5; i++)
                short_desc += descriptionStr[i] + " ";
            short_desc += "...";
        } else {
            short_desc = full_desc;
        }
        return short_desc;
    }

    public static String getAge(String birth_date) {
        if (birth_date != null) {
            String bdStr[] = birth_date.split("-");
            String age;
            if (bdStr.length > 0) {
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                int birthYear = Integer.parseInt(bdStr[0]);
                age = Integer.toString(currentYear - birthYear);
            } else {
                age = "-";
            }
            return age;
        }
        return "-";
    }

    public static String getCategories(Activity context, String categories) {
        String categoriesStr[] = categories.split(",");
        String formattedTxt = "";
        if (categoriesStr.length == 0) {
            int textKey = context.getResources().getIdentifier (categories,"string",context.getPackageName());
            if (textKey == 0) {
                formattedTxt = categories;
            } else {
                formattedTxt = context.getResources().getString(textKey);
            }
        } else {
            for (int i=0; i<categoriesStr.length; i++) {
                int textKey = context.getResources().getIdentifier (categoriesStr[i],"string",context.getPackageName());
                if (textKey == 0) {
                    formattedTxt += categoriesStr[i];
                } else {
                    formattedTxt += context.getResources().getString(textKey);
                }
                if (i != categoriesStr.length-1) {
                    formattedTxt += ", ";
                }
            }
        }
        return formattedTxt;
    }

    public static String getType(Activity context, String type) {
        String formattedTxt = "";
        int textKey = context.getResources().getIdentifier (type,"string",context.getPackageName());
        if (textKey == 0) {
            formattedTxt = type;
        } else {
            formattedTxt = context.getResources().getString(textKey);
        }
        return formattedTxt;
    }
}
