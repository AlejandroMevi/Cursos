package com.example.cursos

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.cursos.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val STORAGE_PERMISSION_REQUEST_CODE = 1
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showNotificationSimple()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun showNotificationSimple() {
        val channelId = "pdf_download_channel"
        val channelName = "PDF Downloads"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                STORAGE_PERMISSION_REQUEST_CODE
            )
        } else {
            println("else")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, importance).apply {
                    lightColor = Color.BLUE
                    enableLights(true)
                }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
        //val downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        //val downloadFolderUri = Uri.parse(downloadFolder.path)

        //val intent = Intent(Intent.ACTION_VIEW)
        //intent.setDataAndType(downloadFolderUri, "resource/folder")
        //intent.addCategory(Intent.CATEGORY_OPENABLE)
        //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.mx/"))
        //val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            //.setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("titulo")
            .setContentText("texto")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val progressMax = 100
        val progressCurrent = 0
        NotificationManagerCompat.from(this).apply {
            notificationBuilder.setProgress(progressMax, progressCurrent, false)
            notify(1, notificationBuilder.build())
            notificationBuilder.setContentText("texto")
                .setProgress(0, 0, false)
            notify(1, notificationBuilder.build())
        }
    }
}