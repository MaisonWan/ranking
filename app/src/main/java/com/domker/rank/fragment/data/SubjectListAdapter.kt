package com.domker.rank.fragment.data

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.domker.rank.DetailActivity
import com.domker.rank.R
import java.io.BufferedReader
import java.io.InputStreamReader

class SubjectListAdapter(private val context: Context) : RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * 学科列表
     */
    private val subjectList: MutableList<SubjectData> = mutableListOf()

    init {
        val input = context.assets.open("subject.property")
        val reader = BufferedReader(InputStreamReader(input))
        var line: String? = reader.readLine()
        while (line != null) {
            val items = line.split(" ")
            if (items.size == 3) {
                subjectList.add(SubjectData(items[0], items[1], items[2]))
            }
            line = reader.readLine()
        }
        reader.close()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val itemView = layoutInflater.inflate(R.layout.subject_item_layout, null)
//        val height = UI.dip2px(context, 60)
        itemView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return SubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjectList[position]
        holder.nameTextView?.text = subject.name
        holder.typeTextView?.text = subject.type
        holder.iconTextView?.text = subject.name[0].toString()
        holder.item?.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("subjectName", subject.name)
            intent.putExtra("imageName", subject.imageName)
            context.startActivity(intent)
        }

    }

    class SubjectViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val item: View? = itemView
        val nameTextView: TextView? = itemView?.findViewById(R.id.textViewSubject)
        val typeTextView: TextView? = itemView?.findViewById(R.id.textViewType)
        val iconTextView: TextView? = itemView?.findViewById(R.id.textViewIcon)
    }
}