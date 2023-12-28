package com.example.cf_sdk.logic.changebankapi.network;

import com.example.cf_sdk.logic.changebankapi.network.api.ApiConfig;
import com.example.cf_sdk.logic.changebanksdk.model.account.Money;
import com.example.cf_sdk.logic.changebanksdk.util.gson.MoneyTypeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Handles creation of Retrofit instance.
 */

public class RetrofitFactory {
    public static Retrofit createDebugRetrofit() {
        return new RetrofitFactory().getRetrofit();
//        return new RetrofitFactory().getDebugRetrofit();
    }

    public static Retrofit createReleaseRetrofit() {
        return new RetrofitFactory().getRetrofit();
    }

    private Retrofit getDebugRetrofit() {
        Retrofit retrofit;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new X509TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

            }}, new SecureRandom());

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//            OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                    .sslSocketFactory(sslContext.getSocketFactory())
//                    .addInterceptor(loggingInterceptor)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS);

            OkHttpClient builder = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).build();


//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Money.class, new MoneyTypeConverter())
                    .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                    .create();

            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(ApiConfig.AUTHENTICATION_BASE_ENDPOINT)
                    .client(builder)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            retrofit = getRetrofit();
        } catch (KeyManagementException e) {
            retrofit = getRetrofit();
        }

        return retrofit;
    }

    private Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Money.class, new MoneyTypeConverter())
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .create();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiConfig.AUTHENTICATION_BASE_ENDPOINT)
                .build();
    }
}
