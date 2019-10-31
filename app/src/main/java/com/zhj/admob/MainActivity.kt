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
    val APPID = "1106662554"
    val TBannerID = "6030189801366430"
    val TInterstitialID = "9030688981739111"
    val GBannerID = "ca-app-pub-3940256099942544/6300978111"
    val GInterstitialID = "ca-app-pub-3940256099942544/1033173712"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iAdMob = getAdMob(this)
        iAdMob.initAdSdk()
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
                Toast.makeText(this@MainActivity, "关闭", Toast.LENGTH_SHORT).show()
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

    //是否使用谷歌广告
    private var isGoogleAd = false

    /**
     * 获取广告服务
     *
     * @param context
     * @return
     */
    private fun getAdMob(context: Activity): IAdMob {
        return if (isGoogleAd) {
            GoogleAd(context, APPID, GBannerID, GInterstitialID)
        } else {
            TencentAd(context, APPID, TBannerID, TInterstitialID)
        }
    }
}
