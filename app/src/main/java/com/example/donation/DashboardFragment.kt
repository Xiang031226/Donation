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

        //initialize parent (AdminActivity) navController
        val navHost = requireActivity().supportFragmentManager.findFragmentById(R.id.fcv_admin)
        val navController = navHost?.findNavController()

        //daily graph is shown by default
        changeGraph(DailyGraph())

        dashboardBinding.apply {

            volunteerApplicationContainer.setOnClickListener {
                navController?.navigate(R.id.application_fragment)
            }

            daily.setOnClickListener {
                changeGraph(DailyGraph())
            }

            monthly.setOnClickListener {
                changeGraph(MonthlyGraph())
            }
        }
    }

    private fun changeGraph(graph : Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_graph, graph)
            commit()
        }
        changeTab(graph)
    }

    private fun changeTab(graph : Fragment) {
        lateinit var selected: TextView
        lateinit var nonSelected: TextView

        if (graph is DailyGraph) {
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
