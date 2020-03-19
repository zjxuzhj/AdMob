package com.zhj.admob.googlead;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.interfaceAd.IRewardVideoADListener;
import com.zhj.admob.interfaceAd.IRewardVideoAd;

import static com.zhj.admob.interfaceAd.AdConstants.GRewardVideoID;

/**
 * 谷歌激励广告类
 */
public class RewardVideoAd implements IRewardVideoAd {
    private RewardedVideoAd _mRewardVideoAD;

    public RewardVideoAd(Context context) {
        _mRewardVideoAD = MobileAds.getRewardedVideoAdInstance(context);
    }

    @Override
    public void show() {
        if (_mRewardVideoAD.isLoaded()) {
            _mRewardVideoAD.show();
        }
    }

    @Override
    public void addInterstitialADListener(Activity context, final IRewardVideoADListener rewardADListener) {
        _mRewardVideoAD.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                rewardADListener.onADLoad();
                Log.i("admob", "onADLoad");
            }

            @Override
            public void onRewardedVideoAdOpened() {
                Log.i("admob", "onRewardedVideoAdOpened");
            }

            @Override
            public void onRewardedVideoStarted() {
                Log.i("admob", "onRewardedVideoStarted");
            }

            @Override
            public void onRewardedVideoAdClosed() {
                rewardADListener.onADClose();
                Log.i("admob", "onRewardedVideoAdClosed");
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                rewardADListener.onReward();
                Log.i("admob", "onRewarded");
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                rewardADListener.onADClick();
                Log.i("admob", "onRewardedVideoAdLeftApplication");
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                AdError googleError = new AdError(i, "googleError");
                rewardADListener.onError(googleError);
                Log.i("admob", "onRewardedVideoAdFailedToLoad:" + googleError);
            }

            @Override
            public void onRewardedVideoCompleted() {
                rewardADListener.onVideoComplete();
                Log.i("admob", "onRewardedVideoAdFailedToLoad");
            }
        });
        _mRewardVideoAD.loadAd(GRewardVideoID, new AdRequest.Builder().build());
    }
}
