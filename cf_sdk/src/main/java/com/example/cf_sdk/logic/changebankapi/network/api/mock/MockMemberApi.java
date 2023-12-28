package com.example.cf_sdk.logic.changebankapi.network.api.mock;

import com.example.cf_sdk.logic.changebankapi.network.api.MemberApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.model.Features;
import com.example.cf_sdk.logic.changebanksdk.model.Role;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.ProductDetailsReponseItem;
import com.example.cf_sdk.logic.changebanksdk.model.member.ActivateCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.Address;
import com.example.cf_sdk.logic.changebanksdk.model.member.CardDetailResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.DocumentApiResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.ManualVerification;
import com.example.cf_sdk.logic.changebanksdk.model.member.MemberDetails;
import com.example.cf_sdk.logic.changebanksdk.model.member.RSAPublicKeyResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.RegistrationStatus;
import com.example.cf_sdk.logic.changebanksdk.model.member.UploadProfilePictureApiResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.UserProfileResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.version.Android;
import com.example.cf_sdk.logic.changebanksdk.model.member.version.RequiredUpdate;
import com.example.cf_sdk.logic.changebanksdk.model.member.version.VersionConfig;
import com.example.cf_sdk.logic.changebanksdk.model.oow.OowQuestions;
import com.example.cf_sdk.logic.changebanksdk.model.oow.Question;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CheckDepositParam;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ActivateCardParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddConfidentialParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddMemberInfoAndAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CreateBatchListMemberParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CreateMemberParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ForgotPasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ForgotUsernameParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.IdscanParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.LogParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ResetPasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.SendPhoneCodeParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.SetESignAgreementAcceptedParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UpdateEmailAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UploadDocumentsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.VerifyOutOfWalletParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.VerifyPhoneCodeParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.validation.EmailValidationParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.validation.PhoneValidationParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.response.DocumentUploadResponse;
import com.example.cf_sdk.logic.changebanksdk.response.IdscanResponse;
import com.example.cf_sdk.logic.changebanksdk.response.VerifyOowResponse;

import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Response;

/**
 *
 * Mock implementation of {@link MemberApi}.
 */

public class MockMemberApi implements MemberApi {
    private static final String TAG = MockMemberApi.class.getSimpleName();
    private final Logger mLogger;

    public MockMemberApi(Logger logger) {
        mLogger = logger;
    }

    @Override
    public Single<ChangebankResponse> sendPhoneCode(
            Map<String, String> headers,
            final SendPhoneCodeParameters sendPhoneCodeParameters) {

        return null;
    }

    @Override
    public Completable verifyPhoneCode(
            Map<String, String> headers,
            final VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setMessage("Phone code sent");
        response.setHttpCode(200);

        return Completable.complete()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "verifyPhoneCode onSubscribe: verifyPhoneCodeParameters["
                                + verifyPhoneCodeParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mLogger.debug(TAG, "verifyPhoneCode onComplete");
                    }
                });
    }

    @Override
    public Single<Session> createMember(
            Map<String, String> headers,
            final CreateMemberParameters createMemberParameters) {
        return Single.just(createMemberParameters)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "createMember onSubscribe: createMemberParameters["
                                + createMemberParameters + "]");
                    }
                })
                .map(new Function<CreateMemberParameters, Session>() {
                    @Override
                    public Session apply(CreateMemberParameters createMemberParameters) throws Exception {
                        return Session.create("abc123abc123")
                                .withMemberId(1L)
                                .withNeedsTwoFactor(false)
                                .withRole(Role.USER)
                                .withFeatures(Features.createWithAllFeatures())
                                .withTokenExpirationDate(Long.MAX_VALUE);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        mLogger.debug(TAG, "createMember doOnSuccess: session[" + session + "]");
                    }
                });
    }

    @Override
    public Completable addAddress(
            Map<String, String> headers,
            String memberId,
            final AddAddressParameters addAddressParameters) {
        return Completable.complete()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "addAddress onSubscribe: " +
                                "addAddressParameters[" + addAddressParameters
                                + "], memberId[" + memberId + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mLogger.debug(TAG, "addAddress onNext");
                    }
                });
    }

    @Override
    public Single<MemberDetails> getAcountToAcount(Map<String, String> headers, String accountId, String adminNumber, String last$Digit) {
        return null;
    }


    @Override
    public Single<MemberDetails> checkDeposit(Map<String, String> headers, long memberId, CheckDepositParam checkDepositParam) {
        return null;
    }

    @Override
    public Single<MemberDetails> updateMember(Map<String, String> headers, final long memberId, final AddMemberInfoAndAddressParameters addMemberInfoAndAddressParameters) {
        return Single.just(String.valueOf(memberId))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "memberDetails onSubscribe: memberId[" + memberId + "]");
                    }
                })
                .map(new Function<String, MemberDetails>() {
                    @Override
                    public MemberDetails apply(String id) throws Exception {
                        return MemberDetails.create(id)
                                .withAddress(Address.create(
                                        "6922 Hollywood Blvd.",
                                        "Suite 922",
                                        "Los Angeles",
                                        "90034",
                                        "CA"))
                                .withMemberCreatedDate(DateTime.now()
                                        .minusYears(3)
                                        .toString("yyyy-MM-dd'T'HH:mm:ss.SSS"))
                                .withDateOfBirth("1989-02-06")
                                .withEmail("changebank@changebank.me")
                                .withFirstName("Change")
                                .withLastName("Bank")
                                .withUsername("changebank")
                                .withPhone("5553451234")
                                .withRegistrationStatus(RegistrationStatus.Create(ManualVerification.NOT_REQUIRED, true))
                                .withProfilePicture("pictureId12");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<MemberDetails>() {
                    @Override
                    public void accept(MemberDetails memberDetails) throws Exception {
                        mLogger.debug(TAG, "memberDetails onNext: memberDetails["
                                + memberDetails + "]");
                    }
                });
    }

    @Override
    public Single<OowQuestions> addConfidential(
            Map<String, String> headers,
            final long memberId,
            final AddConfidentialParameters addConfidentialParameters) {
        return Observable.range(1, 4)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "addConfidential onSubscribe: " +
                                "addConfidentialParameters[" + addConfidentialParameters +
                                "], memberId[" + memberId + "]");
                    }
                })
                .map(new Function<Integer, Question>() {
                    @Override
                    public Question apply(Integer integer) throws Exception {
                        return Question.create(
                                integer.toString(),
                                "Question " + integer,
                                Arrays.asList("A", "B", "C", "D", "E"));
                    }
                })
                .toList()
                .map(new Function<List<Question>, OowQuestions>() {
                    @Override
                    public OowQuestions apply(List<Question> questions) throws Exception {
                        return OowQuestions.create(new Random().nextLong())
                                .withQuestions(questions);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<OowQuestions>() {
                    @Override
                    public void accept(OowQuestions oowQuestions) throws Exception {
                        mLogger.debug(TAG, "addConfidential onNext: oowQuestions["
                                + oowQuestions + "]");
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> phoneAvailability(
            Map<String, String> headers,
            PhoneValidationParameters phoneValidationParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(404);
        response.setMessage("Phone not found");

        return Single.<ChangebankResponse>error(response)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "phoneAvailability onSubscribe: phone[" + phoneValidationParameters.getPhone() + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse changebankResponse) throws Exception {
                        mLogger.debug(TAG, "phoneAvailability onNext: doOnSuccess[" + changebankResponse + "]");
                    }
                });
    }


    @Override
    public Single<ChangebankResponse> emailAvailability(
            Map<String, String> headers,
            EmailValidationParameters checkEmailParameter) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(404);
        response.setMessage("Username not found");

        return Single.<ChangebankResponse>error(response)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "emailAvailability onSubscribe: email[" + checkEmailParameter.getEmail() + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mLogger.debug(TAG, "emailAvailability onNext: response[" + response + "]");
                    }
                });
    }

    @Override
    public Single<MemberDetails> memberDetails(
            Map<String, String> headers,
            final String memberId) {
        return Single.just(memberId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "memberDetails onSubscribe: memberId[" + memberId + "]");
                    }
                })
                .map(new Function<String, MemberDetails>() {
                    @Override
                    public MemberDetails apply(String id) throws Exception {
                        return MemberDetails.create(id)
                                .withAddress(Address.create(
                                        "6922 Hollywood Blvd.",
                                        "Suite 922",
                                        "Los Angeles",
                                        "90034",
                                        "CA"))
                                .withMemberCreatedDate(DateTime.now()
                                        .minusYears(3)
                                        .toString("yyyy-MM-dd'T'HH:mm:ss.SSS"))
                                .withDateOfBirth("1989-02-06")
                                .withEmail("changebank@changebank.me")
                                .withFirstName("Change")
                                .withLastName("Bank")
                                .withUsername("changebank")
                                .withPhone("5553451234")
                                .withRegistrationStatus(RegistrationStatus.Create(ManualVerification.NOT_REQUIRED, true, false))
                                .withProfilePicture("pictureId12");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<MemberDetails>() {
                    @Override
                    public void accept(MemberDetails memberDetails) throws Exception {
                        mLogger.debug(TAG, "memberDetails onNext: memberDetails["
                                + memberDetails + "]");
                    }
                });
    }

    @Override
    public Single<VerifyOowResponse> verifyOowQuestions(
            Map<String, String> headers,
            final long memberId,
            final VerifyOutOfWalletParameters verifyOutOfWalletParameters) {
        return Single.just(verifyOutOfWalletParameters)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "verifyOowQuestions onSubscribe: " +
                                "memberId[" + memberId + "], " +
                                "verifyOutOfWalletParameters[" + verifyOutOfWalletParameters + "]");
                    }
                })
                .map(new Function<VerifyOutOfWalletParameters, VerifyOowResponse>() {
                    @Override
                    public VerifyOowResponse apply(VerifyOutOfWalletParameters verifyOutOfWalletParameters) throws Exception {
                        return VerifyOowResponse.create("ok");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<VerifyOowResponse>() {
                    @Override
                    public void accept(VerifyOowResponse verifyOowResponse) throws Exception {
                        mLogger.debug(TAG, "verifyOowQuestions onNext: " +
                                "verifyOowResponse[" + verifyOowResponse + "]");
                    }
                });
    }
    @Override
    public Single<DocumentUploadResponse> uploadDocuments(Map<String, String> headers, final long memberId, final UploadDocumentsParameters uploadDocuments) {
                 return Single.just(uploadDocuments)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "verifyOowQuestions onSubscribe: " +
                                "memberId[" + memberId + "], " +
                                "verifyOutOfWalletParameters[" + uploadDocuments + "]");
                    }
                })
                .map(new Function<UploadDocumentsParameters, DocumentUploadResponse>() {
                    @Override
                    public DocumentUploadResponse apply(UploadDocumentsParameters uploadDocumentsParameters) throws Exception {
                        return DocumentUploadResponse.create();
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<DocumentUploadResponse>() {
                    @Override
                    public void accept(DocumentUploadResponse verifyOowResponse) throws Exception {
                        mLogger.debug(TAG, "verifyOowQuestions onNext: " +
                                "verifyOowResponse[" + verifyOowResponse + "]");
                    }
                });

    }

    @Override
    public Single<OowQuestions> refreshOowQuestions(
            Map<String, String> headers,
            final long memberId) {
        return Observable.range(1, 4)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "refreshOowQuestions onSubscribe: memberId[" + memberId + "]");
                    }
                })
                .map(new Function<Integer, Question>() {
                    @Override
                    public Question apply(Integer integer) throws Exception {
                        return Question.create(
                                integer.toString(),
                                "Question " + integer,
                                Arrays.asList("A", "B", "C", "D", "E"));
                    }
                })
                .toList()
                .map(new Function<List<Question>, OowQuestions>() {
                    @Override
                    public OowQuestions apply(List<Question> questions) throws Exception {
                        return OowQuestions.create(new Random().nextLong())
                                .withQuestions(questions);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<OowQuestions>() {
                    @Override
                    public void accept(OowQuestions oowQuestions) throws Exception {
                        mLogger.debug(TAG, "refreshOowQuestions onNext: " +
                                "oowQuestions[" + oowQuestions + "]");
                    }
                });
    }

    @Override
    public Single<Response<ResponseBody>> getAgreement(Map<String, String> headers,String documentName) {
        String file = "res/raw/test.pdf"; // res/raw/test.txt also work.
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);

        Buffer bf = null;
        try {
            bf = new Buffer().readFrom(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Single.just(Response.success(
                ResponseBody.create(MediaType.parse("application/pdf"),
                        bf.size(), bf)));
    }

    @Override
    public Single<ChangebankResponse> setESignAgreementAsRead(
            Map<String, String> headers,
            long memberId,
            SetESignAgreementAcceptedParameters setESignAgreementAcceptedParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Single.just(response);
    }

    @Override
    public Single<ChangebankResponse> updateEmailAddress(
            Map<String, String> headers,
            UpdateEmailAddressParameters updateEmailAddressParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Single.just(response);
    }

    @Override
    public Single<Session> forgotPassword(
            Map<String, String> headers,
            final ForgotPasswordParameters forgotPasswordParameters) {
        return Single
                .fromCallable(new Callable<Session>() {
                    @Override
                    public Session call() throws Exception {
                        return Session.create("abc123abc123")
                                .withMemberId(1L)
                                .withNeedsTwoFactor(false)
                                .withRole(Role.USER)
                                .withFeatures(Features.createWithAllFeatures())
                                .withTokenExpirationDate(Long.MAX_VALUE);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "forgotPassword onSubscribe: forgotPasswordParameters[" + forgotPasswordParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        mLogger.debug(TAG, "forgotPassword onNext: session[" + session + "]");
                    }
                });
    }

    @Override
    public Completable forgotUsername(Map<String, String> headers, ForgotUsernameParameters forgotUsernameParameters, String appId) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Completable.complete();
    }

    @Override
    public Single<ChangebankResponse> resetPassword(
            Map<String, String> headers,
            ResetPasswordParameters resetPasswordParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Single.just(response);
    }

    @Override
    public Single<IdscanResponse> submitIdscan(
            Map<String, String> headers,
            final long memberId,
            final IdscanParameters idscanParameters) {
        return Single
                .fromCallable(new Callable<IdscanResponse>() {
                    @Override
                    public IdscanResponse call() throws Exception {
                        Address address = Address.create(
                                "2133 Whole Blvd.",
                                "Suite 111",
                                "Los Angeles",
                                "90067",
                                "MI");
                        return IdscanResponse.create("Change", "Bank", "2000-09-26", address);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "submitIdscan onSubscribe: " +
                                "idscanParameters[" + idscanParameters + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<IdscanResponse>() {
                    @Override
                    public void accept(IdscanResponse idscanResponse) throws Exception {
                        mLogger.debug(TAG, "submitIdscan onNext: " +
                                "idscanResponse[" + idscanResponse + "]");
                    }
                });
    }

    @Override
    public Single<UploadProfilePictureApiResponse> uploadProfilePicture(Map<String, String> headers, RequestBody addProfilePictureParameters) {
        return Single.just(UploadProfilePictureApiResponse.create("681d740d-bc1f-428c-bb89-cd92deab7e09"));
    }

    @Override
    public Single<ChangebankResponse> updateMemberPhone(Map<String, String> headers, VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Single.just(response);
    }

    @Override
    public Completable deleteProfilePicture(Map<String, String> headers) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Completable.complete();
    }

    @Override
    public Single<DocumentApiResponse> getDocumentById(Map<String, String> headers, String documentId, long memberId) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(400);
        response.setMessage("Fix Mock");
        return Single.error(response);
    }

    @Override
    public Single<Response<ResponseBody>> getDocumentImageById(Map<String, String> headers, String documentId, long memberId) {
        String file = "res/raw/profile.png";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);

        Buffer bf = null;
        try {
            bf = new Buffer().readFrom(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Single.just(Response.success(
                ResponseBody.create(MediaType.parse("image/png"),
                        bf.size(), bf)));
    }

    @Override
    public Single<Response<ResponseBody>> getProfilePicture(Map<String, String> headers) {
        return null;
    }

    @Override
    public Single<ChangebankResponse> log(Map<String, String> headers, LogParameters logParameters) {
        ChangebankResponse response = new ChangebankResponse();
        response.setHttpCode(200);
        response.setMessage("Success");
        return Single.just(response);
    }

    @Override
    public Single<VersionConfig> getVersionConfig(String os, String applicationId,String url) {
        VersionConfig versionConfig = new VersionConfig();
        Android android = new Android();
        RequiredUpdate requiredUpdate = new RequiredUpdate();
        requiredUpdate.setShow(false);
        android.setRequiredUpdate(requiredUpdate);
        versionConfig.setRequiredUpdate(requiredUpdate);

        return Single.just(versionConfig);
    }

    @Override
    public Single<UserProfileResponse> getUserProfile(Map<String, String> headers) {
        return null;
    }

    @Override
    public Single<CardDetailResponse> getCardDetails(Map<String, String> headers, String cardNumber, boolean includeCVV2) {
        return null;
    }

    @Override
    public Single<List<ProductDetailsReponseItem>> getSubCardRelation(Map<String, String> headers, String productCode) {
        return null;
    }

    @Override
    public Single<RSAPublicKeyResponse> getRSAPublicKey(Map<String, String> headers) {
        return null;
    }

    @Override
    public Single<ActivateCardResponse> getAccountActivate(Map<String, String> headers, String cardNumber, ActivateCardParameter activateCardParameter) {
        return null;
    }

    @Override
    public Completable batchListConfirm(Map<String, String> headers, final String adminNumber, String dob, String last4Ssn) {
        return Completable.complete()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG,
                                "batchListConfirm[" + adminNumber + "]");
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mLogger.debug(TAG, "batchListConfirm onComplete: ");
                    }
                });
    }

    @Override
    public Completable getAdminNumberAvailability(Map<String, String> headers, final CreateBatchListMemberParameters createBatchListMemberParameters) {
      return Completable.complete();

//        return Single.just(AdminNumberResponse.Create(true));
    }

    @Override
    public Single<Session> batchListCreateMember(Map<String, String> headers, final CreateBatchListMemberParameters createBatchListMemberParameters) {
        return Single.just(createBatchListMemberParameters)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLogger.debug(TAG, "createMember onSubscribe: createMemberParameters["
                                + createBatchListMemberParameters + "]");
                    }
                })
                .map(new Function<CreateBatchListMemberParameters, Session>() {
                    @Override
                    public Session apply(CreateBatchListMemberParameters createMemberParameters) throws Exception {
                        return Session.create("abc123abc123")
                                .withMemberId(1L)
                                .withNeedsTwoFactor(false)
                                .withRole(Role.USER)
                                .withFeatures(Features.createWithAllFeatures())
                                .withTokenExpirationDate(Long.MAX_VALUE);
                    }
                })
                .delay(1, TimeUnit.SECONDS)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        mLogger.debug(TAG, "createMember doOnSuccess: session[" + session + "]");
                    }
                });
    }
}
