package com.example.newsview.apiClient;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsview.model.NewsItem;

import java.util.List;

public class Repository {

    public interface RepositoryCallback {
        void onComplete(LiveData<List<NewsItem>> response);
        void onError (String message);
    }
    private static Repository instance;
    private Context context;
    private DataService dataService;
    private Repository (Context context) {
        this.context = context;
        dataService = new DataService(context);
    }
    public static  Repository getInstance(Context context){
        if (instance==null) {
            instance=new Repository(context);
        }
        return instance;
    }

    public void  getNewsDataFromThread (RepositoryCallback repositoryCallback) {
dataService.getNews(new VolleyResponseListener() {
    @Override
    public void onError(String message) {
        repositoryCallback.onError(message);
    }

    @Override
    public void onResponse(LiveData<List<NewsItem>> list) {
repositoryCallback.onComplete(list);
    }
});
        }
}
