package com.example.cf_sdk.logic.changebankapi;

import com.example.cf_sdk.logic.changebankapi.network.api.ApiConfig;
import com.example.cf_sdk.logic.changebanksdk.exception.LoginTwoFactorTooManyAttemptsException;
import com.example.cf_sdk.logic.changebanksdk.exception.UnauthorizedSessionException;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.util.NoSuchElementException;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

/**
 *
 * RxAndroid friendly ErrorHandler.
 */

public class ErrorHandler<T> implements ChangebankError<T> {

    private Retrofit mRetrofit;

    public ErrorHandler(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream.onErrorResumeNext(new Function<Throwable, SingleSource<? extends T>>() {
            @Override
            public SingleSource<? extends T> apply(Throwable throwable) throws Exception {
                if (throwable instanceof HttpException) {
                    ResponseBody body = ((HttpException) throwable).response().errorBody();


                    try {
                        Converter<ResponseBody, ChangebankResponse> converter = mRetrofit
                                .responseBodyConverter(ChangebankResponse.class, new Annotation[0]);
                        ChangebankResponse error = converter.convert(body);
                        error.setHttpCode(((HttpException) throwable).code());

                        Throwable mappedError = mapErrorCode(error);

                        return Single.error(mappedError);
                    } catch (IOException | IllegalArgumentException e) {
                        ChangebankResponse error = new ChangebankResponse();
                        error.setHttpCode(((HttpException) throwable).code());
                        error.setMessage(throwable.getMessage());
                        return Single.error(error);
                    }
                } else if (throwable instanceof NoSuchElementException) {
                    try {
                        ChangebankResponse error = new ChangebankResponse();
                        error.setHttpCode(HttpURLConnection.HTTP_NO_CONTENT);
                        error.setMessage(ApiConfig.RESPONSE_CODE_204);
                        return Single.error(error);
                    } catch (Exception e) {

                    }
                }
                return Single.error(throwable);
            }
        });
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream.onErrorResumeNext(new Function<Throwable, CompletableSource>() {
            @Override
            public CompletableSource apply(Throwable throwable) throws Exception {
                if (throwable instanceof HttpException) {
                    ResponseBody body = ((HttpException) throwable).response().errorBody();


                    try {
                        Converter<ResponseBody, ChangebankResponse> converter = mRetrofit
                                .responseBodyConverter(ChangebankResponse.class, new Annotation[0]);
                        ChangebankResponse error = converter.convert(body);
                        error.setHttpCode(((HttpException) throwable).code());

                        Throwable mappedError = mapErrorCode(error);

                        return Completable.error(mappedError);
                    } catch (IOException | IllegalArgumentException e) {
                        ChangebankResponse error = new ChangebankResponse();
                        error.setHttpCode(((HttpException) throwable).code());
                        error.setMessage(throwable.getMessage());
                        return Completable.error(error);
                    }
                }
                return Completable.error(throwable);
            }
        });
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                if (throwable instanceof HttpException) {
                    ResponseBody body = ((HttpException) throwable).response().errorBody();


                    try {
                        Converter<ResponseBody, ChangebankResponse> converter = mRetrofit
                                .responseBodyConverter(ChangebankResponse.class, new Annotation[0]);
                        ChangebankResponse error = converter.convert(body);
                        error.setHttpCode(((HttpException) throwable).code());

                        Throwable mappedError = mapErrorCode(error);

                        return Observable.error(mappedError);
                    } catch (IOException | IllegalArgumentException e) {
                        ChangebankResponse error = new ChangebankResponse();
                        error.setHttpCode(((HttpException) throwable).code());
                        error.setMessage(throwable.getMessage());
                        return Observable.error(error);
                    }
                } else if (throwable instanceof NoSuchElementException) {
                    try {
                        ChangebankResponse error = new ChangebankResponse();
                        error.setHttpCode(HttpURLConnection.HTTP_NO_CONTENT);
                        error.setMessage(ApiConfig.RESPONSE_CODE_204);
                        return Observable.error(error);
                    } catch (Exception e) {

                    }
                }
                return Observable.error(throwable);
            }
        });
    }

    private Throwable mapErrorCode(ChangebankResponse error) {
        Throwable mappedError;
        switch (error.getKey()) {
            case "authentication.code.failure":
                mappedError = new LoginTwoFactorTooManyAttemptsException(error.getMessage());
                break;
            case "UnAuthorized":
            case "authentication.failure":
            case "authentication.invalid.token":
                mappedError = new UnauthorizedSessionException();
                break;
            default:
                mappedError = error;
                break;
        }
        return mappedError;
    }
}
