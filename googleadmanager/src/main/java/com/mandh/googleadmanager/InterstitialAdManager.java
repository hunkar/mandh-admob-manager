package com.mandh.googleadmanager;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Date;

public class InterstitialAdManager {
    /**
     * Ad variable
     */
    public static InterstitialAd mInterstitialAd;

    /**
     * Condition variables
     */
    private static Date lastAdDate = new Date();
    private static Boolean waitingLoad = false;
    private static Boolean showWhenReady = false;

    /**
     * Set AdManager required variables
     * @param context Application context
     */
    public void initInterstitialAd(Context context) {
        AdManager.setmContext(context);
        setAfterInitConditions();
    }

    /**
     * Set last ad date for tracking timeouts between ad showings. First date set to 1 hour earlier.
     */
    public static void initInterstitialAd() {
        lastAdDate.setHours(lastAdDate.getHours() - 1);

        setAfterInitConditions();
    }

    /**
     * Making condition of ad loading. If it passes, interstitial ad will be loaded.
     */
    public static void setAfterInitConditions() {
        if (AdManager.getmContext() == null || AdManager.getInterstitialKey() == null || AdManager.getInterstitialKey().isEmpty()) {
            return;
        }

        loadAd();
    }

    /**
     * Interstitial ad loading.
     */
    public static void loadAd() {
        if (mInterstitialAd != null || waitingLoad) {
            return;
        }

        waitingLoad = true;

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(AdManager.getmContext(), AdManager.getInterstitialKey(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                waitingLoad = false;

                if (showWhenReady) {
                    showAd();
                }
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
                waitingLoad = false;
            }
        });
    }

    /**
     * Showing interstitial ad
     */
    public static void showAd() {
        if (mInterstitialAd == null || AdManager.getmActivity() == null) {
            setAfterInitConditions();
            return;
        }

        if (!AdManager.isAdsClosed() && AdManager.checkInternetConnection() && lastAdDate != null && ((new Date().getTime() - lastAdDate.getTime()) > AdManager.getInterstitialAdTimeout())) {

            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    mInterstitialAd = null;
                    showWhenReady = false;
                    lastAdDate = new Date();

                    loadAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    mInterstitialAd = null;
                    showWhenReady = false;
                    lastAdDate = new Date();

                    loadAd();
                }
            });

            mInterstitialAd.show(AdManager.getmActivity());
        }
    }


    /**
     * If it is true, ads will be shown when its load.
     */
    public static void showOnReady() {
        showWhenReady = true;
    }
}
