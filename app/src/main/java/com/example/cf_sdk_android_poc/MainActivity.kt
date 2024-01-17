package com.example.cf_sdk_android_poc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cf_sdk.defination.CFSDKCall
import com.example.cf_sdk.defination.CFSDKResponseCallback
import com.example.cf_sdk.defination.response.AccessTokenResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse
import com.example.cf_sdk.defination.response.ChangebankResponse
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk_android_poc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.root)
        binding.edtBaseUrl.setText("https://alb-internal.vtxn-qadev.cf-cloud.net/v1/")
        binding.edtAppId.setText("20cbd0a0-fed3-407e-9be2-ba3825e6faaf")
        binding.btnGetSettings.setOnClickListener {
            if (binding.edtBaseUrl.text.isNotEmpty() && binding.edtAppId.text.isNotEmpty()) {
                callMobileSettingsAPI()
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnGetAuthCode.setOnClickListener {
//            callAccessToken()
            callAuthCode()
        }
    }

    fun callMobileSettingsAPI() {
        CFSDKCall.getVersionConfig(
            binding.edtBaseUrl.text.toString(),
            binding.edtAppId.text.toString(),
            object :
                CFSDKResponseCallback<VersionConfig> {
                override fun onSuccess(versionConfig: VersionConfig) {
                    Toast.makeText(
                        this@MainActivity,
                        "Minimum version is ${versionConfig?.requiredUpdate?.minimumVersion} and  required update is = ${versionConfig?.requiredUpdate?.show}",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tvResponse.text =
                        "Minimum version is ${versionConfig?.requiredUpdate?.minimumVersion} and  required update is = ${versionConfig?.requiredUpdate?.show}"
                }

                override fun onFailure(error: Throwable?) {
                    Toast.makeText(
                        this@MainActivity,
                        (error as ChangebankResponse)?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun callAuthCode() {
        CFSDKCall.getAuthCode(
            "9cf152e0-6c7b-462e-b78d-5ee4d37e566a",
            "1.0.1.1",
            "12345678901234567890123456789012",
            object : CFSDKResponseCallback<AuthCodeResponse> {
                override fun onSuccess(response: AuthCodeResponse) {
                    Toast.makeText(this@MainActivity,"Auth Code="+response.getAuthCode(),Toast.LENGTH_SHORT).show()
                    binding.tvResponse.text = "Auth Code=" + response.getAuthCode()
                    callAccessToken(authCode = response.getAuthCode())
                }

                override fun onFailure(error: Throwable?) {
                    Toast.makeText(
                        this@MainActivity,
                        (error as ChangebankResponse)?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun callAccessToken(authCode:String){
        CFSDKCall.getAccessToken(
            authCode,
            "12345678901234567890123456789012",
            object : CFSDKResponseCallback<AccessTokenResponse> {
                override fun onSuccess(response: AccessTokenResponse) {
                    Toast.makeText(this@MainActivity,"Access token="+response.getAccessToken(),Toast.LENGTH_SHORT).show()
                    binding.tvResponse.text = "Access token=" + response.getAccessToken()
                }

                override fun onFailure(error: Throwable?) {
                    Toast.makeText(
                        this@MainActivity,
                        (error as ChangebankResponse)?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}