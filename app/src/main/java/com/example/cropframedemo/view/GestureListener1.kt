package com.example.cropframedemo.view

import android.view.GestureDetector
import android.view.MotionEvent

class GestureListener1(private val cropFrameView1: CropFrameView1) :
    GestureDetector.SimpleOnGestureListener() {
    private var lastTouchX = 0f
    private var lastTouchY = 0f

    override fun onDown(e: MotionEvent): Boolean {
        lastTouchX = e.x
        lastTouchY = e.y
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        val newX = cropFrameView1.frameX - distanceX
        val newY = cropFrameView1.frameY - distanceY
        cropFrameView1.updateFramePosition(newX, newY)
        return true
    }
}