package com.zhj.admob.googlead

import android.app.Activity
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.zhj.admob.IAdMob
import com.zhj.admob.IInterstitialAd

class GoogleAd(private val context: Activity) : IAdMob {
    override fun getIInterstitialAd(): IInterstitialAd {
        return InterstitialAd(context)
    }

    override fun initAdSdk() {
        MobileAds.initialize(context) {}
    }

    override fun getBannerView(): View {
        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        adView.loadAd(AdRequest.Builder().build())
        return adView
    }
}
