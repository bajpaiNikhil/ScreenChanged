package com.example.screenchanged

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        for background service that is the service will run even if the application is in back ground state
//        val intent = Intent(this , MyBackgroundService::class.java)
//        startService(intent)

        if(!foregroundServiceRunning()){
            val intent = Intent(this , MyForegroundService::class.java)
            startForegroundService(intent)
        }

    }

    private fun foregroundServiceRunning(): Boolean {

        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (MyForegroundService::class.java.name == service.service.className) {
                return true
            }
        }
        return false

    }
}