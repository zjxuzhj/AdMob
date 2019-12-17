package com.zhj.admob.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.R;

public class GSplashAdActivity extends AppCompatActivity {
    private TextView skipView;
    private ImageView splashHolder;
    private static final String SKIP_TEXT = "点击跳过 %d";
    public boolean canJump = false;
    private String jumpClassName;
    private boolean isGoogle;
    private String splashPosID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_splash_ad);

        boolean have_ad = getIntent().getBooleanExtra("have_ad", false);
        boolean isTest = getIntent().getBooleanExtra("isTest", false);
        isGoogle = getIntent().getBooleanExtra("isGoogle", false);
        int backgroundImageId = getIntent().getIntExtra("backgroundImageId", 0);
        String appID = getIntent().getStringExtra("appID");
        splashPosID = getIntent().getStringExtra("SplashPosID");
        jumpClassName = getIntent().getStringExtra("jumpClassName");
        String appName = getIntent().getStringExtra("appName");
        int appLogo = getIntent().getIntExtra("appLogo", 0);
        int watermark = getIntent().getIntExtra("watermark", 0);

        ViewGroup container = findViewById(R.id.anzhi_splash_container);
        ImageView iv_app_logo = findViewById(R.id.iv_app_logo);
        ImageView iv_water_mark = findViewById(R.id.iv_water_mark);
        iv_app_logo.setImageDrawable(getDrawable(appLogo));
        iv_water_mark.setImageDrawable(getDrawable(watermark));
        skipView = findViewById(R.id.anzhi_skip_view);
        splashHolder = findViewById(R.id.anzhi_splash_holder);
        if (!isGoogle) {
            splashHolder.setImageDrawable(getResources().getDrawable(backgroundImageId));
        } else {

        }
        if (have_ad && !isTest) {
            if (!TextUtils.isEmpty(appID) && !TextUtils.isEmpty(splashPosID)) {
                fetchSplashAD(this, container, skipView, appID, splashPosID, 4000);
            } else {
                jumpToNextAct(jumpClassName);
            }
        } else {
            jumpToNextAct(jumpClassName);
        }
    }

    private void jumpToNextAct(String jumpClassName) {
        Intent intent = new Intent();
        intent.setClassName(this, jumpClassName);
        boolean isApplication = getIntent().getBooleanExtra("isApplication", false);
        intent.putExtra("isApplication", isApplication);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("1activity", "onStart: ");
    }

    /**
     * 拉取开屏广告，开屏广告的构造方法有3种，详细说明请参考开发者文档。
     *
     * @param activity      展示广告的activity
     * @param adContainer   展示广告的大容器
     * @param skipContainer 自定义的跳过按钮：传入该view给SDK后，SDK会自动给它绑定点击跳过事件。SkipView的样式可以由开发者自由定制，其尺寸限制请参考activity_splash.xml或者接入文档中的说明。
     * @param appId         应用ID
     * @param posId         广告位ID
     * @param fetchDelay    拉取广告的超时时长：取值范围[3000, 5000]，设为0表示使用广点通SDK默认的超时时长。
     */
    private void fetchSplashAD(final Activity activity, final ViewGroup adContainer, View skipContainer,
                               String appId, String posId, int fetchDelay) {
        AdLoader adLoader = new AdLoader.Builder(activity, posId)
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        TemplateView templateView = new TemplateView(activity, R.layout.gnt_medium_template_view);
                        templateView.setNativeAd(unifiedNativeAd);
                        adContainer.addView(templateView);
                        downTime(4000);
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Handle the failure by logging, altering the UI, and so on.
                        Log.i(
                                "AD_DEMO",
                                String.format("LoadSplashADFail, eCode=%d, errorMsg=%s", errorCode,
                                        errorCode));
                        /** 如果加载广告失败，则直接跳转 */
                        jumpToNextAct(jumpClassName);
                    }

                    @Override
                    public void onAdClicked() {
                        Log.i("AD_DEMO", "SplashADClicked");
                    }

                    @Override
                    public void onAdClosed() {
                        Log.i("AD_DEMO", "SplashADDismissed");
                        next();
                    }

                    @Override
                    public void onAdLoaded() {
                        Log.i("AD_DEMO", "SplashADPresent");
                        splashHolder.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
                    }

                    @Override
                    public void onAdOpened() {
                        Log.i("AD_DEMO", "SplashADPresent");
                        splashHolder.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
                    }

                    @Override
                    public void onAdImpression() {

                    }

                    @Override
                    public void onAdLeftApplication() {

                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
        skipContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerCancel();
                jumpToNextAct(jumpClassName);
            }
        });
    }

    private CountDownTimer timer;

    //倒计时3秒，结束跳转
    public void downTime(long down) {
        timer = new CountDownTimer(down, 1000L) {
            @Override
            public void onTick(long l) {
                long second = Math.round(l / 1000d);
                now_timer = l;
                is_loading = true;
                skipView.setText("跳过 " + second);
            }

            @Override
            public void onFinish() {
                is_loading = false;
                now_timer = 0;
                jumpToNextAct(jumpClassName);
            }
        }.start();
    }

    public long now_timer = 0;
    public boolean is_loading = false;

    public void timerCancel() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            now_timer = 0;
            is_loading = false;
        }
    }

    /**
     * Populates a {@link UnifiedNativeAdView} object with data from a given
     * {@link UnifiedNativeAd}.
     *
     * @param nativeAd the object containing the ad's assets
     * @param adView   the view to be populated
     */
    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        // Set the media view.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

//        // Updates the UI to say whether or not this ad has a video asset.
//        if (vc.hasVideoContent()) {
//            videoStatus.setText(String.format(Locale.getDefault(),
//                    "Video status: Ad contains a %.2f:1 video asset.",
//                    vc.getAspectRatio()));
//
//            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
//            // VideoController will call methods on this object when events occur in the video
//            // lifecycle.
//            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
//                @Override
//                public void onVideoEnd() {
//                    // Publishers should allow native ads to complete video playback before
//                    // refreshing or replacing them with another ad in the same UI location.
//                    refresh.setEnabled(true);
//                    videoStatus.setText("Video status: Video playback has ended.");
//                    super.onVideoEnd();
//                }
//            });
//        } else {
//            videoStatus.setText("Video status: Ad does not contain a video asset.");
//            refresh.setEnabled(true);
//        }
    }

    /**
     * 设置一个变量来控制当前开屏页面是否可以跳转，当开屏广告为普链类广告时，点击会打开一个广告落地页，此时开发者还不能打开自己的App主页。当从广告落地页返回以后，
     * 才可以跳转到开发者自己的App主页；当开屏广告是App类广告时只会下载App。
     */
    private void next() {
        if (canJump) {
            jumpToNextAct(jumpClassName);
        } else {
            canJump = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("1activity", "onResume: ");
        if (canJump) {
            next();
        }
        canJump = true;
    }

    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
