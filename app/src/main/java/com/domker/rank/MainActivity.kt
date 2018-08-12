package com.domker.rank

import android.Manifest
import android.app.Fragment
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
import android.support.v4.content.ContextCompat
import com.domker.rank.fragment.AboutFragment
import com.domker.rank.fragment.MainFragment
import com.domker.rank.fragment.OnFragmentInteractionListener
import com.domker.rank.fragment.SubjectFragment
import com.domker.rank.fragment.data.RankEvent
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), OnFragmentInteractionListener, OnRequestPermissionsResultCallback {
    private lateinit var mainFragment: MainFragment
    private lateinit var subjectFragment: SubjectFragment
    private lateinit var aboutFragment: AboutFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragment(mainFragment)
                RankEvent.onClickTab(this, "home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_subject -> {
                showFragment(subjectFragment)
                RankEvent.onClickTab(this, "subject")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                showFragment(aboutFragment)
                RankEvent.onClickTab(this, "about")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun initFragment() {
        mainFragment = MainFragment()
        subjectFragment = SubjectFragment()
        aboutFragment = AboutFragment()
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
        requestPermission()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    private fun requestPermission() {
        val permissions = arrayOf(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE)
        permissions.forEach {
            val permissionCheck = ContextCompat.checkSelfPermission(this, it)

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 1)
            }
        }
    }
}
