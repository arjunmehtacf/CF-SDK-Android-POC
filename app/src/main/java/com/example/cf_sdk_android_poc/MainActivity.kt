package com.example.cf_sdk_android_poc

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cf_sdk.changebankapi.ChangebankSingleObserver
import com.example.cf_sdk.defination.CFSDKCall
import com.example.cf_sdk.defination.CFSDKResponseCallback
import com.example.cf_sdk.defination.response.AccessTokenResponse
import com.example.cf_sdk.defination.response.AccountsApiResponse
import com.example.cf_sdk.defination.response.AuthCodeResponse
import com.example.cf_sdk.defination.response.ChangebankResponse
import com.example.cf_sdk.defination.response.Session
import com.example.cf_sdk.defination.response.UserProfileResponse
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk_android_poc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var applicationId = "f4665ee1-f8c1-4111-baa5-e755a2e83320"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.root)
        binding.edtBaseUrl.setText("https://alb-internal.vtxn-qadev.cf-cloud.net/v1/")
        binding.edtCardHolderId.setText("9cf152e0-6c7b-462e-b78d-5ee4d37e566a")
        binding.btnAuthorize.setOnClickListener {
            if (binding.edtCardHolderId.text.isNotEmpty()) {
                callAuthCode()
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnUserProfile.setOnClickListener {
            if (binding.edtBaseUrl.text.isNotEmpty() && binding.edtCardHolderId.text.isNotEmpty()) {
                callGetUserProfileData()
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnConfigureSDK.setOnClickListener {
            if (binding.edtAuthCode.text.isNotEmpty()) {
                callAccessToken(binding.edtAuthCode.text.toString())
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnCardAccountList.setOnClickListener {
            if (binding.edtAuthCode.text.isNotEmpty()) {
                callGetAccountsData()
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun callGetUserProfile() {
        CFSDKCall.getVersionConfig(
            binding.edtBaseUrl.text.toString(),
            applicationId,
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
            binding.edtCardHolderId.text.toString(),
            "1.0.1.1",
            "12345678901234567890123456789012",
            object : CFSDKResponseCallback<AuthCodeResponse> {
                override fun onSuccess(response: AuthCodeResponse) {
                    Toast.makeText(
                        this@MainActivity,
                        "Auth Code=" + response.getAuthCode(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tvResponse.text = "Auth Code=" + response.getAuthCode()
                    binding.edtAuthCode.visibility = View.VISIBLE
                    binding.btnConfigureSDK.visibility = View.VISIBLE
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

    fun callAccessToken(authCode: String) {
        CFSDKCall.getAccessToken(
            authCode,
            "12345678901234567890123456789012",
            object : CFSDKResponseCallback<Session> {
                override fun onSuccess(response: Session) {
                    Toast.makeText(
                        this@MainActivity,
                        "Access token=" + response.token,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tvResponse.text = "SDK Configured successfully."
                    binding.btnUserProfile.visibility = View.VISIBLE
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

    fun callGetUserProfileData() {
        CFSDKCall.getUserProfileInfo(object : CFSDKResponseCallback<UserProfileResponse> {
            override fun onSuccess(response: UserProfileResponse) {
                binding.tvResponse.text = response.toRawString()
            }

            override fun onFailure(var1: Throwable?) {

            }
        })
    }

    fun callGetAccountsData() {
        CFSDKCall.getAccountsData(object : CFSDKResponseCallback<AccountsApiResponse> {
            override fun onSuccess(response: AccountsApiResponse) {
                binding.tvResponse.text = "Response \n" + response.toRawString()
                for (account in response.accounts) {
                    binding.tvResponse.append(account.toString())
                }
            }

            override fun onFailure(var1: Throwable?) {

            }
        })
    }
}