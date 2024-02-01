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
    lateinit var customerNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding.root)
        binding.edtBaseUrl.setText("https://alb-internal.vtxn-qadev.cf-cloud.net/v1/")
        binding.edtCardHolderId.setText("9cf152e0-6c7b-462e-b78d-5ee4d37e566a")
        binding.btnAuthorize.setOnClickListener {
            if (binding.edtCardHolderId.text?.isNotEmpty() == true) {
                callAuthCode()
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnUserProfile.setOnClickListener {
            if (binding.edtBaseUrl.text?.isNotEmpty() == true && binding.edtCardHolderId.text?.isNotEmpty() == true) {
                callGetUserProfileData()
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnConfigureSDK.setOnClickListener {
            if (binding.edtAuthCode.text?.isNotEmpty() == true) {
                callAccessToken(binding.edtAuthCode.text.toString())
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnCardAccountList.setOnClickListener {
            if (binding.edtAuthCode.text?.isNotEmpty() == true) {
                callGetAccountsData(customerNumber)
            } else {
                Toast.makeText(this@MainActivity, "Please provide valid input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun callAuthCode() {
        CFSDKCall.getAuthCode(
            binding.edtBaseUrl.text.toString(),
            binding.edtCardHolderId.text.toString(),
            "1.0.11",
            object : CFSDKResponseCallback<AuthCodeResponse> {
                override fun onSuccess(response: AuthCodeResponse) {
                    Toast.makeText(
                        this@MainActivity,
                        "Auth Code=" + response.getAuthCode(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.edtAuthCode.setText(response.getAuthCode())
                    binding.inputAuthCode.visibility = View.VISIBLE
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
            object : CFSDKResponseCallback<Session> {
                override fun onSuccess(response: Session) {
                    binding.tvResponse.text = "SDK Configured successfully."
                    binding.btnUserProfile.visibility = View.VISIBLE
                }

                override fun onFailure(error: Throwable?) {
                    binding.inputAuthCode.visibility = View.GONE
                    binding.btnConfigureSDK.visibility = View.GONE
                    binding.btnUserProfile.visibility = View.GONE
                    binding.btnCardAccountList.visibility = View.GONE
                    binding.tvResponse.text = ""
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
                customerNumber = response.customerNumber
                binding.tvResponse.text = response.toRawString()
                binding.btnCardAccountList.visibility = View.VISIBLE
            }

            override fun onFailure(var1: Throwable?) {

            }
        })
    }

    fun callGetAccountsData(customerNumber: String) {
        CFSDKCall.getAccountsData(customerNumber,
            object : CFSDKResponseCallback<AccountsApiResponse> {
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