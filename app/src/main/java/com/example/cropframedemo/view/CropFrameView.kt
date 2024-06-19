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

class CropFrameView : View {
    private var playerViewLeft = 0
    private var playerViewTop = 0
    private var playerViewRight = 0
    private var playerViewBottom = 0

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private val gestureDetector = GestureDetectorCompat(context, GestureListener(this))

    private var frameX = 0f
    private var frameY = 0f
    private var frameWidth = 0f
    private var frameHeight = 0f
    private var aspectRatio = AspectRatioEnum.ORIGIN.ordinal

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        context?.let {
            val array: TypedArray = it.obtainStyledAttributes(attrs, R.styleable.CropFrameView)
            aspectRatio =
                array.getInt(R.styleable.CropFrameView_aspect_ratio, AspectRatioEnum.ORIGIN.ordinal)
            array.recycle()
        }
    }

    fun getFrameX(): Float {
        return frameX
    }

    fun getFrameY(): Float {
        return frameY
    }

    fun setPlayerViewBounds(left: Int, top: Int, right: Int, bottom: Int) {
        playerViewLeft = left
        playerViewTop = top
        playerViewRight = right
        playerViewBottom = bottom
        changeFrameAspectRatio(aspectRatio)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(frameX, frameY, frameX + frameWidth, frameY + frameHeight, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasureByAspectRatio(aspectRatio, widthMeasureSpec, heightMeasureSpec)
    }

    private fun setMeasureByAspectRatio(
        aspectRatio: Int,
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            setMeasuredDimension(frameWidth.toInt(), frameHeight.toInt())
        } else {
            super.setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    fun updateFramePosition(newX: Float, newY: Float) {
        val adjustedX = when {
            newX < playerViewLeft -> playerViewLeft.toFloat()
            newX + frameWidth > playerViewRight -> playerViewRight - frameWidth
            else -> newX
        }

        val adjustedY = when {
            newY < playerViewTop -> playerViewTop.toFloat()
            newY + frameHeight > playerViewBottom -> playerViewBottom - frameHeight
            else -> newY
        }

        frameX = adjustedX
        frameY = adjustedY

        invalidate()
    }

    fun changeAspectRatio(ratio: AspectRatioEnum) {
        aspectRatio = ratio.ordinal
        changeFrameAspectRatio(aspectRatio)
        invalidate()
    }

    private fun changeFrameAspectRatio(ratio: Int) {
        when (ratio) {
            AspectRatioEnum.ORIGIN.ordinal -> {
                frameWidth = (playerViewRight - playerViewLeft).toFloat()
                frameHeight = (playerViewBottom - playerViewTop).toFloat()
                frameX = playerViewLeft.toFloat()
                frameY = playerViewTop.toFloat()
            }

            AspectRatioEnum.AR_1_1.ordinal -> {
                frameWidth = (playerViewRight - playerViewLeft).toFloat()
                frameHeight = frameWidth
                frameX = playerViewLeft.toFloat()
                frameY = playerViewTop.toFloat()
            }

            AspectRatioEnum.AR_4_5.ordinal -> {
                frameWidth = (playerViewRight - playerViewLeft).toFloat()
                frameHeight = frameWidth / 4 * 5
                frameX = playerViewLeft.toFloat()
                frameY = playerViewTop.toFloat()
            }

            AspectRatioEnum.AR_16_9.ordinal -> {
                frameWidth = (playerViewRight - playerViewLeft).toFloat()
                frameHeight = frameWidth / 16 * 9
                frameX = playerViewLeft.toFloat()
                frameY = playerViewTop.toFloat()
            }

            AspectRatioEnum.AR_9_16.ordinal -> {
                frameWidth = (playerViewRight - playerViewLeft).toFloat()
                frameHeight = frameWidth / 9 * 16
                frameX = playerViewLeft.toFloat()
                frameY = playerViewTop.toFloat()
            }

        }
    }
}