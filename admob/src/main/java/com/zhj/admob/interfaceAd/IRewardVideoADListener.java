package com.zhj.admob.interfaceAd;

import com.qq.e.comm.util.AdError;

/**
 * 激励视频广告状态监听接口
 */
public interface IRewardVideoADListener {

    //加载成功，可在此回调后进行广告展示 同谷歌 onRewardedVideoAdLoaded （）
    void onADLoad();

    //激励发放  同谷歌 onRewarded （RewardItem 奖励）
    void onReward();

    //被点击 同谷歌 onRewardedVideoAdLeftApplication（）
    void onADClick();

    //广告视频素材播放完毕 同谷歌 onRewardedVideoCompleted（）
    void onVideoComplete();

    //被关闭，同谷歌 onRewardedVideoAdClosed（）
    void onADClose();

    //广告流程出错，AdError中包含错误码和错误描述  同谷歌 onRewardedVideoAdFailedToLoad（int errorCode）
    void onError();
}
