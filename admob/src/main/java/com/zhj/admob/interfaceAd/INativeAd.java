package com.zhj.admob.interfaceAd;

/**
 * 原生广告接口
 */
public interface INativeAd {
    public static final int SMALL_NATIVE_AD = 1;
    public static final int MEDIUM_NATIVE_AD = 2;

    /**
     * 展示插屏广告，有遮罩
     */
    void show();

    /**
     * 展示插屏广告，无遮罩
     */
    void showAsPopupWindow();

}
