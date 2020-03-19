package com.zhj.admob.interfaceAd;

import android.app.Activity;

/**
 * 插屏广告接口
 */
public interface IInterstitialAd {
    /**
     * 展示插屏广告，有遮罩
     */
    void show();

    /**
     * 展示插屏广告，无遮罩
     */
    void showAsPopupWindow();

    /**
     * 广点通的插屏广告需要销毁
     */
    void destroy();

    /**
     * 添加广告监听
     */
    public void addInterstitialADListener(Activity context, IADListener unifiedInterstitialADListener);

}
