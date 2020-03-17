package com.zhj.admob.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.qq.e.ads.rewardvideo.RewardVideoAD
import com.qq.e.ads.rewardvideo.RewardVideoADListener
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
import com.zhj.admob.tencentad.TencentAd
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var nativeAd: UnifiedNativeAd? = null
    private val ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"
    private var adLoaded = false
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iAdMob = getAdMob(this, this)
        iAdMob.initAdSdk()
//        fl_ad_view.addView(iAdMob.getNativeAd(MEDIUM_NATIVE_AD))
        val iInterstitialAd = iAdMob.iInterstitialAd
//        iInterstitialAd.addInterstitialADListener(this, object : IADListener {
//            override fun onNoAD(var1: AdError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onAdOpened() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onADExposure() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onAdClicked() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onAdLeftApplication() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onAdClosed() {
//                Toast.makeText(this@MainActivity, "关闭", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onAdLoaded() {
//                Toast.makeText(this@MainActivity, "成功", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//        jumpToAD(iAdMob)
        val rewardVideoAD = RewardVideoAD(this, TAPPID, TRewardVideID, object : RewardVideoADListener {
            override fun onADExpose() {
            }

            override fun onADClick() {
            }

            override fun onVideoCached() {
            }

            /**
             * 激励视频触发激励（观看视频大于一定时长或者视频播放完毕）
             */
            override fun onReward() {
            }

            override fun onADClose() {
            }

            override fun onADLoad() {
                adLoaded = true
                val msg = "load ad success ! expireTime = "
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
            }

            override fun onVideoComplete() {
            }

            override fun onError(p0: AdError?) {
                val msg = p0!!.errorMsg
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
            }

            override fun onADShow() {
            }

        }) // 有声播放

//        rewardVideoAD.loadAD()
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = object : RewardedVideoAdListener {
            override fun onRewardedVideoAdClosed() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoAdLeftApplication() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoAdLoaded() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoAdOpened() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoCompleted() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewarded(p0: com.google.android.gms.ads.reward.RewardItem?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoStarted() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoAdFailedToLoad(p0: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                AdRequest.Builder().build())
//        refreshAd()
//        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
//                .forUnifiedNativeAd(fun(unifiedNativeAd: UnifiedNativeAd) {
//                    val styles = NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(getResources().getColor(R.color.gnt_white))).build()
//
//                    val template = my_template
//                    template.setStyles(styles)
//                    template.setNativeAd(unifiedNativeAd)
//                })
//                .build()
//
//        adLoader.loadAd(AdRequest.Builder().build())

        button.setOnClickListener {
            //            if (adLoaded) {//广告展示检查1：广告成功加载，此处也可以使用videoCached来实现视频预加载完成后再展示激励视频广告的逻辑
//                if (!rewardVideoAD.hasShown()) { //广告展示检查2：当前广告数据还没有展示过
//                    val delta: Long = 1000 //建议给广告过期时间加个buffer，单位ms，这里demo采用1000ms的buffer
//                    //广告展示检查3：展示广告前判断广告数据未过期
//                    if (SystemClock.elapsedRealtime() < rewardVideoAD.getExpireTimestamp() - delta) {
//                        rewardVideoAD.showAD()
//                    } else {
//                        Toast.makeText(this@MainActivity, "激励视频广告已过期，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show()
//                    }
//                } else {
//                    Toast.makeText(this@MainActivity, "此条广告已经展示过，请再次请求广告后进行广告展示！", Toast.LENGTH_LONG).show()
//                }
//            }
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
        }
//        jumpToAD()
//        fl_ad_view.addView(iAdMob.bannerView)
    }

    //跳转到开屏广告页面
    private fun jumpToAD(iAdMob: IAdMob) {
        val intent = Intent()
        intent.putExtra("have_ad", true)
        intent.putExtra("isTest", false)
        intent.putExtra("backgroundImageId", R.drawable.background_circle)
        intent.putExtra("appName", getString(R.string.app_name))
        intent.putExtra("appLogo", R.mipmap.ic_launcher)
        intent.putExtra("watermark", R.mipmap.ic_launcher)
        intent.putExtra("isGoogle", isGoogleAd)
        intent.putExtra("jumpClassName", "com.zhj.admob.activity.GSplashAdActivity")
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
            GoogleAd(context, activity, GAPPID, GBannerID, GInterstitialID, GSplashPosID, GNativeID)
        } else {
            TencentAd(context, activity, TAPPID, TBannerID, TInterstitialID, TSplashPosID, TNativeID)
        }
    }
}