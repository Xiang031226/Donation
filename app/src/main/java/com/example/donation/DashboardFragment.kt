package com.example.donation

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.donation.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var dashboardBinding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return dashboardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize graphNavController
        val graphNavHost = childFragmentManager.findFragmentById(R.id.fcv_graph) as NavHostFragment
        val graphNavController = graphNavHost.navController

        //daily graph is shown by default
        graphNavController.navigate(R.id.dailyGraph)

        dashboardBinding.apply {
            daily.setOnClickListener {
                graphNavController.navigate(R.id.dailyGraph)
                changeTab()
            }
            monthly.setOnClickListener {
                graphNavController.navigate(R.id.monthlyGraph)
                changeTab()
            }
        }
    }

    private fun changeTab() {
        val graphNavHost = childFragmentManager.findFragmentById(R.id.fcv_graph) as NavHostFragment
        val graphNavController = graphNavHost.navController
        lateinit var selected: TextView
        lateinit var nonSelected: TextView

        val currentFragment = graphNavController.currentDestination?.id
        if (currentFragment == R.id.dailyGraph) {
            selected = dashboardBinding.daily
            nonSelected = dashboardBinding.monthly
        } else {
            selected = dashboardBinding.monthly
            nonSelected = dashboardBinding.daily
        }

        //changing background and text color of the tabs
        selected.setBackgroundResource(R.drawable.tab_active_shape)
        selected.setTypeface(null, BOLD)

        selected.setTextColor(Color.parseColor("#FFFFFF"))
        nonSelected.setBackgroundResource(R.color.transparent)
        nonSelected.setTextColor(Color.parseColor("#4D000000"))
    }

}
