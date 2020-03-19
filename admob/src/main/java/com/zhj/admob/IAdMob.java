package com.zhj.admob;

import android.content.Intent;
import android.view.View;

import com.zhj.admob.interfaceAd.IInterstitialAd;
import com.zhj.admob.interfaceAd.IRewardVideoAd;

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
     */
    IInterstitialAd getInterstitialAd();

    /**
     * 获得激励视频广告
     */
    IRewardVideoAd getRewardVideoAd();

    /**
     * 获得开屏广告
     */
    void getSplashAD(Intent intent);

    /**
     * 获得原生广告
     */
    View getNativeAd(int type);
}
