package com.zhj.admob.tencentad;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.interfaceAd.IRewardVideoADListener;
import com.zhj.admob.interfaceAd.IRewardVideoAd;

/**
 * 腾讯激励广告类
 */
public class RewardVideoAd implements IRewardVideoAd {
    private RewardVideoAD _mRewardVideoAD;
    private String appId, adId;

    public RewardVideoAd(Context context, String appId, String adId) {
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
    public void addInterstitialADListener(Activity context, final IRewardVideoADListener rewardVideoADListener) {
        if (_mRewardVideoAD == null) {
            _mRewardVideoAD = new RewardVideoAD(context, appId, adId, new RewardVideoADListener() {

                @Override
                public void onADLoad() {
                    rewardVideoADListener.onADLoad();
                    Log.i("admob", "onADLoad");
                }

                @Override
                public void onVideoCached() {
                    Log.i("admob", "onVideoCached");
                }

                @Override
                public void onADShow() {
                    Log.i("admob", "onADShow");
                }

                @Override
                public void onADExpose() {
                    Log.i("admob", "onADExpose");
                }

                @Override
                public void onReward() {
                    rewardVideoADListener.onReward();
                    Log.i("admob", "onReward");
                }

                @Override
                public void onADClick() {
                    rewardVideoADListener.onADClick();
                    Log.i("admob", "onADClick");
                }

                @Override
                public void onVideoComplete() {
                    rewardVideoADListener.onVideoComplete();
                    Log.i("admob", "onVideoComplete");
                }

                @Override
                public void onADClose() {
                    rewardVideoADListener.onADClose();
                    Log.i("admob", "onADClose");
                }

                @Override
                public void onError(AdError adError) {
                    rewardVideoADListener.onError();
                    Log.i("admob", String.format("onError, eCode=%d, errorMsg=%s", adError.getErrorCode(), adError.getErrorMsg()));
                }
            });
            _mRewardVideoAD.loadAD();
        }
    }
}
