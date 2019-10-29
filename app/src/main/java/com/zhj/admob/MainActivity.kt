package com.zhj.admob

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhj.admob.googlead.GoogleAd
import com.zhj.admob.tencentad.TencentAd
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
        val iAdMob = TencentAd(this)
        val iInterstitialAd = iAdMob.iInterstitialAd
        button.setOnClickListener {
            iInterstitialAd.show()
        }


//        fl_ad_view.addView(iAdMob.bannerView)
    }

    /**
     * 获取地图服务
     *
     * @param context
     * @return
     */
    private fun getAdMob(context: Activity): IAdMob {
        return GoogleAd(context)
    }

}
