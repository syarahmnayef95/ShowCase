package com.trendyol.showcase.util

import android.content.res.Resources
import android.graphics.Rect
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.menu.MenuView
import com.trendyol.showcase.ui.tooltip.ArrowPosition
import kotlin.math.pow
import kotlin.math.sqrt

object TooltipFieldUtil {

    fun calculateArrowPosition(resources: Resources, verticalCenter: Float): ArrowPosition {
        val screenHeight = resources.displayMetrics.heightPixels

        return if (screenHeight / 2 > verticalCenter) ArrowPosition.UP else ArrowPosition.DOWN
    }
    fun calculateViewRadius(view: Any?):Float {
        if (view is View){
         return   calculateRadius(view)
        }else
        return    calculateItemRadius(view as MenuItem)

    }
    fun calculateRadius(view: View): Float {
        val x = view.width.toDouble() / 2
        val y = view.height.toDouble() / 2

        return sqrt(x.pow(2) + y.pow(2)).toFloat()
    }

    fun calculateItemRadius(view:MenuItem): Float {
        val x = 100.toDouble()/2
        val y = 100.toDouble()/2

        return sqrt(x.pow(2) + y.pow(2)).toFloat()
    }
    fun calculateMarginForCircle(resources: Resources, top: Float, bottom: Float, arrowPosition: ArrowPosition, statusBarDiff: Int) = when (arrowPosition) {
        ArrowPosition.UP -> bottom.toInt() + statusBarDiff
        ArrowPosition.DOWN -> resources.displayMetrics.heightPixels - top.toInt()// + statusBarDiff
        else -> 0//throw IllegalArgumentException("arrowPosition should be ArrowPosition.UP or ArrowPosition.DOWN")
    }

    fun calculateMarginForRectangle(resources: Resources, top: Float, bottom: Float, arrowPosition: ArrowPosition, statusBarDiff: Int) =
            when (arrowPosition) {
        ArrowPosition.UP -> bottom.toInt() + statusBarDiff
        ArrowPosition.DOWN -> resources.displayMetrics.heightPixels - top.toInt() - statusBarDiff
        else -> 0//throw IllegalArgumentException("arrowPosition should be ArrowPosition.UP or ArrowPosition.DOWN")

//                resources.displayMetrics.heightPixels - top.toInt() - statusBarDiff
    }

    fun calculateArrowMargin(resources: Resources, horizontalCenter: Float): Int {
        val arrowHalfWidth = (15 * resources.displayMetrics.density)

        return (horizontalCenter - arrowHalfWidth).toInt()
    }
}
