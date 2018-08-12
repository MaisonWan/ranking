package com.domker.rank.fragment.data

import android.content.Context
import android.content.Intent
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.domker.rank.DetailActivity
import com.domker.rank.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

class SubjectListAdapter(private val context: Context) : RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * 学科列表
     */
    private val allSubjectList: MutableList<SubjectData> = mutableListOf()

    private var showSubjectList: MutableList<SubjectData>
    private var showOldSubjectList: MutableList<SubjectData> = mutableListOf()

    init {
        val input = context.assets.open("subject.property")
        val reader = BufferedReader(InputStreamReader(input) as Reader?)
        var line: String? = reader.readLine()
        while (line != null) {
            val items = line.split(" ")
            if (items.size == 3) {
                allSubjectList.add(SubjectData(items[0], items[1], items[2]))
            }
            line = reader.readLine()
        }
        reader.close()
        // 默认都显示
        showSubjectList = mutableListOf()
        showSubjectList.addAll(allSubjectList)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val itemView = layoutInflater.inflate(R.layout.subject_item_layout, null)
        itemView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return SubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return showSubjectList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = showSubjectList[position]
        holder.nameTextView?.text = subject.name
        holder.typeTextView?.text = subject.type
        holder.iconTextView?.text = subject.name[0].toString()
        holder.item?.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("subjectName", subject.name)
            intent.putExtra("imageName", subject.imageName)
            context.startActivity(intent)
            RankEvent.onDetailView(context, subject.name)
        }

    }

    /**
     * 使用搜索词过滤
     */
    fun searchSubject(query: String?) {
        showOldSubjectList.clear()
        showOldSubjectList.addAll(showSubjectList)

        showSubjectList.clear()
        if (query.isNullOrBlank()) {
            showSubjectList.addAll(allSubjectList)
        } else {
            allSubjectList.filter {
                it.name.contains(query!!)
            }.forEach {
                showSubjectList.add(it)
            }
        }
    }

    /**
     * 调用执行DiffUtil的接口
     */
    fun notifyDiffData() {
        val diffResult = DiffUtil.calculateDiff(DiffCallBack(showOldSubjectList, showSubjectList), true)
        diffResult.dispatchUpdatesTo(this)
    }

    class SubjectViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val item: View? = itemView
        val nameTextView: TextView? = itemView?.findViewById(R.id.textViewSubject)
        val typeTextView: TextView? = itemView?.findViewById(R.id.textViewType)
        val iconTextView: TextView? = itemView?.findViewById(R.id.textViewIcon)
    }
}