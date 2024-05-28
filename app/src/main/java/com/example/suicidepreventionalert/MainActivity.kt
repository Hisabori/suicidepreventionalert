package com.example.suicidepreventionalert

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.suicidepreventionalert.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnableService.setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        binding.btnCallHelpLine.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:109"))
            startActivity(intent)
        }
    }
}
