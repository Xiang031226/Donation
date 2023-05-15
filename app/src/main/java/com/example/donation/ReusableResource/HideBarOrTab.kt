package com.example.donation.ReusableResource

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.donation.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

abstract class HideBarOrTab: Fragment() {
    private var bottomNavigationView: BottomNavigationView? = null
    private var appBarLayoutView: AppBarLayout? = null
    private var tabLayout: TabLayout? = null

    protected fun hideBottomBar() {
        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation_bar)
        bottomNavigationView?.visibility = View.GONE
    }

    protected fun showBottomBar() {
        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation_bar)
        bottomNavigationView?.visibility = View.VISIBLE
    }

    protected fun showAppBar() {
        appBarLayoutView  = activity?.findViewById(R.id.appBarLayout)
        appBarLayoutView?.visibility = View.VISIBLE
    }

    protected fun hideAppBar() {
        Toast.makeText(activity, "Damn", Toast.LENGTH_SHORT).show()
        appBarLayoutView  = activity?.findViewById(R.id.appBarLayout)
        appBarLayoutView?.visibility = View.GONE
    }

    protected fun hideTab() {
        tabLayout = activity?.findViewById(R.id.tab_layout)
        tabLayout?.visibility = View.GONE
    }

    protected fun showTab() {
        tabLayout = activity?.findViewById(R.id.tab_layout)
        tabLayout?.visibility = View.VISIBLE
    }
}