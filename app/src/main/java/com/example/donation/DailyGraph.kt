package com.example.donation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.donation.databinding.FragmentDailyGraphBinding
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class DailyGraph : Fragment(R.layout.fragment_daily_graph) {

    private lateinit var binding : FragmentDailyGraphBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDailyGraphBinding.bind(view)
        val dailyChart = binding.dailyChart

        //Entries used for daily graph
        val list: ArrayList<Entry> = ArrayList()
        list.add(Entry(100f, 100f))
        list.add(Entry(105f, 105f))
        list.add(Entry(110f, 90f))
        list.add(Entry(120f, 120f))

        //Assigning the entries as dataset for the daily graph
        val dailyDataSet = LineDataSet(list, "Daily")
        dailyDataSet.apply {
            setColors(ColorTemplate.COLORFUL_COLORS, 255)
            valueTextColor = Color.BLACK
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        }

        val dailyData = LineData(dailyDataSet)

        dailyChart.apply {
            data = dailyData
            description.text = "Daily Graph"
            setBackgroundColor(Color.parseColor("#FCEBEB"))
            //Setting the color of the graph, excluding the axes
            setDrawGridBackground(true)
            setGridBackgroundColor(Color.parseColor("#FFFFFF"))
            animateX(500)
            animateY(1000)
        }
    }
}