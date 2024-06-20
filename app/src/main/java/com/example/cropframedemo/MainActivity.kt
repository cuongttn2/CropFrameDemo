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
            val distances = binding.cropFrameView.getDistancesToPlayerView()
            Log.d(
                "DISTANCES",
                "Left: ${distances.left}, Top: ${distances.top}, Right: ${distances.right}, Bottom: ${distances.bottom}"
            )
            Log.d(
                "DISTANCES", "Player left: ${binding.playerView.left}\n" +
                        "Player top: ${binding.playerView.top}\n" +
                        "Player right: ${binding.playerView.right}\n" +
                        "Player bottom: ${binding.playerView.bottom}\n" +
                        "Player width: ${binding.playerView.width}\n" +
                        "Player height: ${binding.playerView.height}"
            )
        }
    }

    private fun setupInitialFrameBounds() {
        binding.playerView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
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