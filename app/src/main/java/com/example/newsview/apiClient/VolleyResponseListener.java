package com.example.newsview.apiClient;

import androidx.lifecycle.LiveData;

import com.example.newsview.model.NewsItem;

import java.util.List;

public interface VolleyResponseListener {
    void onError (String message);
    void onResponse (LiveData<List<NewsItem>> list);
}
