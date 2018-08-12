package com.domker.rank.fragment.data

import android.content.Context
import com.umeng.analytics.MobclickAgent

/**
 * 事件统计
 */
object RankEvent {
    /**
     * 统计tab点击次数
     */
    fun onClickTab(context: Context, tabName: String) {
        MobclickAgent.onEvent(context, "tab_click", tabName)
    }

    /**
     * 点击查看详情
     */
    fun onDetailView(context: Context, subjectName: String) {
        MobclickAgent.onEvent(context, "detail_view", subjectName)
    }

    /**
     * 搜索
     */
    fun onSearch(context: Context, query: String) {
        MobclickAgent.onEvent(context, "search_query", query)
    }
}