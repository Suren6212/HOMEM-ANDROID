package com.application.smardanyan.myapplication.data;

import android.app.Activity;

import java.util.Calendar;
import java.util.List;

/**
 * Created by smardanyan on 17.11.2014.
 */
public class Data {
    public static List<Category> categories;
    public static List<Master> masters;

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
        return "Age: " + age;
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
}
