package com.example.screenchanged

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.screenchanged.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MyForegroundService:Service() {
    private lateinit var db : FirebaseDatabase
    companion object{
        var flag : Boolean = false
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(
            Runnable {
                run {
                    while(true){
                        checkFirebaseEntry()

                        Log.d("MyBackgroundService" , "iamstupid from foreground service")

                        Thread.sleep(2000)

                        if (flag){
                            stopForegroundService()
                        }

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
            .setSmallIcon(R.drawable.ic_launcher_background)



//        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
//        val notification: Notification.Builder = Builder(this, CHANNELID)
//            .setContentText("Service is running")
//            .setContentTitle("Service enabled")
//            .setSmallIcon(R.drawable.ic_launcher_background)
        startForeground(1001 , notification.build())

        return super.onStartCommand(intent, flags, startId)
    }

    private fun checkFirebaseEntry() {
        db =  Firebase.database
        Log.d("MyBackgroundService" , "firebase function reached")
        val ref = FirebaseDatabase.getInstance().getReference("ScreenChange")
        Log.d("MyBackgroundService" , "$ref")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("MyBackgroundService" , "snapshot ${snapshot.exists()}")
                for(children in snapshot.children){
                    Log.d("MyBackgroundService" , "$children")
                    val user = children.getValue(User::class.java)
                    Log.d("MyBackgroundService" , "$user , ${user?.About}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("MyBackgroundService" , "error $error")
            }
        })
    }

    private fun stopForegroundService() {
        stopForeground(true)
        stopSelf()
    }

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }
}