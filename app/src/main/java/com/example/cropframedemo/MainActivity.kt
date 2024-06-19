package com.example.cropframedemo

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.example.cropframedemo.databinding.ActivityMainBinding
import com.example.cropframedemo.view.AspectRatioEnum

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupInitialFrameBounds()

        binding.btn1.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_1_1)
        }
        binding.btn2.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_4_5)
        }
        binding.btn3.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_16_9)
        }
        binding.btn4.setOnClickListener {
            binding.cropFrameView.changeAspectRatio(AspectRatioEnum.AR_9_16)
        }

        binding.btn5.setOnClickListener {
            Log.d("CROP_FRAME", "X: ${binding.cropFrameView.getFrameX()}, Y: ${binding.cropFrameView.getFrameY()}")
        }
    }

    private fun setupInitialFrameBounds() {
        binding.playerView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.playerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.cropFrameView.setPlayerViewBounds(
                    binding.playerView.left,
                    binding.playerView.top,
                    binding.playerView.right,
                    binding.playerView.bottom
                )
            }
        })
    }
}