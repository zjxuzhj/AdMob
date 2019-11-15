package com.zhj.admob;

/**
 * 广告状态
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
