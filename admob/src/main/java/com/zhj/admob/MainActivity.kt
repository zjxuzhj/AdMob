package com.zhj.admob

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.zhj.admob.INativeAd.MEDIUM_NATIVE_AD
import com.zhj.admob.INativeAd.SMALL_NATIVE_AD
import com.zhj.admob.googlead.GoogleAd
import com.zhj.admob.tencentad.TencentAd
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAPPID = "1106662554"
    val TBannerID = "6030189801366430"
    val TInterstitialID = "9030688981739111"
    val TNativeID = "5050420952467625"
    val TSplashPosID = "1030527979166616"
    val GSplashPosID = "ca-app-pub-3940256099942544/2247696110"
    val GNativeID = "ca-app-pub-3940256099942544/2247696110"
    val GAPPID = "ca-app-pub-8931961554971597~7543877514"
    val GBannerID = "ca-app-pub-8931961554971597/2063501880"
    val GInterstitialID = "ca-app-pub-8931961554971597/8854758051"
    private var nativeAd: UnifiedNativeAd? = null
    private val ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iAdMob = getAdMob(this)
        iAdMob.initAdSdk()
        fl_ad_view.addView(iAdMob.getNativeAd(MEDIUM_NATIVE_AD))
//        val iInterstitialAd = iAdMob.iInterstitialAd
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

//        button.setOnClickListener {
//            iInterstitialAd.show()
//        }
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
        intent.putExtra("jumpClassName", "com.zhj.admob.activity.SplashAdActivity")
        iAdMob.getSplashAD(intent)
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
            GoogleAd(context, GAPPID, GBannerID, GInterstitialID, GSplashPosID, GNativeID)
        } else {
            TencentAd(context, TAPPID, TBannerID, TInterstitialID, TSplashPosID, TNativeID)
        }
    }
}
