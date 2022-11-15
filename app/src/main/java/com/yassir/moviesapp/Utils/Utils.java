package com.yassir.moviesapp.Utils;

import com.yassir.moviesapp.R;

public class Utils {
    public static String minimizeString(String text){
        return text.substring(0,32);
    }
    public static String getUrlDetails(long id){
        return "https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US";
    }
}
