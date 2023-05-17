package com.example.donation.Dashboard

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donation.R
import com.example.donation.databinding.FragmentMonthlyGraphBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class MonthlyGraph : Fragment(R.layout.fragment_monthly_graph) {

    private lateinit var binding : FragmentMonthlyGraphBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonthlyGraphBinding.bind(view)
        val monthlyChart = binding.monthlyChart


        val xAxis: XAxis = monthlyChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        //Entries used for monthly graph
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(101f, 100f))
        list.add(Entry(105f, 150f))
        list.add(Entry(110f, 190f))
        list.add(Entry(150f, 90f))

        //Assigning the entries as dataset for the monthly graph
        val monthlyDataSet = LineDataSet(list, "Monthly")
        monthlyDataSet.apply {
            setColors(ColorTemplate.COLORFUL_COLORS, 255)
            valueTextColor = Color.BLACK
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        }

        val monthlyData = LineData(monthlyDataSet)

        monthlyChart.apply {
            data = monthlyData
            description.text = "monthly Graph"
            setBackgroundColor(Color.parseColor("#FCEBEB"))
            //Setting the color of the graph, excluding the axes
            setDrawGridBackground(true)
            setGridBackgroundColor(Color.parseColor("#FFFFFF"))
            animateX(500)
            animateY(1000)
        }
    }
}