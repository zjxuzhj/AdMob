package com.zhj.admob.googlead

import android.app.Activity
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.zhj.admob.IAdMob
import com.zhj.admob.IInterstitialAd

class GoogleAd(private val context: Activity, appId: String, private val bannerId: String, private val interstitialId: String) : IAdMob {
    override fun getIInterstitialAd(): IInterstitialAd {
        return InterstitialAd(context,interstitialId)
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
