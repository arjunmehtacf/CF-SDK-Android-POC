package com.example.cf_sdk.changebankapi.network


import com.example.cf_sdk.changebankapi.model.account.Money
import com.example.cf_sdk.changebankapi.network.api.ApiConfig
import com.example.cf_sdk.changebankapi.util.MoneyTypeConverter
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

/**
 *
 * Handles creation of Retrofit instance.
 */
class RetrofitFactory {
    fun createDebugRetrofit(): Retrofit? {
        return RetrofitFactory().getRetrofit()
//        return new RetrofitFactory().getDebugRetrofit();
    }

    fun createReleaseRetrofit(): Retrofit? {
        return RetrofitFactory().getRetrofit()
    }

    private fun getDebugRetrofit(): Retrofit? {
        var retrofit: Retrofit?
        try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, arrayOf<X509TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            }), SecureRandom())
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//            OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                    .sslSocketFactory(sslContext.getSocketFactory())
//                    .addInterceptor(loggingInterceptor)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS);
            val builder = OkHttpClient.Builder()
                .sslSocketFactory(sslContext.socketFactory)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .hostnameVerifier { hostname, session -> true }.build()


//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
            val gson = GsonBuilder()
                .registerTypeAdapter(Money::class.java, MoneyTypeConverter())
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .create()
            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiConfig.AUTHENTICATION_BASE_ENDPOINT)
                .client(builder)
                .build()
        } catch (e: NoSuchAlgorithmException) {
            retrofit = getRetrofit()
        } catch (e: KeyManagementException) {
            retrofit = getRetrofit()
        }
        return retrofit
    }

    public fun getRetrofit(): Retrofit? {
        val gson = GsonBuilder()
            .registerTypeAdapter(Money::class.java, MoneyTypeConverter())
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .create()
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiConfig.AUTHENTICATION_BASE_ENDPOINT)
            .build()
    }
}