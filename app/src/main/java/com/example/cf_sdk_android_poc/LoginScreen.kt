package com.example.cf_sdk_android_poc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cf_sdk.defifination.CFApiCall
import com.example.cf_sdk.logic.CFSDKResponseCallback
import com.example.cf_sdk.logic.changebanksdk.model.Session
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse
import com.example.cf_sdk_android_poc.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater())
        setContentView(binding.root)

        val cfApiCall = CFApiCall.getInstance(application)
        val baseUrl = intent.getStringExtra("base_url")
        val app_id = intent.getStringExtra("app_id")

        binding.btnLogin.setOnClickListener {
            if (binding.edtUsername.text.toString()
                    .isNotEmpty() && binding.edtPassword.text.toString().isNotEmpty()
            ) {
                cfApiCall.callLoginAPI(
                    binding.edtUsername.text.toString(),
                    binding.edtPassword.text.toString(),
                    baseUrl,app_id,
                    object : CFSDKResponseCallback<Session> {
                        override fun onSuccess(successResponse: Session?) {
                            binding.tvResponse.text = "Access Token = " + successResponse?.token
                        }

                        override fun onFailure(error: Throwable?) {
                            Toast.makeText(
                                this@LoginScreen,
                                (error as ChangebankResponse)?.getmMessage(),
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.tvResponse.text=""
                        }
                    })
            }

        }

    }
}