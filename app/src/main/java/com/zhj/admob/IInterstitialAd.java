package com.zhj.admob;

import android.app.Activity;

/**
 * Created by HongJay on 2019-10-30.
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
     * 添加广告监听
     */
    public void addInterstitialADListener(Activity context, IADListener unifiedInterstitialADListener);

}
