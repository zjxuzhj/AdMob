package com.zhj.admob.interfaceAd;

/**
 * 统一的广告状态监听接口
 */
public interface IADListener {
    void onAdLoaded();

    void onNoAD(String error);

    void onAdOpened();

    void onADExposure();

    void onAdClicked();

    void onAdLeftApplication();

    void onAdClosed();
}
