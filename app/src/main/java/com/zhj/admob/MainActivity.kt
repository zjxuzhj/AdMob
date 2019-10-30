package com.zhj.admob

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qq.e.comm.util.AdError
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
        val iAdMob = GoogleAd(this)
        val iInterstitialAd = iAdMob.iInterstitialAd
        iInterstitialAd.addInterstitialADListener(this, object : IInterstitialAd.InterstitialADListener {
            override fun onNoAD(var1: AdError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAdOpened() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onADExposure() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAdClicked() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAdLeftApplication() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAdClosed() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAdLoaded() {
                Toast.makeText(this@MainActivity, "成功", Toast.LENGTH_SHORT).show()
            }

        })
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
