package com.a1androidhmw

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


class CalculateService : Service() {

    private val LOG_TAG = "Service"

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "MyService onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "MyService onDestroy")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "MyService onStartCommand")

        val firstNum = intent.getDoubleExtra(FIRST_NUMBER, -2.0)
        val secondNum = intent.getDoubleExtra(SECOND_NUMBER, -1.0)
        val result = firstNum + secondNum

        val intentToBR = Intent()
        intentToBR.action = BROADCAST_ACTION
        intentToBR.putExtra(RESULT, result)
        //intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES) // разрешающий запуск активности остановленных приложений
        sendBroadcast(intentToBR)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }
}