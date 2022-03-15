package com.example.screenchanged

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1?.action ==Intent.ACTION_BOOT_COMPLETED){
            val foregroundServiceIntent = Intent(p0 ,MyForegroundService::class.java )
            p0?.startForegroundService(foregroundServiceIntent)
        }
    }
}