package com.domker.rank.fragment

import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.domker.rank.R
import com.domker.rank.fragment.data.SubjectListAdapter
import kotlinx.android.synthetic.main.fragment_subject.*
import android.support.v7.widget.DividerItemDecoration



/**
 * 专业排名
 */
class SubjectFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var subjectListAdapter: SubjectListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity.title = getString(R.string.title_subject)

        subjectListAdapter = SubjectListAdapter(activity)
        subjectRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        subjectRecyclerView.layoutManager = LinearLayoutManager(activity)
        subjectRecyclerView.adapter = subjectListAdapter
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
