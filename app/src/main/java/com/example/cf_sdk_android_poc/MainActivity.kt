package com.example.cf_sdk_android_poc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cf_sdk.changebankapi.ChangebankSingleObserver
import com.example.cf_sdk.changebankapi.model.member.version.VersionConfig
import com.example.cf_sdk.changebankapi.parameter.member.SettingsParameter
import com.example.cf_sdk_android_poc.databinding.ActivityMainBinding
import com.example.sdk_no_dagger.changebankapi.task.VersionConfigTask
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.root)
        VersionConfigTask().execute(object :
            ChangebankSingleObserver<VersionConfig> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@MainActivity,"errorrrrrr", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(t: VersionConfig) {
                Toast.makeText(this@MainActivity,"Data== ${t.optionalUpdate.optionalVersion}",
                    Toast.LENGTH_SHORT).show()

            }
        }, SettingsParameter(null))
    }
}