package com.zhj.admob.tencentad;

import android.app.Activity;
import android.util.Log;

import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.IInterstitialAd;

/**
 * Created by HongJay on 2019-10-30.
 */
public class InterstitialAd implements IInterstitialAd {
    private UnifiedInterstitialAD interstitialAD;
    public static final String APPID = "1106662554";

    InterstitialAd(Activity context) {
        String posId = "9030688981739111";
        if (interstitialAD == null) {
            interstitialAD = new UnifiedInterstitialAD(context, APPID, posId, new UnifiedInterstitialADListener() {
                @Override
                public void onADReceive() {
                    Log.i("aaaaaa","onADReceive");
                }

                @Override
                public void onNoAD(AdError adError) {
                    Log.i("aaaaaa","adError");
                }

                @Override
                public void onADOpened() {
                    Log.i("aaaaaa","onADOpened");
                }

                @Override
                public void onADExposure() {
                    Log.i("aaaaaa","onADExposure");
                }

                @Override
                public void onADClicked() {
                    Log.i("aaaaaa","onADClicked");
                }

                @Override
                public void onADLeftApplication() {
                    Log.i("aaaaaa","onADLeftApplication");
                }

                @Override
                public void onADClosed() {
                    Log.i("aaaaaa","onADClosed");
                }
            });
            interstitialAD.loadAD();
        }
    }

    public UnifiedInterstitialAD getInterstitialAD() {
        return interstitialAD;
    }

    public void setInterstitialAD(UnifiedInterstitialAD interstitialAD) {
        this.interstitialAD = interstitialAD;
    }

    @Override
    public void show() {
        interstitialAD.show();
    }
}
