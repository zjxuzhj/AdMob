package com.zhj.admob

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.qq.e.comm.util.AdError
import com.zhj.admob.googlead.GoogleAd
import com.zhj.admob.tencentad.TencentAd
import kotlinx.android.synthetic.main.native_ad_layout.*
import java.util.*


class MainActivity : AppCompatActivity() {
    val TAPPID = "1106662554"
    val TBannerID = "6030189801366430"
    val TInterstitialID = "9030688981739111"
    val TNativeID = "9030688981739111"
    val TSplashPosID = "1030527979166616"
    val GNativeID = "ca-app-pub-3940256099942544/2247696110"
    val GAPPID = "ca-app-pub-3940256099942544~3347511713"
    val GBannerID = "ca-app-pub-3940256099942544/6300978111"
    val GInterstitialID = "ca-app-pub-3940256099942544/1033173712"
    private var nativeAd: UnifiedNativeAd? = null
    private val ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.native_ad_layout)

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
        val intent = Intent()
        intent.putExtra("have_ad", true)
        intent.putExtra("isTest", false)
        intent.putExtra("backgroundImageId", R.drawable.background_circle)
        intent.putExtra("appName", getString(R.string.app_name))
        intent.putExtra("appLogo", R.mipmap.ic_launcher)
        intent.putExtra("watermark", R.mipmap.ic_launcher)
        iAdMob.getSplashAD(intent)
//        refreshAd()
//        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
//                .forUnifiedNativeAd { unifiedNativeAd ->
//                    val styles = NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(getResources().getColor(R.color.gnt_white))).build()
//
//                    val template = my_template
//                    template.setStyles(styles)
//                    template.setNativeAd(unifiedNativeAd)
//                }
//                .build()
//
//        adLoader.loadAd(AdRequest.Builder().build())

//        button.setOnClickListener {
//            iInterstitialAd.show()
//        }
        jumpToAD()
//        fl_ad_view.addView(iAdMob.bannerView)
    }

    //跳转到开屏广告页面
    private fun jumpToAD() {

        startActivity(intent)
        finish()
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
            GoogleAd(context, GAPPID, GBannerID, GInterstitialID, TSplashPosID)
        } else {
            TencentAd(context, TAPPID, TBannerID, TInterstitialID, TSplashPosID)
        }
    }

    /**
     * Populates a [UnifiedNativeAdView] object with data from a given
     * [UnifiedNativeAd].
     *
     * @param nativeAd the object containing the ad's assets
     * @param adView          the view to be populated
     */
    private fun populateUnifiedNativeAdView(nativeAd: UnifiedNativeAd, adView: UnifiedNativeAdView) {
        // Set the media view.
        adView.mediaView = adView.findViewById(R.id.ad_media) as MediaView

        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.priceView = adView.findViewById(R.id.ad_price)
        adView.starRatingView = adView.findViewById(R.id.ad_stars)
        adView.storeView = adView.findViewById(R.id.ad_store)
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView).text = nativeAd.headline
        adView.mediaView.setMediaContent(nativeAd.mediaContent)

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                    nativeAd.icon.drawable)
            adView.iconView.visibility = View.VISIBLE
        }

        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView.visibility = View.VISIBLE
        }

        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd)

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        val vc = nativeAd.videoController

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
            tv_video_status.setText(String.format(Locale.getDefault(),
                    "Video status: Ad contains a %.2f:1 video asset.",
                    vc.aspectRatio))

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(object : VideoController.VideoLifecycleCallbacks() {
                override fun onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    btn_refresh.setEnabled(true)
                    tv_video_status.setText("Video status: Video playback has ended.")
                    super.onVideoEnd()
                }
            })
        } else {
            tv_video_status.setText("Video status: Ad does not contain a video asset.")
            btn_refresh.setEnabled(true)
        }
    }

    /**
     * Creates a request for a new native ad based on the boolean parameters and calls the
     * corresponding "populate" method when one is successfully returned.
     *
     */
    private fun refreshAd() {
        btn_refresh.setEnabled(false)

        val builder = AdLoader.Builder(this, ADMOB_AD_UNIT_ID)

        builder.forUnifiedNativeAd { unifiedNativeAd ->
            // OnUnifiedNativeAdLoadedListener implementation.
            // You must call destroy on old ads when you are done with them,
            // otherwise you will have a memory leak.
            if (nativeAd != null) {
                nativeAd!!.destroy()
            }
            nativeAd = unifiedNativeAd
            val frameLayout = fl_adplaceholder
            val adView = layoutInflater
                    .inflate(R.layout.ad_unified, null) as UnifiedNativeAdView
            populateUnifiedNativeAdView(unifiedNativeAd, adView)
            frameLayout.removeAllViews()
            frameLayout.addView(adView)
        }

        val videoOptions = VideoOptions.Builder()
                .setStartMuted(true)
                .build()

        val adOptions = NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build()

        builder.withNativeAdOptions(adOptions)

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                btn_refresh.setEnabled(true)
                Toast.makeText(this@MainActivity, "Failed to load native ad: $errorCode", Toast.LENGTH_SHORT).show()
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())
    }

}
