package com.domker.rank

import android.app.Fragment
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.domker.rank.fragment.MainFragment
import com.domker.rank.fragment.OnFragmentInteractionListener
import com.domker.rank.fragment.SubjectFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
    private lateinit var mainFragment: MainFragment
    private lateinit var subjectFragment: SubjectFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragment(mainFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_subject -> {
                showFragment(subjectFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun initFragment() {
        mainFragment = MainFragment()
        subjectFragment = SubjectFragment()
    }

    /**
     * 显示主页
     */
    private fun showFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.mainView, fragment).commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        showFragment(subjectFragment)
        navigation.selectedItemId = R.id.navigation_subject
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}
