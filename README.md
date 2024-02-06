# Change Financial SDK POC Integration Guide
Change Financial SDK POC is a sample SDK in which it have predefined functions which provide the data from change financial's CMS.

# Table of Contents
1. [Getting Started](#getting-started)

2. [Integration Guides](#integration-guides)

3. [Code Examples](#code-examples)
4. [SDK Functions](#sdk-functions)
   * [User Profile](#user-profile)
   * [Get Accounts](#get-accounts)

- - - -

# Getting Started

## Release Notes
* Auth code and Access Token functions are added for external app users.

### **Version 1.0.11**

## System Requirements

| Technology               | Version |
| :--------------------    |:--------|
| Android Gradle Plugin    | 7.4.0   |
| Gradle                   | 8.0     |
| Kotlin                   | 1.8.20  |
| JDK                      | 17      |
| Android min API level    | 28      |
| Android target API level | 34      |


- - - - 

# Integration Guides

To integrate the Change Financials SDK POC into any other project please follow the below steps:

1. Create a Personal Access Token (PAT) on https://www.github.com.  
   Please follow [this guide](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token#creating-a-token) to create a PAT and make sure to select the `read:packages` scope.
2. Add the following to the **project level** `settings.gradle`:
    ```groovy
    dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    
        maven {
            url = uri('https://maven.pkg.github.com/arjunmehtacf/CF-SDK-Android-POC')
            credentials {
                username = 'your-github-username'
                password = 'personal-access-token' // created in Step 1.
            }
        }
   }
    ```
3. Add the below dependency in your app level `build.gradle` file in dependency section.
  ```groovy
dependencies {
      implementation 'com.changefinancial:sdkpoc:1.0.11'
}
```
4. Add the below supporting dependencies to the app level `build.gradle` file in the dependency section ( It is optional if you already have in your project).
```groovy
dependencies {
    implementation "com.google.code.gson:gson:2.8.1"
    implementation "com.squareup.okhttp3:logging-interceptor:3.4.1"
    implementation 'com.squareup.okhttp3:okhttp:3.14.9'

    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    implementation "com.squareup.okhttp3:logging-interceptor:$retrofitLoggingInterceptorVersion"
    implementation 'com.squareup.okhttp3:okhttp:3.14.9'

    implementation "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    implementation "io.reactivex.rxjava2:rxandroid:$rxAndroidVersion"
}
```
- - - -

# Code Examples

Below code examples demonstrates to get the mobile settings data from the SDK POC.

```groovy
CFSDKCall.getVersionConfig(
            "CF BASE URL",
            "CF APPLICATION ID",
            object :
                CFSDKResponseCallback<VersionConfig> {
                override fun onSuccess(versionConfig: VersionConfig) {
                    // Use versionConfig to get mobile settings data.
                }

                override fun onFailure(error: Throwable?) {
                    // Show errors message if any using (error as ChangebankResponse)?.message
                }
            })
```
- - - -

# SDK Functions
## User Profile
```groovy
CFSDKCall.getUserProfileInfo(object : CFSDKResponseCallback<UserProfileResponse> {
            override fun onSuccess(profileData: UserProfileResponse) {
                // Use profileData to get user profile data
            }

            override fun onFailure(var1: Throwable?) {
               // Show errors message if any using (error as ChangebankResponse)?.message
            }
        })
```

## Get Accounts
To get list of accounts you need to pass Customer Number which you get from User Profile response.
```groovy
CFSDKCall.getAccountsData(customerNumber,
            object : CFSDKResponseCallback<AccountsApiResponse> {
                override fun onSuccess(response: AccountsApiResponse) {
                    // Use response to get user account list data
                }

                override fun onFailure(var1: Throwable?) {
                    // Show errors message if any using (error as ChangebankResponse)?.message
                }
            })
```

