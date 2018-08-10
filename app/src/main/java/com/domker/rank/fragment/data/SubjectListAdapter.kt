package com.domker.rank.fragment.data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SubjectListAdapter(private val context: Context) : RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder>() {
    private val fileList: Array<out String>? = context.assets.list("subjects")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val textView = TextView(context)
        return SubjectViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return fileList?.size ?: 0
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        fileList?.let {
            holder.nameTextView?.text = fileList[position]
        }
    }

    class SubjectViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView? = itemView as TextView

    }
}