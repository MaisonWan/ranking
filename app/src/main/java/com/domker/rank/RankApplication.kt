package com.domker.rank

import android.app.Application
import com.umeng.commonsdk.UMConfigure

/**
 * 系统Application初始化
 */
class RankApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UMConfigure.init(this, "5b6fb488f29d9818cc000078", "update", UMConfigure.DEVICE_TYPE_PHONE, null)
    }
}