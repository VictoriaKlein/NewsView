package com.example.newsview.ui;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.newsview.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class NewsDescFragment extends BottomSheetDialogFragment {
String url;
    public NewsDescFragment(String url) {
this.url = url;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_desc, container, false);
        final WebView web = (WebView) view.findViewById(R.id.news_url_content);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   TextView newsTextView = (TextView) view.findViewById(R.id.news_url_content);
     //   newsTextView.setMovementMethod(LinkMovementMethod.getInstance());
        String url = "http://www.stackoverflow.com";
 // wModel = ViewModelProviders.of(getActivity()).get(TaskChangesViewModel.class);
        }

    }
