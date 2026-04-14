package com.example.churrascometro

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var sbMen = findViewById<SeekBar>(R.id.seekBarMen)
        var menQtt = findViewById<TextView>(R.id.textViewMen)

        var sbWomen = findViewById<SeekBar>(R.id.seekBarWomen)
        var womenQtt = findViewById<TextView>(R.id.textViewWomen)
        sbMen?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                menQtt.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        sbWomen?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                womenQtt.text = progress.toString()
                calculate(sbMen.progress, progress)
            }

            fun calculate(menQtt: Int, womenQtt: Int) {
                val outputSausage = findViewById<TextView>(R.id.outputSausage)
                val outputMeat = findViewById<TextView>(R.id.outputMeat)
                val sausageQtt: Double = (menQtt * 250.0 + womenQtt * 150.0)/1000
                val meatQtt: Double = (menQtt * 450.0 + womenQtt * 300.0)/1000
                outputSausage.text = sausageQtt.toString() + "Kg"
                outputMeat.text = meatQtt.toString() + "Kg"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }


}