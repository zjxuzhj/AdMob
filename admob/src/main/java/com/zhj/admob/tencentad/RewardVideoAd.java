package com.zhj.admob.tencentad;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.interfaceAd.IADListener;
import com.zhj.admob.interfaceAd.IRewardVideoAd;

/**
 * 腾讯激励广告类
 */
public class RewardVideoAd implements IRewardVideoAd {
    private RewardVideoAD _mRewardVideoAD;
    private String appId, adId;

    RewardVideoAd(Context context, String appId, String adId) {
        this.appId = appId;
        this.adId = adId;
    }

    @Override
    public void show() {
        if (!_mRewardVideoAD.hasShown()) { //广告展示检查2：当前广告数据还没有展示过
            long delta = 1000L; //建议给广告过期时间加个buffer，单位ms，这里demo采用1000ms的buffer
            //广告展示检查3：展示广告前判断广告数据未过期
            if (SystemClock.elapsedRealtime() < _mRewardVideoAD.getExpireTimestamp() - delta) {
                _mRewardVideoAD.showAD();
            } else {
                Log.i("admob", "激励视频广告已过期，请再次请求广告后进行广告展示！");
            }
        } else {
            Log.i("admob", "此条广告已经展示过，请再次请求广告后进行广告展示！");
        }
    }

    @Override
    public void showAsPopupWindow() {

    }

    @Override
    public void destroy() {
    }

    @Override
    public void addInterstitialADListener(Activity context, final IADListener unifiedInterstitialADListener) {
//        if (_mRewardVideoAD == null) {
//            _mRewardVideoAD = new UnifiedInterstitialAD(context, appId, adId, new UnifiedInterstitialADListener() {
//                @Override
//                public void onADReceive() {
//                    Log.i("admob", "onADReceive");
//                    unifiedInterstitialADListener.onAdLoaded();
//                }
//
//                @Override
//                public void onVideoCached() {
//
//                }
//
//                @Override
//                public void onNoAD(AdError adError) {
//                    Log.i("admob", "adError");
//                    unifiedInterstitialADListener.onAdLoaded();
//                }
//
//                @Override
//                public void onADOpened() {
//                    Log.i("admob", "onADOpened");
//                    unifiedInterstitialADListener.onAdOpened();
//                }
//
//                @Override
//                public void onADExposure() {
//                    Log.i("admob", "onADExposure");
//                    unifiedInterstitialADListener.onADExposure();
//                }
//
//                @Override
//                public void onADClicked() {
//                    Log.i("admob", "onADClicked");
//                    unifiedInterstitialADListener.onAdClicked();
//                }
//
//                @Override
//                public void onADLeftApplication() {
//                    Log.i("admob", "onADLeftApplication");
//                    unifiedInterstitialADListener.onAdLeftApplication();
//                }
//
//                @Override
//                public void onADClosed() {
//                    Log.i("admob", "onADClosed");
//                    unifiedInterstitialADListener.onAdClosed();
//                }
//            });
//            _mRewardVideoAD.loadAD();
//        }
    }
}
