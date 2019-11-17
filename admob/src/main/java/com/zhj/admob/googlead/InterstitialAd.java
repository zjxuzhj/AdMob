package com.zhj.admob.googlead;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.zhj.admob.IADListener;
import com.zhj.admob.IInterstitialAd;

/**
 * Created by HongJay on 2019-10-30.
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
    public void addInterstitialADListener(Activity context, final IADListener unifiedInterstitialADListener) {
        interstitialAD = new com.google.android.gms.ads.InterstitialAd(context);
        interstitialAD.setAdUnitId(interstitialId);
        interstitialAD.loadAd(new AdRequest.Builder().build());
        interstitialAD.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("admob", "onADReceive");
                unifiedInterstitialADListener.onAdLoaded();
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.i("admob", "adError");
                unifiedInterstitialADListener.onNoAD(errorCode + "");
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                Log.i("admob", "onADOpened");
                unifiedInterstitialADListener.onAdOpened();
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                Log.i("admob", "onADClicked");
                unifiedInterstitialADListener.onAdClicked();
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                Log.i("admob", "onADLeftApplication");
                unifiedInterstitialADListener.onAdLeftApplication();
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                Log.i("admob", "onADClosed");
                unifiedInterstitialADListener.onAdClosed();
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }

}
