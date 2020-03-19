package com.zhj.admob.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhj.admob.IAdMob
import com.zhj.admob.R
import com.zhj.admob.googlead.GoogleAd
import com.zhj.admob.tencentad.TencentAd
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iAdMob = getAdMob(this, this)
        iAdMob.initAdSdk()

        button.setOnClickListener {
            fl_ad_view.addView(iAdMob.bannerView)
        }
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
