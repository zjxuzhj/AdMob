package com.zhj.admob;

import android.view.View;

import com.google.android.gms.ads.InterstitialAd;

public interface IAdMob {
    /**
     * 加载必要资源
     * 初始化sdk
     */
    public void initAdSdk();

    public View getBannerView();

    public InterstitialAd getInterstitialAd();
}
