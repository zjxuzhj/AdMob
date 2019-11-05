package com.zhj.admob;

import com.qq.e.comm.util.AdError;

/**
 * 广告状态
 */
public interface IADListener {
    void onAdLoaded();

    void onNoAD(AdError var1);

    void onAdOpened();

    void onADExposure();

    void onAdClicked();

    void onAdLeftApplication();

    void onAdClosed();
}
