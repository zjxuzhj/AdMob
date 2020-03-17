package com.zhj.admob;

import android.content.Intent;
import android.view.View;

import com.zhj.admob.interfaceAd.IInterstitialAd;

/**
 * 广告顶层代理类实现接口
 */
public interface IAdMob {
    /**
     * 加载必要资源
     * 初始化sdk
     */
    void initAdSdk();

    /**
     * 获得banner的view
     */
    View getBannerView();

    /**
     * 获得插屏广告
     * @return
     */
    IInterstitialAd getIInterstitialAd();

    /**
     * 获得开屏广告
     */
    void getSplashAD(Intent intent);

    /**
     * 获得原生广告
     */
    View getNativeAd(int type);
}
