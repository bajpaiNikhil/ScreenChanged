package com.example.screenchanged

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


class MyForegroundService:Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(
            Runnable {
                run {
                    while(true){
                        Log.d("MyBackgroundService" , "iamstupid from foreground service")
                        Thread.sleep(2000)
                    }
                }
            }
        ).start()

        val CHANNEL_ID = "foreground_service_id"
        val channel = NotificationChannel(
            CHANNEL_ID ,
            CHANNEL_ID ,
            NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this ,CHANNEL_ID)
            .setContentText("IamStupid from Service")
            .setContentTitle("Service Enabled")
            .setSmallIcon(R.drawable.ic_lock_idle_alarm)



//        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
//        val notification: Notification.Builder = Builder(this, CHANNELID)
//            .setContentText("Service is running")
//            .setContentTitle("Service enabled")
//            .setSmallIcon(R.drawable.ic_launcher_background)
        startForeground(1001 , notification.build())

        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(p0: Intent?): IBinder? {
       return null
    }
}