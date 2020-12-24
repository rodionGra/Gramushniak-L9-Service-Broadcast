package com.a1androidhmw

import java.lang.Double
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_.*

/**Write a project that will use the broadcast receiver to capture the result of the Service.
 * Service must be started with Activity. Service performs any arithmetic operation.
 * Display the result of the operation in the Fragment, which is open in Activity.
 */

class MainActivity : AppCompatActivity() {

    private var br: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getDoubleExtra(RESULT, 0.0)
            fragment.resultText.text = "Result = $result"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(br, IntentFilter(BROADCAST_ACTION))
    }

    override fun onStop() {
        super.onStop()
        try {
            unregisterReceiver(br)
        } catch (e: IllegalArgumentException) {
            Log.e("Broadcast", "Time tick Receiver not registered", e)
        }
    }

    private fun setupListeners(){
        start_service_textV.setOnClickListener {
            val number1Str : String = first_number_edit.text.toString()
            val number2Str : String = second_number_edit.text.toString()

            if(number1Str.trim() == "" || number2Str.trim() == ""){
                Toast.makeText(this, "Sorry you did't type number" , Toast.LENGTH_SHORT).show()
            }
            else {
                val number1 = Double.valueOf(number1Str)
                val number2 = Double.valueOf(number2Str)
                Intent(this, CalculateService::class.java).apply {
                    putExtra(FIRST_NUMBER, number1)
                    putExtra(SECOND_NUMBER, number2)
                    startService(this)
                }
            }
        }
    }

}