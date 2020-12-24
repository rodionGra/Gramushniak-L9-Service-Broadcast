package com.a1androidhmw

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


class CalculateService : Service() {


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val intentToBR = Intent()
        intentToBR.action = BROADCAST_ACTION
        intentToBR.putExtra(RESULT, addNumber(intent))
        sendBroadcast(intentToBR)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    private fun addNumber(intent: Intent) : Double{
        val firstNum = intent.getDoubleExtra(FIRST_NUMBER, -2.0)
        val secondNum = intent.getDoubleExtra(SECOND_NUMBER, -1.0)
        return firstNum + secondNum
    }
}