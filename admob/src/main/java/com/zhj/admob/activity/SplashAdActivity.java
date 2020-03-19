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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;
import com.zhj.admob.R;
import com.zhj.admob.interfaceAd.AdConstants;

public class SplashAdActivity extends AppCompatActivity {
    private TextView skipView;
    private ImageView splashHolder;
    private static final String SKIP_TEXT = "点击跳过 %d";
    public boolean canJump = false;
    private String jumpClassName;
    private boolean isGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_splash_ad);

        boolean have_ad = getIntent().getBooleanExtra("have_ad", false);
        boolean isTest = getIntent().getBooleanExtra("isTest", false);
        isGoogle = getIntent().getBooleanExtra("isGoogle", false);
        int backgroundImageId = getIntent().getIntExtra("backgroundImageId", 0);
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

        //是否是谷歌
        if (isGoogle) {

        } else {
            splashHolder.setImageDrawable(getResources().getDrawable(backgroundImageId));
        }
        if (have_ad && !isTest) {
            fetchSplashAD(this, container, skipView, 4000);
        } else {
            jumpToNextAct(jumpClassName);
        }
    }

    private void jumpToNextAct(String jumpClassName) {
        if (TextUtils.isEmpty(jumpClassName)) {
            return;
        }
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
     * @param fetchDelay    拉取广告的超时时长：取值范围[3000, 5000]，设为0表示使用广点通SDK默认的超时时长。
     */
    private void fetchSplashAD(final Activity activity, final ViewGroup adContainer, View skipContainer, final int fetchDelay) {
        if (isGoogle) {
            AdLoader adLoader = new AdLoader.Builder(activity, AdConstants.Companion.getGSplashPosID())
                    .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                        @Override
                        public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                            TemplateView templateView = new TemplateView(activity, R.layout.gnt_medium_template_view);
                            templateView.setNativeAd(unifiedNativeAd);
                            adContainer.addView(templateView);
                            downTime(fetchDelay);
                        }
                    })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            Log.i("admob", String.format("LoadSplashADFail, eCode=%d, errorMsg=%s", errorCode,
                                    errorCode));
                            /** 如果加载广告失败，则直接跳转 */
                            jumpToNextAct(jumpClassName);
                        }

                        @Override
                        public void onAdClicked() {
                            Log.i("admob", "SplashADClicked");
                        }

                        @Override
                        public void onAdClosed() {
                            Log.i("admob", "SplashADDismissed");
                            next();
                        }

                        @Override
                        public void onAdLoaded() {
                            Log.i("admob", "SplashADPresent");
                            splashHolder.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
                        }

                        @Override
                        public void onAdOpened() {
                            Log.i("admob", "SplashADPresent");
                            splashHolder.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
                        }

                        @Override
                        public void onAdImpression() {
                            Log.i("admob", "onAdImpression");
                        }

                        @Override
                        public void onAdLeftApplication() {
                            Log.i("admob", "onAdLeftApplication");
                        }
                    })
                    .withNativeAdOptions(new NativeAdOptions.Builder()
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
        } else {
            SplashAD splashAD = new SplashAD(activity, skipContainer, AdConstants.Companion.getTAPPID(), AdConstants.Companion.getTSplashPosID(), new SplashADListener() {
                @Override
                public void onADDismissed() {
                    Log.i("admob", "SplashADDismissed");
                    next();
                }

                @Override
                public void onNoAD(AdError adError) {
                    Log.i("admob", String.format("LoadSplashADFail, eCode=%d, errorMsg=%s", adError.getErrorCode(),
                            adError.getErrorMsg()));
                    /** 如果加载广告失败，则直接跳转 */
                    jumpToNextAct(jumpClassName);
                }

                @Override
                public void onADPresent() {
                    Log.i("admob", "SplashADPresent");
                    splashHolder.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
                }

                @Override
                public void onADClicked() {
                    Log.i("admob", "SplashADClicked");
                }

                /**
                 * 倒计时回调，返回广告还将被展示的剩余时间。
                 * 通过这个接口，开发者可以自行决定是否显示倒计时提示，或者还剩几秒的时候显示倒计时
                 *
                 * @param millisUntilFinished 剩余毫秒数
                 */
                @Override
                public void onADTick(long millisUntilFinished) {
                    Log.i("admob", "SplashADTick " + millisUntilFinished + "ms");
                    skipView.setText(String.format(SKIP_TEXT, Math.round(millisUntilFinished / 1000f)));
                }

                @Override
                public void onADExposure() {
                    Log.i("admob", "onADExposure");
                }

                @Override
                public void onADLoaded(long l) {
                    Log.i("admob", "onADLoaded");
                }
            }, fetchDelay);
            splashAD.fetchAndShowIn(adContainer);

        }
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
