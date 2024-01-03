package com.example.cf_sdk_android_poc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cf_sdk_android_poc.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater())
        setContentView(binding.root)
    }
}