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
import com.zhj.admob.interfaceAd.AdConstants.GAPPID
import com.zhj.admob.interfaceAd.AdConstants.GBannerID
import com.zhj.admob.interfaceAd.AdConstants.GInterstitialID
import com.zhj.admob.interfaceAd.AdConstants.GNativeID
import com.zhj.admob.interfaceAd.AdConstants.GSplashPosID
import com.zhj.admob.interfaceAd.AdConstants.TAPPID
import com.zhj.admob.interfaceAd.AdConstants.TBannerID
import com.zhj.admob.interfaceAd.AdConstants.TInterstitialID
import com.zhj.admob.interfaceAd.AdConstants.TNativeID
import com.zhj.admob.interfaceAd.AdConstants.TRewardVideID
import com.zhj.admob.interfaceAd.AdConstants.TSplashPosID
import com.zhj.admob.interfaceAd.IRewardVideoADListener
import com.zhj.admob.tencentad.RewardVideoAd
import com.zhj.admob.tencentad.TencentAd
import kotlinx.android.synthetic.main.activity_main.*


class DemoMainActivity : AppCompatActivity() {
    private var nativeAd: UnifiedNativeAd? = null
    private val ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"
    private var adLoaded = false
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*********初始化广告模块********/
        val iAdMob = getAdMob(this, this)
        iAdMob.initAdSdk()

        /*********激励视频 腾讯********/
        val rewardVideoAD = RewardVideoAd(this, TAPPID, TRewardVideID) // 有声播放
        rewardVideoAD.addInterstitialADListener(this, object : IRewardVideoADListener {
            override fun onADClick() {
            }

            override fun onReward() {
            }

            override fun onADClose() {
            }

            override fun onADLoad() {
                adLoaded = true
                val msg = "load ad success ! expireTime = "
                Toast.makeText(this@DemoMainActivity, msg, Toast.LENGTH_LONG).show()
            }

            override fun onVideoComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(var1: AdError?) {
                val msg = var1!!.errorMsg
                Toast.makeText(this@DemoMainActivity, msg, Toast.LENGTH_LONG).show()
            }
        })
        rewardVideoAD.show()

        /*********激励视频 谷歌********/
        val rewardVideoAd = com.zhj.admob.googlead.RewardVideoAd(this)
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
            GoogleAd(context, activity, GAPPID, GBannerID, GInterstitialID, GSplashPosID, GNativeID)
        } else {
            TencentAd(context, activity, TAPPID, TBannerID, TInterstitialID, TSplashPosID, TNativeID)
        }
    }
}
