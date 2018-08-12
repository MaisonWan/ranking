package com.domker.rank

import android.support.v7.app.AppCompatActivity
import com.umeng.analytics.MobclickAgent

/**
 * Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}