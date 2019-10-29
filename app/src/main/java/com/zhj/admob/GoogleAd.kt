package com.zhj.admob

import android.content.Context
import android.util.Log
import android.view.View
import com.google.android.gms.ads.*

class GoogleAd(private val context: Context) : IAdMob {

    override fun initAdSdk() {
        MobileAds.initialize(context) {}
    }

    override fun getInterstitialAd():InterstitialAd {
        val mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
       return mInterstitialAd
    }

    override fun getBannerView(): View {
        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        adView.loadAd(AdRequest.Builder().build())
        return adView
    }
}
