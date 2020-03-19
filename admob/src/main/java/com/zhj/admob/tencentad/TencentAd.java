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
import com.zhj.admob.activity.SplashAdActivity;
import com.zhj.admob.interfaceAd.IInterstitialAd;
import com.zhj.admob.interfaceAd.IRewardVideoAd;

import java.util.ArrayList;
import java.util.List;

import static com.zhj.admob.interfaceAd.AdConstants.TAPPID;
import static com.zhj.admob.interfaceAd.AdConstants.TBannerID;
import static com.zhj.admob.interfaceAd.AdConstants.TInterstitialID;
import static com.zhj.admob.interfaceAd.AdConstants.TNativeID;
import static com.zhj.admob.interfaceAd.AdConstants.TRewardVideID;

/**
 * 腾讯广告代理类
 */
public class TencentAd implements IAdMob {
    private UnifiedBannerView bv;
    private String posId;
    private Activity activity;
    private Context context;

    public TencentAd(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void initAdSdk() {

    }

    @Override
    public View getBannerView() {
        String posId = TBannerID;
        if (this.bv != null && this.posId.equals(posId)) {
            return this.bv;
        }
        this.posId = posId;
        this.bv = new UnifiedBannerView(activity, TAPPID, posId, new UnifiedBannerADListener() {

            @Override
            public void onNoAD(AdError error) {
                Log.i("admob", String.format("Banner onNoAD，eCode = %d, eMsg = %s", error.getErrorCode(),
                        error.getErrorMsg()));
            }

            @Override
            public void onADReceive() {
                Log.i("admob", "onADReceive");
            }

            @Override
            public void onADExposure() {
                Log.i("admob", "onADExposure");
            }

            @Override
            public void onADClosed() {
                Log.i("admob", "onADClosed");
            }

            @Override
            public void onADClicked() {
                Log.i("admob", "onADClicked");
            }

            @Override
            public void onADLeftApplication() {
                Log.i("admob", "onADLeftApplication");
            }

            @Override
            public void onADOpenOverlay() {
                Log.i("admob", "onADOpenOverlay");
            }

            @Override
            public void onADCloseOverlay() {
                Log.i("admob", "onADCloseOverlay");
            }

        });
        bv.loadAD();
        return bv;
    }

    @Override
    public IInterstitialAd getInterstitialAd() {
        return new InterstitialAd(context, TAPPID, TInterstitialID);
    }

    @Override
    public IRewardVideoAd getRewardVideoAd() {
        return new RewardVideoAd(context, TAPPID, TRewardVideID); // 有声播放;
    }

    @Override
    public void getSplashAD(Intent intent) {
        intent.setClass(context, SplashAdActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public View getNativeAd(int type) {
        final FrameLayout view = new FrameLayout(context);
        ADSize adSize = new ADSize(ADSize.FULL_WIDTH, 230); // 消息流中用AUTO_HEIGHT
        NativeExpressAD mADManager = new NativeExpressAD(context, adSize, TAPPID, TNativeID, new NativeExpressAD.NativeExpressADListener() {
            @Override
            public void onADLoaded(List<NativeExpressADView> list) {
                NativeExpressADView nativeExpressADView = list.get(0);
                nativeExpressADView.render();
                view.addView(nativeExpressADView);
            }

            @Override
            public void onRenderFail(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onRenderFail");
            }

            @Override
            public void onRenderSuccess(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onRenderSuccess");
            }

            @Override
            public void onADExposure(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onADExposure");
            }

            @Override
            public void onADClicked(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onADClicked");
            }

            @Override
            public void onADClosed(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onADClosed");
            }

            @Override
            public void onADLeftApplication(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onADLeftApplication");
            }

            @Override
            public void onADOpenOverlay(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onADOpenOverlay");
            }

            @Override
            public void onADCloseOverlay(NativeExpressADView nativeExpressADView) {
                Log.i("admob", "onADCloseOverlay");
            }

            @Override
            public void onNoAD(AdError adError) {
                Log.i("admob", "onNoAD");
            }
        });
        mADManager.setDownAPPConfirmPolicy(DownAPPConfirmPolicy.Default);
        mADManager.loadAD(AD_COUNT);
        return view;
    }

    protected List<NativeExpressADView> mAdViewList = new ArrayList<>();
    public static final int AD_COUNT = 1;    // 加载广告的条数，取值范围为[1, 10]
}
