package com.domker.rank.fragment


import android.app.Fragment
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domker.rank.R
import kotlinx.android.synthetic.main.fragment_about.*


/**
 * 关于
 */
class AboutFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity.title = getString(R.string.title_info)
        textViewVersion.text = "${getString(R.string.app_name)} V${getVersionName()}"
    }

    private fun getVersionName(): String {
        return try {
            val pm = activity.packageManager
            val packageInfo = pm.getPackageInfo(activity.packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "1.0"
        }
    }
}
