package com.zhj.admob.interfaceAd;

import android.app.Activity;

/**
 * 激励视频广告接口
 */
public interface IRewardVideoAd {
    /**
     * 展示激励广告
     */
    void show();

    /**
     * 添加广告监听
     */
    public void addInterstitialADListener(Activity context, IRewardVideoADListener rewardVideoADListener);

}
