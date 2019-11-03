package com.zhj.admob.googlead

import android.app.Activity
import android.content.Intent
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.zhj.admob.IAdMob
import com.zhj.admob.IInterstitialAd
import com.zhj.admob.activity.TSplashAdActivity

class GoogleAd(private val context: Activity, appId: String, private val bannerId: String,
               private val interstitialId: String, private val splashPosId: String) : IAdMob {
    override fun getNativeAd(): UnifiedNativeAdView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSplashAD(intent: Intent) {
        intent.setClass(context, TSplashAdActivity::class.java)
        intent.putExtra("appID", "saaaa")
        intent.putExtra("SplashPosID", splashPosId)
        intent.putExtra("jumpClassName", "com.zhj.admob.activity.TSplashAdActivity")
        context.startActivity(intent)
        context.finish()
    }

    override fun getIInterstitialAd(): IInterstitialAd {
        return InterstitialAd(context, interstitialId)
    }

    override fun initAdSdk() {
        MobileAds.initialize(context) {}
    }

    override fun getBannerView(): View {
        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = bannerId
        adView.loadAd(AdRequest.Builder().build())
        return adView
    }
}
