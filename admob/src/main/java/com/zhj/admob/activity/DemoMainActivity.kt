package com.zhj.admob.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qq.e.comm.util.AdError
import com.zhj.admob.IAdMob
import com.zhj.admob.R
import com.zhj.admob.googlead.GoogleAd
import com.zhj.admob.interfaceAd.IADListener
import com.zhj.admob.interfaceAd.IRewardVideoADListener
import com.zhj.admob.tencentad.TencentAd
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 广告demo类，用于展示各种广告的调用方法
 */
class DemoMainActivity : AppCompatActivity() {

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

            override fun onError() {
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

            override fun onNoAD() {
                Toast.makeText(this@DemoMainActivity, "错误", Toast.LENGTH_SHORT).show()
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
     */
    private fun getAdMob(context: Context, activity: Activity): IAdMob {
        return if (isGoogleAd) {
            GoogleAd(context, activity)
        } else {
            TencentAd(context, activity)
        }
    }
}
