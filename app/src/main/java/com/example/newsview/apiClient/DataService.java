package com.example.newsview.apiClient;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.newsview.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    Context context;
    NewsItem newsItem;
    private MutableLiveData<List<NewsItem>> currencyLiveData;
    private List<NewsItem> newsItemList = new ArrayList<>();
    private static final String usedUrl =
            "http://api.mediastack.com/v1/news?access_key=ff01d963da5313b5252f17fa41e42b8e&languages=en,-de";
    public DataService(Context context) {
        this.context = context;
        this.currencyLiveData = new MutableLiveData<>();

    }
    public void getNews (VolleyResponseListener volleyResponseListener) {
        final StringRequest request = new StringRequest(Request.Method.GET,
                usedUrl, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject newsObj = jsonArray.getJSONObject(i);
                            String title = newsObj.getString("title");
                            String desc = newsObj.getString("description");
                            String author = newsObj.getString("author");
                            String date = newsObj.getString("published_at");
                            String source = newsObj.getString("source");
                            String url = newsObj.getString("url");
                            String imgUrl = newsObj.getString("image");
                            String category = newsObj.getString("category");
                            newsItem = new NewsItem(title,desc,author,date,source,url,imgUrl,category);
                            newsItemList.add(newsItem);
                        }
                        currencyLiveData.setValue(newsItemList);
                        volleyResponseListener.onResponse(currencyLiveData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> volleyResponseListener.onError("smth wrong"));
        VolleySingleton.getInstance(context).addToReqQueue(request);
    }
}
