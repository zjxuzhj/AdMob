package com.zhj.admob.googlead

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.zhj.admob.IAdMob
import com.zhj.admob.interfaceAd.IInterstitialAd
import com.zhj.admob.interfaceAd.INativeAd.SMALL_NATIVE_AD
import com.zhj.admob.R
import com.zhj.admob.activity.SplashAdActivity
import com.zhj.admob.interfaceAd.AdConstants.Companion.GBannerID
import com.zhj.admob.interfaceAd.AdConstants.Companion.GInterstitialID
import com.zhj.admob.interfaceAd.AdConstants.Companion.GSplashPosID
import com.zhj.admob.interfaceAd.IRewardVideoAd

/**
 * 谷歌广告代理类
 */
class GoogleAd(private val context: Context, private val activity: Activity) : IAdMob {
    override fun getNativeAd(type: Int): View {
        val templateView: TemplateView = if (SMALL_NATIVE_AD == type) {
            TemplateView(activity, R.layout.gnt_small_template_view)
        } else {
            TemplateView(activity, R.layout.gnt_medium_template_view)
        }
        val adLoader = AdLoader.Builder(activity, GSplashPosID)
                .forUnifiedNativeAd { unifiedNativeAd ->
                    templateView.setNativeAd(unifiedNativeAd)
                }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(errorCode: Int) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build()
        adLoader.loadAd(AdRequest.Builder().build())
        return templateView
    }

    override fun getSplashAD(intent: Intent) {
        intent.setClass(activity, SplashAdActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    override fun getInterstitialAd(): IInterstitialAd {
        return InterstitialAd(context, GInterstitialID)
    }

    override fun getRewardVideoAd(): IRewardVideoAd {
        return RewardVideoAd(context)
    }

    override fun initAdSdk() {
        MobileAds.initialize(context) {}
    }

    override fun getBannerView(): View {
        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = GBannerID
        adView.loadAd(AdRequest.Builder().build())
        return adView
    }
}
