package com.zhj.admob.googlead;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.zhj.admob.IInterstitialAd;

/**
 * Created by HongJay on 2019-10-30.
 */
public class InterstitialAd implements IInterstitialAd {
    private com.google.android.gms.ads.InterstitialAd interstitialAD;
    public static final String APPID = "1106662554";

    InterstitialAd(Activity context) {
        com.google.android.gms.ads.InterstitialAd mInterstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public com.google.android.gms.ads.InterstitialAd getInterstitialAD() {
        return interstitialAD;
    }

    public void setInterstitialAD(com.google.android.gms.ads.InterstitialAd interstitialAD) {
        this.interstitialAD = interstitialAD;
    }

    @Override
    public void show() {
        if (interstitialAD.isLoaded()) {
            interstitialAD.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}
