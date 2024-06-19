package com.example.cropframedemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cropframedemo.databinding.ActivityMainBinding
import com.example.cropframedemo.view.AspectRatioEnum

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveCropFrame()
//        -vf "crop=w:h:out_w:out_h"
//        -vf "crop=getWidth:getHeight:0:???"

        binding.btn1.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_1_1)
            binding.cropFrameView.requestLayout()
            binding.cropFrameView.invalidate()
        }
        binding.btn2.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_4_5)
            binding.cropFrameView.requestLayout()
            binding.cropFrameView.invalidate()
        }
        binding.btn3.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_16_9)
            binding.cropFrameView.requestLayout()
            binding.cropFrameView.invalidate()
        }
        binding.btn4.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_9_16)
            binding.cropFrameView.requestLayout()
            binding.cropFrameView.invalidate()
        }

        binding.btn5.setOnClickListener {
            Log.d(
                "CROP_FRAME",
                "check x: ${binding.cropFrameView.getFrameX()} \ncheck y: ${binding.cropFrameView.getFrameY()}"
            )
        }


    }

    private fun moveCropFrame() {
        binding.playerView.post {
            binding.cropFrameView.setPlayerViewBounds(
                binding.playerView.left,
                binding.playerView.top,
                binding.playerView.right,
                binding.playerView.bottom
            )
        }
    }

    private fun moveCropFrame2() {
        //        binding.playerView.viewTreeObserver.addOnGlobalLayoutListener {
//            val location = IntArray(2)
//            binding.playerView.getLocationOnScreen(location)
//            val left = location[0]
//            val top = location[1]
//            val right = left + binding.playerView.width
//            val bottom = top + binding.playerView.height
//
//            binding.cropFrameView.setPlayerViewBounds(left, top, right, bottom)
//        }
    }
}