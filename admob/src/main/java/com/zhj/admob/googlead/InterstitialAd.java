package com.zhj.admob.googlead;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.interfaceAd.IADListener;
import com.zhj.admob.interfaceAd.IInterstitialAd;

/**
 * 插屏广告实现类
 */
public class InterstitialAd implements IInterstitialAd {
    private com.google.android.gms.ads.InterstitialAd interstitialAD;
    private String interstitialId;

    InterstitialAd(Context context, String interstitialId) {
        this.interstitialId = interstitialId;
    }

    @Override
    public void show() {
        if (interstitialAD.isLoaded()) {
            interstitialAD.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }

    @Override
    public void showAsPopupWindow() {
    }

    @Override
    public void destroy() {

    }

    @Override
    public void addInterstitialADListener(Activity context, final IADListener interstitialADListener) {
        interstitialAD = new com.google.android.gms.ads.InterstitialAd(context);
        interstitialAD.setAdUnitId(interstitialId);
        interstitialAD.loadAd(new AdRequest.Builder().build());
        interstitialAD.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("admob", "onAdLoaded");
                interstitialADListener.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.i("admob", "adError");
                interstitialADListener.onNoAD(new AdError(errorCode, "googleError"));
            }

            @Override
            public void onAdOpened() {
                Log.i("admob", "onADOpened");
                interstitialADListener.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                Log.i("admob", "onADClicked");
                interstitialADListener.onAdClicked();
            }

            @Override
            public void onAdLeftApplication() {
                Log.i("admob", "onADLeftApplication");
            }

            @Override
            public void onAdClosed() {
                Log.i("admob", "onADClosed");
                interstitialADListener.onAdClosed();
            }
        });
    }

}
