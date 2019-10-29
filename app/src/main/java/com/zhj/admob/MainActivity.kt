package com.zhj.admob

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val iAdMob = getAdMob(this)
//        iAdMob.initAdSdk()
//        fl_ad_view.addView(iAdMob.bannerView)
//        val interstitialAd = iAdMob.interstitialAd
//        interstitialAd.adListener = object : AdListener() {
//            override fun onAdClosed() {
//                interstitialAd.loadAd(AdRequest.Builder().build())
//            }
//        }
//        button.setOnClickListener {
//            if (interstitialAd.isLoaded) {
//                interstitialAd.show()
//            } else {
//                Log.d("TAG", "The interstitial wasn't loaded yet.")
//            }
//        }

        val iAdMob = TencentAd(this)
        fl_ad_view.addView(iAdMob.bannerView)
    }

    /**
     * 获取地图服务
     *
     * @param context
     * @return
     */
    private fun getAdMob(context: Context): IAdMob {
        return GoogleAd(context)
    }

}
