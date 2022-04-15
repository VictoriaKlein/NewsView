package com.example.newsview.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsview.apiClient.Repository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
  private  Repository repository;
    private MutableLiveData <List<NewsItem>> newsList;
    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsList = new MutableLiveData<>();
        repository = Repository.getInstance(application);
    }
    public interface ViewModelCallback {
        void onComplete(LiveData<List<NewsItem>> response);
        void onError (String message);
    }

    public void getNewsList (ViewModelCallback viewModelCallback) {
       repository.getNewsDataFromThread(new Repository.RepositoryCallback() {
           @Override
           public void onComplete(LiveData<List<NewsItem>> response) {
               viewModelCallback.onComplete(response);
           }

           @Override
           public void onError(String message) {
viewModelCallback.onError(message);
           }
       });
    }
}
