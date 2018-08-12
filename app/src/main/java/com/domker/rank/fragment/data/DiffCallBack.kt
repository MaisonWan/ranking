package com.domker.rank.fragment.data

import android.support.v7.util.DiffUtil

/**
 * DiffUtil的回调
 */
class DiffCallBack(private val oldDataList: List<SubjectData>,
                   private val newDataList: List<SubjectData>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataList[oldItemPosition]::class.java == newDataList[newItemPosition]::class.java
    }

    override fun getOldListSize(): Int = oldDataList.size

    override fun getNewListSize(): Int = newDataList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataList[oldItemPosition].name == newDataList[newItemPosition].name
    }
}