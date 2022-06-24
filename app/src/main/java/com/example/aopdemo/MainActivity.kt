package com.example.aopdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aopdemo.aop.fastclick.FastClickView
import com.example.aopdemo.aop.log.LogMethod
import com.example.aopdemo.aop.timeconsume.TimeConsume

class MainActivity : AppCompatActivity() {
    @LogMethod(before = true, after = true, desc = "onCreate")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnFastClick).setOnClickListener(object : View.OnClickListener {
            @FastClickView(2000)
            override fun onClick(view: View?) {
                Log.d("MainActivity", "onClick: click me...")
            }
        })
    }

    @TimeConsume
    override fun onStart() {
        try {
            Thread.sleep(3000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onStart()
    }

    @TimeConsume
    override fun onResume() {
        super.onResume()
    }
}