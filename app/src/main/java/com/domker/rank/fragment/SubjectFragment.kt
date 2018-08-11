package com.domker.rank.fragment

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import com.domker.rank.R
import com.domker.rank.fragment.data.SubjectListAdapter
import kotlinx.android.synthetic.main.fragment_subject.*


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subjectListAdapter = SubjectListAdapter(activity)
        subjectRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        subjectRecyclerView.layoutManager = LinearLayoutManager(activity)
        subjectRecyclerView.adapter = subjectListAdapter
    }

    override fun onResume() {
        super.onResume()
        activity.title = getString(R.string.title_subject)

        searchViewSubject.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return query.isNullOrBlank()
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                subjectListAdapter.searchSubject(newText)
                subjectListAdapter.notifyDataSetChanged()
                return newText.isNullOrBlank()
            }

        })
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
