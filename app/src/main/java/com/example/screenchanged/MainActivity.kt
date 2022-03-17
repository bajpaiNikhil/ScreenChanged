package com.example.screenchanged

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.example.screenchanged.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val alert = findViewById<LottieAnimationView>(R.id.alert_animation)
        val nothing = findViewById<LottieAnimationView>(R.id.nothing_found_animation)
//        for background service that is the service will run even if the application is in back ground state
//        val intent = Intent(this , MyBackgroundService::class.java)
//        startService(intent)

        if(!foregroundServiceRunning()){
            val intent = Intent(this , MyForegroundService::class.java)
            startForegroundService(intent)
        }

        if(MyForegroundService.flag){
            alert.visibility = View.VISIBLE
            nothing.visibility = View.INVISIBLE
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