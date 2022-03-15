package com.example.screenchanged

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService:Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(
            Runnable {
                run {
                    while(true){
                        Log.d("MyBackgroundService" , "iamstupid from background service")
                        Thread.sleep(2000)
                    }
                }
            }
        ).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}