package com.example.cropframedemo.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import com.example.cropframedemo.R

class CropFrameView1 : View {
    private var aspectRatio: Int = 0

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private val gestureDetector = GestureDetectorCompat(context, GestureListener1(this))

    internal var frameX = 100f
    internal var frameY = 100f
    private var frameWidth = 300f
    private var frameHeight = 400f

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val array = context!!.obtainStyledAttributes(attrs, R.styleable.CropFrameView)
        aspectRatio = getAspectRatio(array)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(frameX, frameY, frameX + frameWidth, frameY + frameHeight, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    fun updateFramePosition(newX: Float, newY: Float) {
        if (newX >= 0 && newX + frameWidth <= width) {
            frameX = newX
        }
        if (newY >= 0 && newY + frameHeight <= height) {
            frameY = newY
        }
        invalidate()
    }

    private fun getAspectRatio(typedArray: TypedArray): Int {
        return typedArray.getInt(
            R.styleable.CropFrameView_aspect_ratio,
            AspectRatioEnum.ORIGIN.ordinal
        )
    }


}

