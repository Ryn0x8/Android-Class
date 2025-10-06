package com.saral.examapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ExamAdapter adapter;
    ArrayList<Exam> examList;
    FrameLayout adContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        examList = new ArrayList<>();
        examList.add(new Exam("Math", "01-10-2025"));
        examList.add(new Exam("Physics", "03-10-2025"));
        examList.add(new Exam("Chemistry", "05-10-2025"));
        examList.add(new Exam("Biology", "07-10-2025"));
        examList.add(new Exam("English", "09-10-2025"));

        adapter = new ExamAdapter(examList);
        recyclerView.setAdapter(adapter);
        MobileAds.initialize(this, initializationStatus -> {});
        adContainerView = findViewById(R.id.ad_view_container);
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, 360));

        adContainerView.removeAllViews();
        adContainerView.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
}
