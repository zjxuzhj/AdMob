package com.zhj.admob;

import android.content.Intent;
import android.view.View;

public interface IAdMob {
    /**
     * 加载必要资源
     * 初始化sdk
     */
    public void initAdSdk();

    /**
     * 获得banner的view
     * @return
     */
    public View getBannerView();

    /**
     * 获得一个
     * @return
     */
    public IInterstitialAd getIInterstitialAd();

    /**
     * 获得开屏广告
     */
    public void getSplashAD(Intent intent);
}
