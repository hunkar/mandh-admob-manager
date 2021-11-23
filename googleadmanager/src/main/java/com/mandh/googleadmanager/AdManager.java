package com.mandh.googleadmanager;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.List;

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
        if(AdManager.mContext == null)
            return true;

        ConnectivityManager cm = (ConnectivityManager) AdManager.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());

            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true;
                } else return false;
            } else {
                return false;
            }
        } else {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null) {
                return activeNetwork.isConnectedOrConnecting();
            } else {
                return false;
            }
        }
    }

    public static void setTestDeviceIds(List<String> testDeviceIds){
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);
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
