package com.zhj.admob;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.InterstitialAd;
import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.comm.util.AdError;

public class TencentAd implements IAdMob {
    public static final String BannerID2 = "6030189801366430";
    public static final String APPID = "1106662554";
    private UnifiedBannerView bv;
    private String posId;
    private Activity context;

    TencentAd(Activity context) {
        this.context = context;
    }

    @Override
    public void initAdSdk() {

    }

    @Override
    public View getBannerView() {
        String posId = BannerID2;
        if (this.bv != null && this.posId.equals(posId)) {
            return this.bv;
        }
        this.posId = posId;
        this.bv = new UnifiedBannerView(context, APPID, posId, new UnifiedBannerADListener() {

            @Override
            public void onNoAD(AdError error) {
                Log.i(
                        "AD_DEMO",
                        String.format("Banner onNoAD，eCode = %d, eMsg = %s", error.getErrorCode(),
                                error.getErrorMsg()));
            }

            @Override
            public void onADReceive() {
                    Log.i("aa","onADReceive");
            }

            @Override
            public void onADExposure() {
                Log.i("aa","onADExposure");
            }

            @Override
            public void onADClosed() {
                Log.i("aa","onADClosed");
            }

            @Override
            public void onADClicked() {
                Log.i("aa","onADClicked");
            }

            @Override
            public void onADLeftApplication() {
                Log.i("aa","onADLeftApplication");
            }

            @Override
            public void onADOpenOverlay() {
                Log.i("aa","onADOpenOverlay");
            }

            @Override
            public void onADCloseOverlay() {
                Log.i("aa","onADCloseOverlay");
            }

        });
        bv.loadAD();
        return bv;
    }

    @Override
    public InterstitialAd getInterstitialAd() {
        return null;
    }
}
