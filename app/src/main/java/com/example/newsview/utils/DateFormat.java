package com.example.newsview.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat  {
    public static String formattedDate (String inputString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = "";
        try {
            Date date = sdf.parse(inputString);
            formattedTime = output.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        SimpleDateFormat myFormat = new SimpleDateFormat("MMM, dd  yyyy");
//String reformattedStr = "";
//        try {
//           reformattedStr = myFormat.format(fromUser.parse(inputString));
//            Log.d("wow", "formatedDate: " + reformattedStr);
//            return reformattedStr;
//        } catch (
//                ParseException e) {
//            e.printStackTrace();
//        }
//
//        return reformattedStr;
        return formattedTime;
    }

}
