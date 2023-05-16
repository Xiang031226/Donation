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

    protected fun hideBottomBar(bottomBarId : Int = R.id.bottom_navigation_bar) {
        bottomNavigationView = activity?.findViewById(bottomBarId)
        bottomNavigationView?.visibility = View.GONE
    }

    protected fun showBottomBar(bottomBarId : Int = R.id.bottom_navigation_bar) {
        bottomNavigationView = activity?.findViewById(bottomBarId)
        bottomNavigationView?.visibility = View.VISIBLE
    }

    protected fun showAppBar(appBarId : Int = R.id.appBarLayout) {
        appBarLayoutView  = activity?.findViewById(appBarId)
        appBarLayoutView?.visibility = View.VISIBLE
    }

    protected fun hideAppBar(appBarId : Int = R.id.appBarLayout) {
        appBarLayoutView  = activity?.findViewById(appBarId)
        appBarLayoutView?.visibility = View.GONE
    }

    protected fun hideTab(tabLayoutId : Int = R.id.tab_layout) {
        tabLayout = activity?.findViewById(tabLayoutId)
        tabLayout?.visibility = View.GONE
    }

    protected fun showTab(tabLayoutId : Int = R.id.tab_layout) {
        tabLayout = activity?.findViewById(tabLayoutId)
        tabLayout?.visibility = View.VISIBLE
    }
}