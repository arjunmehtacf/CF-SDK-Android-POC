package com.example.cf_sdk.changebankapi.network.api;



import com.example.cf_sdk.changebankapi.Endpoints;
import com.example.cf_sdk.defination.response.Session;
import com.example.cf_sdk.changebankapi.model.account.ProductDetailsReponseItem;
import com.example.cf_sdk.changebankapi.model.member.ActivateCardResponse;
import com.example.cf_sdk.changebankapi.model.member.CardDetailResponse;
import com.example.cf_sdk.changebankapi.model.member.DocumentApiResponse;
import com.example.cf_sdk.changebankapi.model.member.MemberDetails;
import com.example.cf_sdk.changebankapi.model.member.RSAPublicKeyResponse;
import com.example.cf_sdk.changebankapi.model.member.UploadProfilePictureApiResponse;
import com.example.cf_sdk.defination.response.UserProfileResponse;
import com.example.cf_sdk.defination.request.AccessTokenParameter;
import com.example.cf_sdk.defination.request.AuthCodeParameter;
import com.example.cf_sdk.defination.response.AuthCodeResponse;
import com.example.cf_sdk.defination.response.version.VersionConfig;
import com.example.cf_sdk.changebankapi.model.oow.OowQuestions;
import com.example.cf_sdk.changebankapi.parameter.account.CheckDepositParam;
import com.example.cf_sdk.changebankapi.parameter.member.ActivateCardParameter;
import com.example.cf_sdk.changebankapi.parameter.member.AddAddressParameters;
import com.example.cf_sdk.changebankapi.parameter.member.AddConfidentialParameters;
import com.example.cf_sdk.changebankapi.parameter.member.AddMemberInfoAndAddressParameters;
import com.example.cf_sdk.changebankapi.parameter.member.CreateBatchListMemberParameters;
import com.example.cf_sdk.changebankapi.parameter.member.CreateMemberParameters;
import com.example.cf_sdk.changebankapi.parameter.member.ForgotPasswordParameters;
import com.example.cf_sdk.changebankapi.parameter.member.ForgotUsernameParameters;
import com.example.cf_sdk.changebankapi.parameter.member.IdscanParameters;
import com.example.cf_sdk.changebankapi.parameter.member.LogParameters;
import com.example.cf_sdk.changebankapi.parameter.member.ResetPasswordParameters;
import com.example.cf_sdk.changebankapi.parameter.member.SendPhoneCodeParameters;
import com.example.cf_sdk.changebankapi.parameter.member.SetESignAgreementAcceptedParameters;
import com.example.cf_sdk.changebankapi.parameter.member.UpdateEmailAddressParameters;
import com.example.cf_sdk.changebankapi.parameter.member.UploadDocumentsParameters;
import com.example.cf_sdk.changebankapi.parameter.member.VerifyOutOfWalletParameters;
import com.example.cf_sdk.changebankapi.parameter.member.VerifyPhoneCodeParameters;
import com.example.cf_sdk.changebankapi.parameter.validation.EmailValidationParameters;
import com.example.cf_sdk.changebankapi.parameter.validation.PhoneValidationParameters;
import com.example.cf_sdk.defination.response.ChangebankResponse;
import com.example.cf_sdk.changebankapi.response.DocumentUploadResponse;
import com.example.cf_sdk.changebankapi.response.IdscanResponse;
import com.example.cf_sdk.changebankapi.response.VerifyOowResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 *
 * Retrofit interface to connect with Member API.
 */

public interface MemberApi {

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.SEND_CODE_PHONE)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> sendPhoneCode(
            @HeaderMap Map<String, String> headers,
            @Body SendPhoneCodeParameters sendPhoneCodeParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.VERIFY_PHONE_CODE)
    @Headers("Content-Type: application/json")
    Completable verifyPhoneCode(
            @HeaderMap Map<String, String> headers,
            @Body VerifyPhoneCodeParameters verifyPhoneCodeParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.CREATE_MEMBER)
    @Headers("Content-Type: application/json")
    Single<Session> createMember(
            @HeaderMap Map<String, String> headers,
            @Body CreateMemberParameters createMemberParameters);

    @PUT(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.ADD_ADDRESS)
    @Headers("Content-Type: application/json")
    Completable addAddress(
            @HeaderMap Map<String, String> headers,
            @Path("customerNumber") String memberId,
            @Body AddAddressParameters addAddressParameters);

    @PUT(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.UPDATE_MEMBER)
    @Headers("Content-Type: application/json")
    Single<MemberDetails> updateMember(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId,
            @Body AddMemberInfoAndAddressParameters addMemberInfoAndAddressParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.CHECK_DEPOSIT)
    @Headers("Content-Type: application/json")
    Single<MemberDetails> checkDeposit(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId,
            @Body CheckDepositParam checkDepositParam);


    @PUT(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.ADD_CONFIDENTIAL)
    @Headers("Content-Type: application/json")
    Single<OowQuestions> addConfidential(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId,
            @Body AddConfidentialParameters addConfidentialParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.PHONE_AVAILABILITY)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> phoneAvailability(
            @HeaderMap Map<String, String> headers,
            @Body PhoneValidationParameters phoneValidationParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.EMAIL_AVAILABILITY)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> emailAvailability(
            @HeaderMap Map<String, String> headers,
            @Body EmailValidationParameters checkEmailParameter);


    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.MEMBER_DETAILS)
    @Headers("Content-Type: application/json")
    Single<MemberDetails> memberDetails(
            @HeaderMap Map<String, String> headers,
            @Path("customerNumber") String memberId);

    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Account.VALIDATE_ACCOUNT_NUMBER)
    @Headers("Content-Type: application/json")
    Single<MemberDetails> getAcountToAcount(@HeaderMap Map<String, String> headers,
                                         @Query("adminNumber") String adminNumber,
                                         @Query("name") String recName,
                                         @Query("last4phone") String last$Digit);


    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.VERIFY_OOW_QUESTIONS)
    @Headers("Content-Type: application/json")
    Single<VerifyOowResponse> verifyOowQuestions(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId,
            @Body VerifyOutOfWalletParameters verifyOutOfWalletParameters);

    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.REFRESH_OOW_QUESTIONS)
    @Headers("Content-Type: application/json")
    Single<OowQuestions> refreshOowQuestions(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId);

    @Streaming
    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.GET_AGREEMENT)
    Single<Response<ResponseBody>> getAgreement(@HeaderMap Map<String,String> headers,
            @Path("documentId") String documentName);

    @PUT(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.UPDATE_MEMBER)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> setESignAgreementAsRead(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId,
            @Body SetESignAgreementAcceptedParameters setESignAgreementAcceptedParameters);

    @PUT(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.UPDATE_MEMBER)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> updateEmailAddress(
            @HeaderMap Map<String, String> headers,
            @Body UpdateEmailAddressParameters updateEmailAddressParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.FORGOT_PASSWORD)
    @Headers("Content-Type: application/json")
    Single<Session> forgotPassword(
            @HeaderMap Map<String, String> headers,
            @Body ForgotPasswordParameters forgotPasswordParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.FORGOT_USERNAME)
    @Headers("Content-Type: application/json")
    Completable forgotUsername(
            @HeaderMap Map<String, String> headers,
            @Body ForgotUsernameParameters forgotUsernameParameters, @Query("applicationID") String appId);

    @PUT(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.RESET_PASSWORD)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> resetPassword(
            @HeaderMap Map<String, String> headers,
            @Body ResetPasswordParameters resetPasswordParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.SUBMIT_IDSCAN)
    @Headers("Content-Type: application/json")
    Single<IdscanResponse> submitIdscan(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId,
            @Body IdscanParameters idscanParameters);


    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.IDSCAN_VERIFY)
    @Headers("Content-Type: application/json")
    Single<DocumentUploadResponse> uploadDocuments(
            @HeaderMap Map<String, String> headers,
            @Path("memberId") long memberId,
            @Body UploadDocumentsParameters uploadDocuments);



    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.UPLOAD_PROFILE_PICTURE)
    @Headers("Content-Type: image/png")
    Single<UploadProfilePictureApiResponse> uploadProfilePicture(
            @HeaderMap Map<String, String> headers,
            @Body RequestBody body);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.UPDATE_MEMBER_PHONE)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> updateMemberPhone(
            @HeaderMap Map<String, String> headers,
            @Body VerifyPhoneCodeParameters verifyPhoneCodeParameters);

    @DELETE(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.DELETE_PROFILE_PICTURE)
    @Headers("Content-Type: application/json")
    Completable deleteProfilePicture(
            @HeaderMap Map<String, String> headers);


    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.GET_DOCUMENT_BY_ID)
    @Headers("Content-Type: application/json")
    Single<DocumentApiResponse> getDocumentById(
            @HeaderMap Map<String, String> headers,
            @Path("documentId") String documentId,
            @Path("memberId") long memberId);

    @Streaming
    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.GET_DOCUMENT_IMAGE_BY_ID)
    Single<Response<ResponseBody>> getDocumentImageById(
            @HeaderMap Map<String, String> headers,
            @Path("documentId") String documentId,
            @Path("memberId") long memberId);

    @GET(ApiConfig.MEMBER_BASE_ENDPOINT+Endpoints.Member.GET_PROFILE_PICTURE)
    Single<Response<ResponseBody>> getProfilePicture(@HeaderMap Map<String,String> headers);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.LOG)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> log(
            @HeaderMap Map<String, String> headers,
            @Body LogParameters logParameters);



    @GET
    Single<VersionConfig> getVersionConfig(@Url String baseUrl, @Query("os") String OperatingSystem, @Query("applicationID") String applicationId);


    @POST
    @Headers("Content-Type: application/json")
    Single<AuthCodeResponse> getAuthCode(@Url String url,
                                         @HeaderMap Map<String, String> headers,
                                         @Body AuthCodeParameter authCodeParameter);

    @GET
    Single<UserProfileResponse> getUserProfile(@Url String url, @HeaderMap Map<String, String> headers);

    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.GET_CARD_DETAILS)
    Single<CardDetailResponse> getCardDetails(@HeaderMap Map<String, String> headers,
                                              @Path("cardNumber")String cardNumber, @Header("includeCVV2")boolean includeCVV2);

    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Account.SUB_CARD_RELATION)
    Single<List<ProductDetailsReponseItem>> getSubCardRelation(@HeaderMap Map<String, String> headers,
                                                               @Query("productCode")String productCode);

    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.GET_RSA_PUBLIC_KEY)
    Single<RSAPublicKeyResponse> getRSAPublicKey(@HeaderMap Map<String, String> headers);

    @PUT(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.GET_ACCOUNT_ACTIVATE)
    Single<ActivateCardResponse> getAccountActivate(@HeaderMap Map<String, String> headers, @Path("cardNumber")String cardNumber, @Body ActivateCardParameter activateCardParameter);

    @GET(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.BATCH_LIST_CONFIRM)
    Completable batchListConfirm(@HeaderMap Map<String, String> headers,
                                                @Query("adminNumber") String adminNumber,
                                                @Query("dob") String dob,
                                                @Query("last4SSN") String last4Ssn);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.INSTANT_CARD_AVAILABLE)
    Completable getAdminNumberAvailability(@HeaderMap Map<String, String> headers,
                                                           @Body CreateBatchListMemberParameters createBatchListMemberParameters);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Member.BATCH_LIST_CREATE_CREDENTIALS)
    @Headers("Content-Type: application/json")
    Single<Session> batchListCreateMember(
            @HeaderMap Map<String, String> headers,
            @Body CreateBatchListMemberParameters createBatchListMemberParameters);
}
