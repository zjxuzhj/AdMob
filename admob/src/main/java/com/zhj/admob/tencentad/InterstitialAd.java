package com.zhj.admob.tencentad;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.IADListener;
import com.zhj.admob.IInterstitialAd;

/**
 * Created by HongJay on 2019-10-30.
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
    public void addInterstitialADListener(Activity context, final IADListener unifiedInterstitialADListener) {
        if (interstitialAD == null) {
            interstitialAD = new UnifiedInterstitialAD(context, appId, interstitialId, new UnifiedInterstitialADListener() {
                @Override
                public void onADReceive() {
                    Log.i("admob", "onADReceive");
                    unifiedInterstitialADListener.onAdLoaded();
                }

                @Override
                public void onNoAD(AdError adError) {
                    Log.i("admob", "adError");
                    unifiedInterstitialADListener.onAdLoaded();
                }

                @Override
                public void onADOpened() {
                    Log.i("admob", "onADOpened");
                    unifiedInterstitialADListener.onAdOpened();
                }

                @Override
                public void onADExposure() {
                    Log.i("admob", "onADExposure");
                    unifiedInterstitialADListener.onADExposure();
                }

                @Override
                public void onADClicked() {
                    Log.i("admob", "onADClicked");
                    unifiedInterstitialADListener.onAdClicked();
                }

                @Override
                public void onADLeftApplication() {
                    Log.i("admob", "onADLeftApplication");
                    unifiedInterstitialADListener.onAdLeftApplication();
                }

                @Override
                public void onADClosed() {
                    Log.i("admob", "onADClosed");
                    unifiedInterstitialADListener.onAdClosed();
                }
            });
            interstitialAD.loadAD();
        }
    }
}
