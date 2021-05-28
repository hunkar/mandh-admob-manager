package com.mandh.googleadmanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerView extends RelativeLayout {
    RelativeLayout bannerContainerView;
    Boolean show;
    Context context;

    /**
     * Constructor method.
     * @param context application context
     * @param attrs element attributes
     */
    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        setAttrs(attrs);
        initView();
    }

    /**
     * This method parse custom attributes
     *
     * @param attrs AttributeSet of view
     */
    private void setAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MandhAdManagement,
                0, 0);

        try {
            show = typedArray.getBoolean(R.styleable.MandhAdManagement_show, false);
        } finally {
            typedArray.recycle();
        }

    }

    /**
     * This method prepare view and definitions.
     */
    private void initView() {
        inflate(getContext(), R.layout.ad_layout, this);

        setDefinitions();
    }

    /**
     * This method set definitons views from layout.
     */
    private void setDefinitions() {
        bannerContainerView = findViewById(R.id.banner_container);
    }

    /**
     * Show Banner view
     */
    public void show(){
        if(AdManager.isAdsClosed() || !AdManager.checkInternetConnection()){
            return;
        }

        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(AdManager.getBannerKey());

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        bannerContainerView.addView(adView, params);
    }
}