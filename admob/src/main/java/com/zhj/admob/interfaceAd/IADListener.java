package com.zhj.admob.interfaceAd;

import com.qq.e.comm.util.AdError;

/**
 * 统一的广告状态监听接口
 */
public interface IADListener {
    //加载完毕，此回调后才可以调用 show 方法
    void onAdLoaded();

    //加载失败，error 对象包含了错误码和错误信息
    void onNoAD(AdError error);

    //展开时回调
    void onAdOpened();

    //点击时回调
    void onAdClicked();

    //关闭时回调
    void onAdClosed();
}
