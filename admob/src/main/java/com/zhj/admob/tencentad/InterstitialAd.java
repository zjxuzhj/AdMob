package com.zhj.admob.tencentad;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.interfaceAd.IADListener;
import com.zhj.admob.interfaceAd.IInterstitialAd;

/**
 * 腾讯插屏广告类
 */
public class InterstitialAd implements IInterstitialAd {
    private UnifiedInterstitialAD interstitialAD;
    private String appId, interstitialId;

    InterstitialAd(Context context, String appId, String interstitialId) {
        this.appId = appId;
        this.interstitialId = interstitialId;
    }

    @Override
    public void show() {
        interstitialAD.show();
    }

    @Override
    public void showAsPopupWindow() {

    }

    @Override
    public void destroy() {
        interstitialAD.destroy();
    }

    @Override
    public void addInterstitialADListener(Activity context, final IADListener interstitialADListener) {
        if (interstitialAD == null) {
            interstitialAD = new UnifiedInterstitialAD(context, appId, interstitialId, new UnifiedInterstitialADListener() {
                @Override
                public void onADReceive() {
                    Log.i("admob", "onADReceive");
                    interstitialADListener.onAdLoaded();
                }

                @Override
                public void onVideoCached() {

                }

                @Override
                public void onNoAD(AdError adError) {
                    Log.i("admob", String.format("adError, eCode=%d, errorMsg=%s", adError.getErrorCode(), adError.getErrorMsg()));
                    interstitialADListener.onNoAD();
                }

                @Override
                public void onADOpened() {
                    Log.i("admob", "onADOpened");
                    interstitialADListener.onAdOpened();
                }

                @Override
                public void onADExposure() {
                    Log.i("admob", "onADExposure");
                }

                @Override
                public void onADClicked() {
                    Log.i("admob", "onADClicked");
                    interstitialADListener.onAdClicked();
                }

                @Override
                public void onADLeftApplication() {
                    Log.i("admob", "onADLeftApplication");
                }

                @Override
                public void onADClosed() {
                    Log.i("admob", "onADClosed");
                    interstitialADListener.onAdClosed();
                }
            });
            interstitialAD.loadAD();
        }
    }
}
