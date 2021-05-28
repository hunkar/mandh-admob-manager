package com.mandh.googleadmanager;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Date;

public class AdManager {
    /**
     * Application and ad variable
     */
    private static Context mContext;
    private static Activity mActivity;

    /**
     * Keys. Defaults are example keys.
     */
    private static String interstitialKey = "ca-app-pub-7542048154009592/1737557341";
    private static String bannerKey = "ca-app-pub-7542048154009592/5676802359";
    private static String appAdKey = "not_init";

    /**
     * Interstitial ads options.
     */
    private static boolean adsClosed = false;
    private static long interstitialAdTimeout = 30000;

    /**
     * Set active activity when change activities.
     * @param activity
     */
    public static void setActivity(Activity activity) {
        AdManager.mActivity = activity;
    }

    /**
     * Set AdManager required variables
     * @param context Application context
     * @param appAdKey Application id from Admob
     * @param bannerKey Banner ad key from Admob
     * @param interstitialKey Interstitial ad key from Admob
     */
    public static void setAdManager(Context context, String appAdKey, String bannerKey, String interstitialKey) {
        AdManager.mContext = context;
        AdManager.appAdKey = appAdKey;
        AdManager.bannerKey = bannerKey;
        AdManager.interstitialKey = interstitialKey;
    }

    /**
     * Method which is checking internet connection
     * @return
     */
    public static boolean checkInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) AdManager.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Context getmContext() {  return mContext; }

    public static void setmContext(Context mContext) {
        AdManager.mContext = mContext;
    }

    public static String getInterstitialKey() {
        return interstitialKey;
    }

    public static void setInterstitialKey(String interstitialKey) {
        AdManager.interstitialKey = interstitialKey;
    }

    public static String getBannerKey() {
        return bannerKey;
    }

    public static void setBannerKey(String bannerKey) {
        AdManager.bannerKey = bannerKey;
    }

    public static String getAppAdKey() {
        return appAdKey;
    }

    public static void setAppAdKey(String appAdKey) {
        AdManager.appAdKey = appAdKey;
    }

    public static boolean isAdsClosed() {
        return adsClosed;
    }

    public static void setAdsClosed(boolean adsClosed) {
        AdManager.adsClosed = adsClosed;
    }

    public static long getInterstitialAdTimeout() {
        return interstitialAdTimeout;
    }

    public static void setInterstitialAdTimeout(long interstitialAdTimeout) {
        AdManager.interstitialAdTimeout = interstitialAdTimeout;
    }

    public static Activity getmActivity() {
        return mActivity;
    }

    public static void setmActivity(Activity mActivity) {
        AdManager.mActivity = mActivity;
    }
}
