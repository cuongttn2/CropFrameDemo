package com.example.cropframedemo.view

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class GestureListener(private val cropFrameView: CropFrameView) :
    GestureDetector.SimpleOnGestureListener() {

    private var lastTouchX = 0f
    private var lastTouchY = 0f

    override fun onDown(e: MotionEvent): Boolean {
        lastTouchX = e.rawX
        lastTouchY = e.rawY
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(
            "CROP_FRAME",
            "getFrameX: ${cropFrameView.getFrameX()} , getFrameX: ${cropFrameView.getFrameY()}"
        )
        val newX = cropFrameView.getFrameX() + e2.rawX - lastTouchX
        val newY = cropFrameView.getFrameY() + e2.rawY - lastTouchY

        Log.d("CROP_FRAME", "newX: $newX , newY: $newY")

        lastTouchX = e2.rawX
        lastTouchY = e2.rawY

        cropFrameView.updateFramePosition(newX, newY)
        return true
    }
}