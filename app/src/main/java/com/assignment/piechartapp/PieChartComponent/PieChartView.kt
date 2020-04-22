package com.assignment.piechartapp.PieChartComponent

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop

class PieChartView(context: Context, attrs: AttributeSet): View(context, attrs) {
    private val paint: Paint = Paint()
    private var items: Array<ChartItem>? = null
    private val rectF = RectF(0.0f, 0.0f, 0.0f, 0.0f)

    init{
        val density = context.resources.displayMetrics.density
        val strokeWidth = 25 * density     // To convert width in dp to pixels

        paint.isAntiAlias = true
        paint.isDither = true
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = strokeWidth
        paint.style = Paint.Style.STROKE
    }

    private fun getScaledValues(items: Array<ChartItem>): FloatArray{
        // Calculating total of all percent values
        var total = 0.0f
        for (item in items)
            total += item.percent

        // Creating scaled values for the arcs of the chart
        val scaledValues = FloatArray(items.size)
        for (i in scaledValues.indices)
            scaledValues[i] = (items[i].percent / total) * 360

        return scaledValues
    }

    override fun onDraw(canvas: Canvas?) {
        val tempItems = items?.clone()  // Done to prevent errors due to data change
        if(tempItems != null){
            val startTop = 0.0f + marginTop
            val startLeft = 0.0f + marginStart
            val endRight = width.toFloat() - marginEnd
            val endBottom = endRight    // For getting a square chart

            rectF.set(startLeft, startTop, endRight, endBottom) // Creating a box
            val scaledValues = getScaledValues(tempItems)

            var startPoint = 270.0f  // To start from top center point
            for(i in tempItems.indices.reversed()){  // Using reversed because arcs are drawn clockwise
                paint.color = Color.parseColor(tempItems[i].color)
                canvas?.drawArc(rectF, startPoint, scaledValues[i], false, paint)
                startPoint += scaledValues[i]
            }
        }
    }

    fun setData(itemsArray: Array<ChartItem>){
        items = itemsArray
        invalidate()
    }
}