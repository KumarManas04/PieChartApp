package com.assignment.piechartapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.piechartapp.PieChartComponent.ChartItem

import com.assignment.piechartapp.R
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val pieChartView = rootView.pie_chart_view
        val itemsArray = arrayOf(
            ChartItem("#ef5350", 20f),  // Red
            ChartItem("#9c27b0", 30f),  // Purple
            ChartItem("#00bcd4", 10f),  // Blue
            ChartItem("#ef6c00", 25f),  // Orange
            ChartItem("#263238", 15f)   // Blue Grey
        )
        pieChartView.setData(itemsArray)
        return rootView
    }

}
