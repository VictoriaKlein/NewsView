package com.example.newsview.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.newsview.R;
import com.example.newsview.apiClient.DataService;
import com.example.newsview.apiClient.Repository;
import com.example.newsview.apiClient.VolleyResponseListener;
import com.example.newsview.model.NewsItem;
import com.example.newsview.model.NewsViewModel;
import com.example.newsview.utils.DateFormat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView newsRecyclerView;
    NewsViewModel newsViewModel;
    NewsViewAdapter newsViewAdapter;
    NewsDescFragment newsDescFragment;
    ImageView backToMainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        newsRecyclerView = findViewById(R.id.news_recyclerView);
        backToMainPage = findViewById(R.id.back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        newsRecyclerView.setLayoutManager(linearLayoutManager);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        settingAdapter();
        //returns to the beginning
backToMainPage.setOnClickListener(view -> settingAdapter());
    }
public void settingAdapter () {
    newsViewModel.getNewsList(new NewsViewModel.ViewModelCallback() {
        @Override
        public void onComplete(LiveData<List<NewsItem>> response) {
            newsViewAdapter = new NewsViewAdapter(response.getValue(), url -> {
                openFragment(url);
            });
            newsRecyclerView.setAdapter(newsViewAdapter);
        }
        @Override
        public void onError(String message) { Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show(); }
    });
}
public void openFragment(String url){
        //needs validation
    newsDescFragment = new NewsDescFragment(url);
    newsDescFragment.show(getSupportFragmentManager(),newsDescFragment.getTag());
}

}