package com.zhj.admob.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.qq.e.comm.util.AdError
import com.zhj.admob.IAdMob
import com.zhj.admob.R
import com.zhj.admob.googlead.GoogleAd
import com.zhj.admob.interfaceAd.IADListener
import com.zhj.admob.interfaceAd.IRewardVideoADListener
import com.zhj.admob.tencentad.TencentAd
import kotlinx.android.synthetic.main.activity_main.*


class DemoMainActivity : AppCompatActivity() {
    private var nativeAd: UnifiedNativeAd? = null
    private val ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /********* 初始化广告模块 ********/
        val iAdMob = getAdMob(this, this)
        iAdMob.initAdSdk()

        /********* 激励视频 ********/
        val rewardVideoAd = iAdMob.rewardVideoAd
        rewardVideoAd.addInterstitialADListener(this, object : IRewardVideoADListener {
            override fun onADClick() {
            }

            override fun onReward() {
            }

            override fun onADClose() {
            }

            override fun onADLoad() {
            }

            override fun onVideoComplete() {
            }

            override fun onError(var1: AdError?) {
            }
        })
        rewardVideoAd.show()

        /********* 插屏广告 ********/
        val iInterstitialAd = iAdMob.interstitialAd
        iInterstitialAd.addInterstitialADListener(this, object : IADListener {

            override fun onAdClosed() {
                Toast.makeText(this@DemoMainActivity, "关闭", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClicked() {
            }

            override fun onNoAD(error: AdError?) {
                Toast.makeText(this@DemoMainActivity, error!!.errorMsg, Toast.LENGTH_SHORT).show()
            }

            override fun onAdLoaded() {
                Toast.makeText(this@DemoMainActivity, "成功", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
            }
        })
        iInterstitialAd.show()

        /********* 开屏广告 ********/
        jumpToAD(iAdMob)

        /********* banner广告 ********/
        fl_ad_view.addView(iAdMob.bannerView)
    }

    //跳转到开屏广告页面
    fun jumpToAD(iAdMob: IAdMob) {
        val intent = Intent()
        intent.putExtra("have_ad", true)
        intent.putExtra("isTest", false)
        intent.putExtra("backgroundImageId", R.drawable.background_circle)
        intent.putExtra("appName", getString(R.string.app_name))
        intent.putExtra("appLogo", R.mipmap.ic_launcher)
        intent.putExtra("watermark", R.mipmap.ic_launcher)
        intent.putExtra("isGoogle", isGoogleAd)
        iAdMob.getSplashAD(intent)
    }

    //是否使用谷歌广告
    private var isGoogleAd = true

    /**
     * 获取广告服务
     *
     * @param context
     * @return
     */
    private fun getAdMob(context: Context, activity: Activity): IAdMob {
        return if (isGoogleAd) {
            GoogleAd(context, activity)
        } else {
            TencentAd(context, activity)
        }
    }
}
