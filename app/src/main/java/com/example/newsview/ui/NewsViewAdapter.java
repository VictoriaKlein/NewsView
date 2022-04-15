package com.example.newsview.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsview.R;
import com.example.newsview.model.NewsItem;
import com.example.newsview.utils.DateFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.NewsItemViewHolder>{
    private List<NewsItem> newsItemList;
    OnClickListener OnClickListener;
    public NewsViewAdapter(List <NewsItem> newsItemList, OnClickListener OnClickListener) {
        this.newsItemList = newsItemList;
        this.OnClickListener = OnClickListener;
    }


    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.news_row;
        LayoutInflater inFlater = LayoutInflater.from(context);
        View view = inFlater.inflate(layoutId, parent,false);
        NewsItemViewHolder newsItemViewHolder = new NewsItemViewHolder(view);
        return newsItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
NewsItem newsItem = newsItemList.get(position);
holder.title.setText(newsItem.getTitle());
holder.desc.setText(newsItem.getDesc());
if (newsItem.getAuthor() == "null") {
    holder.author.setText(newsItem.getSource());
} else { holder.author.setText(newsItem.getAuthor());}

holder.date.setText(DateFormat.formattedDate(newsItem.getDate()));

 String  str = newsItem.getImgUrl().replaceAll("[\\\\]*", "");
    String alternativeStrUrl = "https://tinypng.com/images/example-shrunk.png";
        if (newsItem.getImgUrl()!="null"){
        Glide.with(holder.itemView.getContext()).load(str).into(holder.imageView);}
        else { Glide.with(holder.itemView.getContext()).load(alternativeStrUrl).into(holder.imageView);}
holder.source.setText("Category: " + newsItem.getCategory());

    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }
    public void setNewsItemList(List<NewsItem> newsItemList) {
        this.newsItemList = newsItemList;
        notifyDataSetChanged();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder{
    private TextView title;
private TextView author;
private TextView desc;
private TextView date;
private RelativeLayout row;
private OnClickListener onClickListener;
private ImageView imageView;
private TextView source;
    public NewsItemViewHolder(@NonNull View v) {
        super(v);
        this.onClickListener = OnClickListener;
        title = v.findViewById(R.id.title);
        desc = v.findViewById(R.id.news_text);
        author = v.findViewById(R.id.author);
        date = v.findViewById(R.id.date);
        imageView = v.findViewById(R.id.image);
        source = v.findViewById(R.id.source);
        row = v.findViewById(R.id.row);
      row.setOnClickListener(view -> {
          String newsItemUrl = newsItemList.get(getAbsoluteAdapterPosition()).getUrl();
if (OnClickListener != null && getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION) {
onClickListener.onClick(newsItemUrl);
notifyDataSetChanged();
}
      });
    }
}


}
