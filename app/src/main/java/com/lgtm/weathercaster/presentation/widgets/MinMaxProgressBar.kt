package com.lgtm.weathercaster.presentation.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class MinMaxProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context,attrs, defStyle) {

    private var totalMin = 0
    private var totalMax = 0
    private var min = 0
    private var max = 0
    private var step = 0

    private val totalRect = RectF()
    private val progressRect = RectF()

    private val progressPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#aaff0000")
        style = Paint.Style.FILL
    }

    fun setData(totalMin: Int, totalMax: Int, min: Int, max: Int) {
        this.totalMin = totalMin
        this.totalMax = totalMax
        this.min = min
        this.max = max
        step = totalMax - totalMin
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(progressRect, progressPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setupTotalBound(w, h)
        setupProgressBound(w, h)
    }

    private fun setupTotalBound(w: Int, h: Int) {
        val newRect = RectF(
            paddingLeft.toFloat(),
            paddingTop.toFloat(),
            (w - paddingRight).toFloat(),
            (h - paddingBottom).toFloat(),
        )

        totalRect.set(newRect)
    }

    private fun setupProgressBound(w: Int, h: Int) {
        val leftMost = paddingLeft.toFloat()
        val rightMost = (w - paddingRight).toFloat()
        val widthPerStep = (rightMost - leftMost) / step

        val left = leftMost + widthPerStep * (min - totalMin)
        val right = rightMost - widthPerStep * (totalMax - max)

        val newRect = RectF(
            left,
            paddingTop.toFloat(),
            right,
            (h - paddingBottom).toFloat()
        )

        progressRect.set(newRect)
    }

}