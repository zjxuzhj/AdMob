package com.zhj.admob.googlead;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.zhj.admob.IInterstitialAd;

/**
 * Created by HongJay on 2019-10-30.
 */
public class InterstitialAd implements IInterstitialAd {
    private com.google.android.gms.ads.InterstitialAd interstitialAD;

    InterstitialAd(Activity context) {

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
    public void addInterstitialADListener(Activity context, final InterstitialADListener unifiedInterstitialADListener) {
        interstitialAD = new com.google.android.gms.ads.InterstitialAd(context);
        interstitialAD.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAD.loadAd(new AdRequest.Builder().build());
        interstitialAD.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                unifiedInterstitialADListener.onAdLoaded();
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                unifiedInterstitialADListener.onAdClosed();
            }
        });
    }

}
