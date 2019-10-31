package com.zhj.admob.tencentad;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.IAdMob;
import com.zhj.admob.IInterstitialAd;

public class TencentAd implements IAdMob {
    private UnifiedBannerView bv;
    private String posId;
    private Activity context;
    private String appId, bannerId, interstitialId;

    public TencentAd(Activity context, String appId, String bannerId, String interstitialId) {
        this.context = context;
        this.appId = appId;
        this.bannerId = bannerId;
        this.interstitialId = interstitialId;
    }

    @Override
    public void initAdSdk() {

    }

    @Override
    public View getBannerView() {
        String posId = bannerId;
        if (this.bv != null && this.posId.equals(posId)) {
            return this.bv;
        }
        this.posId = posId;
        this.bv = new UnifiedBannerView(context, appId, posId, new UnifiedBannerADListener() {

            @Override
            public void onNoAD(AdError error) {
                Log.i(
                        "AD_DEMO",
                        String.format("Banner onNoAD，eCode = %d, eMsg = %s", error.getErrorCode(),
                                error.getErrorMsg()));
            }

            @Override
            public void onADReceive() {
                Log.i("aa", "onADReceive");
            }

            @Override
            public void onADExposure() {
                Log.i("aa", "onADExposure");
            }

            @Override
            public void onADClosed() {
                Log.i("aa", "onADClosed");
            }

            @Override
            public void onADClicked() {
                Log.i("aa", "onADClicked");
            }

            @Override
            public void onADLeftApplication() {
                Log.i("aa", "onADLeftApplication");
            }

            @Override
            public void onADOpenOverlay() {
                Log.i("aa", "onADOpenOverlay");
            }

            @Override
            public void onADCloseOverlay() {
                Log.i("aa", "onADCloseOverlay");
            }

        });
        bv.loadAD();
        return bv;
    }

    @Override
    public IInterstitialAd getIInterstitialAd() {
        return new InterstitialAd(context, appId, interstitialId);
    }

}
