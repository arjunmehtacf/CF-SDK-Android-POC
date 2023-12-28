package com.example.cf_sdk_android_poc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cf_sdk.defifination.CFApiCall
import com.example.cf_sdk.logic.CFSDKResponseCallback
import com.example.cf_sdk.logic.changebanksdk.model.member.version.VersionConfig
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse
import com.example.cf_sdk_android_poc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.root)

        binding.edtBaseUrl.setText("https://alb-internal.vtxn-qadev.cf-cloud.net/v1/")
        binding.edtAppId.setText("20cbd0a0-fed3-407e-9be2-ba3825e6faaf")
        val cfApiCall = CFApiCall.getInstance(application)
        binding.btnGetSettings.setOnClickListener {
            if (binding.edtBaseUrl.text.toString().length > 0 && binding.edtBaseUrl.text.isNotEmpty()) {
                cfApiCall.callGetSettings(
                    binding.edtAppId.text.toString(),
                    binding.edtBaseUrl.text.toString().trim(),
                    object : CFSDKResponseCallback<VersionConfig> {
                        override fun onSuccess(versionConfig: VersionConfig?) {
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
                                (error as ChangebankResponse)?.getmMessage(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

            } else {
                Toast.makeText(this@MainActivity, "Please enter base url", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        binding.btnGoToLogin.setOnClickListener {
            if (binding.edtBaseUrl.text.toString().length > 0 && binding.edtBaseUrl.text.isNotEmpty()) {
                val intent = Intent(this@MainActivity, LoginScreen::class.java)
                intent.putExtra("base_url", binding.edtBaseUrl.text.toString())
                intent.putExtra("app_id",binding.edtAppId.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Please enter base url", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}