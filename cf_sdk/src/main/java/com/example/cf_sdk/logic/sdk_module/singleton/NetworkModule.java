package com.example.cf_sdk.logic.sdk_module.singleton;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;


import com.example.cf_sdk.BuildConfig;
import com.example.cf_sdk.logic.changebankapi.network.api.ApiConfig;
import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.exception.NoConnectivityException;
import com.example.cf_sdk.logic.changebanksdk.model.account.Money;
import com.example.cf_sdk.logic.changebanksdk.util.gson.MoneyTypeConverter;
import com.example.cf_sdk.logic.sdk_module.ChangebankApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides network libraries / utils instances.
 */
@Module
public class NetworkModule {
    private static final int TIMEOUT_CONNECT = 40;
    private static final int TIMEOUT_READ = 40;
    private static final int TIMEOUT_WRITE = 40;

    private final String mDeviceId;
    private final String mOsVersion;
    private final ChangebankApp mApplication;

    public NetworkModule(ChangebankApp app) {
        mApplication = app;
        mDeviceId = DeviceUtil.getDeviceFingerPrintID(app);
        mOsVersion = DeviceUtil.getOsVersion();
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Money.class, new MoneyTypeConverter())
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .create();
    }

    @Provides
    @Singleton
    @Named("debugHttpClient")
    OkHttpClient provideOkHttpDebugClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS);

        try {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.protocols(Arrays.asList(Protocol.HTTP_1_1));
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory(), new X509TrustManager() {
                @SuppressLint("TrustAllX509TrustManager") // This is set for our test env.
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                        throws CertificateException {

                }

                @SuppressLint("TrustAllX509TrustManager") // This is set for our test env.
                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                        throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            });

            builder.addInterceptor(new ConnectivityInterceptor(mApplication.getApplicationContext()))
                    .addInterceptor(new HeaderInterceptor());

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(loggingInterceptor);
            }

            builder.hostnameVerifier(new HostnameVerifier() {
                @SuppressLint("BadHostnameVerifier") // This is set for our test env.
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

        } catch (NoSuchAlgorithmException | KeyManagementException ignored) {
        }

        return builder.build();
    }

    @Provides
    @Singleton
    @Named("releaseHttpClient")
    OkHttpClient provideReleaseOkHttpClient() {

        SSLContext sslContext = null;
        TrustManager[] trustManagers = new TrustManager[0];
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            keyStore.setCertificateEntry("AWS1", getCert("aws1.pem"));
            keyStore.setCertificateEntry("AWS2", getCert("aws2.pem"));
            keyStore.setCertificateEntry("AWS3", getCert("aws3.pem"));
            keyStore.setCertificateEntry("AWS4", getCert("aws4.pem"));
            keyStore.setCertificateEntry("AWS5", getCert("aws5.pem"));


            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            trustManagers = trustManagerFactory.getTrustManagers();
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
                .addInterceptor(new ConnectivityInterceptor(mApplication.getApplicationContext()))
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                .build();
    }

    private Certificate getCert(String s) {
        Certificate cert = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(mApplication.getAssets().open(s));
            cert = CertificateFactory.getInstance("X.509").generateCertificate(bufferedInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cert;
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, @Named("debugHttpClient") OkHttpClient okHttpClientDebug, @Named("releaseHttpClient") OkHttpClient okHttpClientRelease) {

        OkHttpClient okHttpClient = null;

        if (BuildConfig.DEBUG) {
            okHttpClient = okHttpClientDebug;
        } else {
            okHttpClient = okHttpClientRelease;
        }

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(ApiConfig.AUTHENTICATION_BASE_ENDPOINT)
                .build();
    }


    @Provides
    @Singleton
    @Named("debugRetrofit")
    Retrofit provideDebugRetrofit(Gson gson, @Named("debugHttpClient") OkHttpClient okHttpClientDebug) {

        OkHttpClient okHttpClient = okHttpClientDebug;

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(ApiConfig.AUTHENTICATION_BASE_ENDPOINT)
                .build();
    }

    public class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {

            Request original = chain.request();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader(Header.APPLICATION_ID, "20cbd0a0-fed3-407e-9be2-ba3825e6faaf");
//                    .addHeader(Header.DEVICE_ID, mDeviceId)
//                    .addHeader(Header.CLIENT_OS, mOsVersion)
//                    .addHeader(Header.CLIENT_IP, DeviceUtil.getIpAddress(true))
//                    .addHeader(Header.ORIGIN, DeviceUtil.getOriginHeader())
//                    .addHeader(Header.PROGRAM_CODE,DeviceUtil.programCode)
//                    .addHeader(Header.CLIENT_IP_V6, DeviceUtil.getIpAddress(false));

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }

    public class ConnectivityInterceptor implements Interceptor {

        private Context mContext;

        ConnectivityInterceptor(Context context) {
            mContext = context;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            if (!NetworkUtil.isOnline(mContext)) {
                throw new NoConnectivityException();
            }

            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        }
    }
}
