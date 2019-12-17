package com.zhj.admob.tencentad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.IAdMob;
import com.zhj.admob.IInterstitialAd;
import com.zhj.admob.activity.TXSplashAdActivity;

import java.util.ArrayList;
import java.util.List;

public class TencentAd implements IAdMob {
    private UnifiedBannerView bv;
    private String posId;
    private Activity activity;
    private Context context;
    private String appId, bannerId, interstitialId, splashPosId, nativeId;

    public TencentAd(Context context, Activity activity, String appId, String bannerId, String interstitialId, String splashPosId, String nativeId) {
        this.context = context;
        this.activity = activity;
        this.appId = appId;
        this.bannerId = bannerId;
        this.interstitialId = interstitialId;
        this.splashPosId = splashPosId;
        this.nativeId = nativeId;
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
        this.bv = new UnifiedBannerView(activity, appId, posId, new UnifiedBannerADListener() {

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

    @Override
    public void getSplashAD(Intent intent) {
        intent.setClass(context, TXSplashAdActivity.class);
        intent.putExtra("appID", appId);
        intent.putExtra("SplashPosID", splashPosId);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public View getNativeAd(int type) {
        final FrameLayout view = new FrameLayout(context);
        ADSize adSize = new ADSize(ADSize.FULL_WIDTH, 230); // 消息流中用AUTO_HEIGHT
        NativeExpressAD mADManager = new NativeExpressAD(context, adSize, appId, nativeId, new NativeExpressAD.NativeExpressADListener() {
            @Override
            public void onADLoaded(List<NativeExpressADView> list) {
                NativeExpressADView nativeExpressADView = list.get(0);
                nativeExpressADView.render();
                view.addView(nativeExpressADView);
            }

            @Override
            public void onRenderFail(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onRenderSuccess(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onADExposure(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onADClicked(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onADClosed(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onADLeftApplication(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onADOpenOverlay(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onADCloseOverlay(NativeExpressADView nativeExpressADView) {

            }

            @Override
            public void onNoAD(AdError adError) {

            }
        });
        mADManager.setDownAPPConfirmPolicy(DownAPPConfirmPolicy.Default);
        mADManager.loadAD(AD_COUNT);
        return view;
    }

    protected List<NativeExpressADView> mAdViewList = new ArrayList<>();
    public static final int AD_COUNT = 1;    // 加载广告的条数，取值范围为[1, 10]
}
