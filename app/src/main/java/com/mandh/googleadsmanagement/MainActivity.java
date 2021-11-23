package com.mandh.googleadsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mandh.googleadmanager.AdManager;
import com.mandh.googleadmanager.BannerView;
import com.mandh.googleadmanager.InterstitialAdManager;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAds();
    }

    private void setAds(){
        BannerView banner = findViewById(R.id.mandh_ads);

        AdManager.setAdManager(getApplicationContext(),
                getString(R.string.app_id),
                getString(R.string.banner_id),
                getString(R.string.interstitial_id));

        AdManager.setTestDeviceIds(Arrays.asList(getString(R.string.device_id)));

        AdManager.setActivity(MainActivity.this);
        AdManager.setInterstitialAdTimeout(2000);

        banner.show();

        InterstitialAdManager.initInterstitialAd();
        InterstitialAdManager.showOnReady();

        findViewById(R.id.show_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterstitialAdManager.showAd();
            }
        });
    }
}