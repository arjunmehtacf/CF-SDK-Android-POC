package com.example.cf_sdk.logic.changebankapi.source.remote;

import com.example.cf_sdk.logic.changebankapi.Endpoints;
import com.example.cf_sdk.logic.changebankapi.network.api.MemberApi;
import com.example.cf_sdk.logic.changebanksdk.model.FileResponse;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.ProductDetailsReponseItem;
import com.example.cf_sdk.logic.changebanksdk.model.member.ActivateCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.CardDetailResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.DocumentApiResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.MemberDetails;
import com.example.cf_sdk.logic.changebanksdk.model.member.RSAPublicKeyResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.UploadProfilePictureApiResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.UserProfileResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.version.VersionConfig;
import com.example.cf_sdk.logic.changebanksdk.model.oow.OowQuestions;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CheckDepositParam;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.SubCardRelationParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ActivateCardParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddConfidentialParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddMemberInfoAndAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddProfilePictureParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AgreementParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CardDetailParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CreateBatchListMemberParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CreateMemberParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.DocumentParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ForgotPasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ForgotUsernameParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.IdscanParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.LogParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.MemberDetailParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ProfilePictureParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.RefreshOowParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ResetPasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.SendPhoneCodeParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.SetESignAgreementAcceptedParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.SettingsParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UpdateEmailAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UploadDocumentsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UserProfileParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.VerifyOutOfWalletParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.VerifyPhoneCodeParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.validation.EmailValidationParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.validation.PhoneValidationParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.response.DocumentUploadResponse;
import com.example.cf_sdk.logic.changebanksdk.response.IdscanResponse;
import com.example.cf_sdk.logic.changebanksdk.response.VerifyOowResponse;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.google.common.base.Optional;
import com.google.common.io.BaseEncoding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 *
 * Remote datasource to call Member endpoints.
 */

public class MemberRemoteDatasource implements MemberDatasource {
    private final File mCacheDirectory;
    private MemberApi mMemberApi;

    @Inject
    public MemberRemoteDatasource(MemberApi memberApi, File cacheDirectory) {
        mMemberApi = memberApi;
        mCacheDirectory = cacheDirectory;
    }

    @Override
    public Single<ChangebankResponse> sendPhoneCode(SendPhoneCodeParameters sendPhoneCodeParameters) {
        return mMemberApi.sendPhoneCode(
                sendPhoneCodeParameters.getHeaders(),
                sendPhoneCodeParameters);
    }

    @Override
    public Completable verifyPhoneCode(VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        return mMemberApi.verifyPhoneCode(
                verifyPhoneCodeParameters.getHeaders(),
                verifyPhoneCodeParameters);
    }

    @Override
    public Single<Session> createMember(
            CreateMemberParameters createMemberParameters) {
        return mMemberApi.createMember(createMemberParameters.getHeaders(), createMemberParameters);
    }

    @Override
    public Completable addAddress(AddAddressParameters addAddressParameters) {
        return mMemberApi.addAddress(
                addAddressParameters.getHeaders(),
                addAddressParameters.getMemberId(),
                addAddressParameters);
    }

    @Override
    public Single<MemberDetails> addMemberInfoAndAddress(AddMemberInfoAndAddressParameters addMemberInfoAndAddressParameters) {
        return mMemberApi.updateMember(
                addMemberInfoAndAddressParameters.getHeaders(),
                addMemberInfoAndAddressParameters.getMemberId(),
                addMemberInfoAndAddressParameters);
    }

    @Override
    public Single<Optional<OowQuestions>> addConfidential(AddConfidentialParameters addConfidentialParameters) {
        return mMemberApi.addConfidential(
                addConfidentialParameters.getHeaders(),
                addConfidentialParameters.getMemberId(),
                addConfidentialParameters)
                .map(new Function<OowQuestions, Optional<OowQuestions>>() {
                    @Override
                    public Optional<OowQuestions> apply(OowQuestions oowQuestions) throws Exception {
                        return Optional.of(oowQuestions);
                    }
                });
    }

    @Override
    public Completable saveOowQuestions(OowQuestions oowQuestions) {
        throw new UnsupportedOperationException(
                "MemberRemoteDatasource not support saveOowQuestions");
    }

    @Override
    public Single<ChangebankResponse> phoneAvailability(
            PhoneValidationParameters phoneValidationParameters) {
        return mMemberApi.phoneAvailability(
                phoneValidationParameters.getHeaders(),
                phoneValidationParameters);
    }


    @Override
    public Single<MemberDetails> getCardToCardAccountDetails(CardToCardTransferParameters cardToCardTransferParameters) {
        return mMemberApi.getAcountToAcount(cardToCardTransferParameters.getHeaders(),cardToCardTransferParameters.getmAdminNumber(),cardToCardTransferParameters.getName(),cardToCardTransferParameters.getLast4Phone());
    }


    @Override
    public Single<MemberDetails> addCheckDeposit(CheckDepositParam checkDepositParam) {
        return mMemberApi.checkDeposit(checkDepositParam.getHeaders(),checkDepositParam.getMemberId(),checkDepositParam);
    }

    @Override
    public Single<Optional<MemberDetails>> getMemberDetails(
            MemberDetailParameters stringParameters) {
        return mMemberApi.memberDetails(
                stringParameters.getHeaders(),
                stringParameters.getCustomerNumber())
                .map(new Function<MemberDetails, Optional<MemberDetails>>() {
                    @Override
                    public Optional<MemberDetails> apply(MemberDetails memberDetails) throws Exception {
                        return Optional.of(memberDetails);
                    }
                });
    }

    @Override
    public Single<VerifyOowResponse> verifyOowQuestion(VerifyOutOfWalletParameters verifyOutOfWalletParameters) {
        return mMemberApi.verifyOowQuestions(
                verifyOutOfWalletParameters.getHeaders(),
                verifyOutOfWalletParameters.getMemberId(),
                verifyOutOfWalletParameters);
    }

    @Override
    public Single<OowQuestions> refreshOowQuestions(RefreshOowParameters refreshOowParameters) {
        return mMemberApi.refreshOowQuestions(
                refreshOowParameters.getHeaders(),
                refreshOowParameters.getMemberId());
    }

    @Override
    public Single<File> getAgreement(final AgreementParameters agreementParameters) {
        return mMemberApi.getAgreement(agreementParameters.getHeaders(),agreementParameters.getAgreementType().getAgreementTypeName())
                .flatMap(new Function<Response<ResponseBody>, SingleSource<? extends File>>() {
                    @Override
                    public SingleSource<? extends File> apply(Response<ResponseBody> responseBodyResponse) throws Exception {
                        String fileName = agreementParameters.getAgreementType().getAgreementTypeName();
                        try {
                            File file;
                            file = File.createTempFile(fileName, ".pdf", mCacheDirectory);
                            file.deleteOnExit();
                            BufferedSink sink = Okio.buffer(Okio.sink(file));
                            // you can access body of response
                            ResponseBody responseBody = responseBodyResponse.body();
                            if (responseBody != null) {
                                sink.writeAll(responseBody.source());
                                sink.close();
                            } else {
                                return Single.error(new Exception("Body is null"));
                            }

                            return Single.just(file);
                        } catch (IOException e) {
                            return Single.error(e);
                        }
                    }
                });
    }

    @Override
    public void saveMemberDetails(MemberDetails memberDetails) {
        throw new UnsupportedOperationException(
                "MemberRemoteDatasource not support saveMemberDetails");
    }

    @Override
    public void saveUserProfile(UserProfileResponse userProfileResponse) {
        new UnsupportedOperationException(
                "MemberRemoteDatasource not support saveUserProfile");
    }

    @Override
    public void clearMemberDetailsCache() {
        throw new UnsupportedOperationException(
                "MemberRemoteDatasource not support clearMemberDetailsCache");
    }

    @Override
    public void clearAllMemberDatasourceCache() {
        throw new UnsupportedOperationException(
                "MemberRemoteDatasource not support clearAllMemberDatasourceCache");
    }

    @Override
    public Single<ChangebankResponse> setESignAgreementAsRead(SetESignAgreementAcceptedParameters setESignAgreementAcceptedParameters) {
        return mMemberApi.setESignAgreementAsRead(setESignAgreementAcceptedParameters.getHeaders(),
                setESignAgreementAcceptedParameters.getMemberId(),
                setESignAgreementAcceptedParameters);
    }

    @Override
    public Single<Session> forgotPassword(ForgotPasswordParameters forgotPasswordParameters) {
        return mMemberApi.forgotPassword(forgotPasswordParameters.getHeaders(), forgotPasswordParameters);
    }

    @Override
    public Single<ChangebankResponse> resetPassword(ResetPasswordParameters resetPasswordParameters) {
        return mMemberApi.resetPassword(resetPasswordParameters.getHeaders(), resetPasswordParameters);
    }

    @Override
    public Single<FileResponse> uploadProfilePicture(final AddProfilePictureParameters addProfilePictureParameters) {

        RequestBody requestBody = RequestBody
                .create(MediaType.parse("image/png"), addProfilePictureParameters.getBody());
        return mMemberApi.uploadProfilePicture(
                addProfilePictureParameters.getHeaders(),
                requestBody)
                .flatMap(new Function<UploadProfilePictureApiResponse, SingleSource<? extends FileResponse>>() {
                    @Override
                    public SingleSource<? extends FileResponse> apply(UploadProfilePictureApiResponse uploadProfilePictureApiResponse) throws Exception {
                        String fileName = uploadProfilePictureApiResponse.getDocumentId();
                        try {
                            File file;
                            file = File.createTempFile(fileName, ".png", mCacheDirectory);
                            file.deleteOnExit();

                            if (addProfilePictureParameters.getData() != null) {


                                byte[] pdfAsBytes = BaseEncoding.base64().decode(addProfilePictureParameters.getData());
                                FileOutputStream os;
                                if (pdfAsBytes != null) {
                                    os = new FileOutputStream(file, false);
                                    os.write(pdfAsBytes);
                                    os.flush();
                                    os.close();
                                } else {
                                    return Single.error(new Exception("Body is null"));
                                }
                            } else {
                                return Single.error(new Exception("Image Data is null"));
                            }

                            return Single.just(FileResponse.Create(file, uploadProfilePictureApiResponse.getDocumentId()));
                        } catch (Exception e) {
                            return Single.error(e);
                        }
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateMemberPhone(VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        return mMemberApi.updateMemberPhone(
                verifyPhoneCodeParameters.getHeaders(),
                verifyPhoneCodeParameters);
    }

    @Override
    public Single<ChangebankResponse> updateEmailAddress(UpdateEmailAddressParameters updateEmailAddressParameters) {
        return mMemberApi.updateEmailAddress(
                updateEmailAddressParameters.getHeaders(),
                updateEmailAddressParameters);
    }

    @Override
    public Completable deleteImageDocument(final ProfilePictureParameters documentParameters) {
        return mMemberApi.deleteProfilePicture(documentParameters.getHeaders());
    }

    @Override
    public Single<Optional<DocumentApiResponse>> getDocumentById(DocumentParameters documentParameters) {
        return mMemberApi.getDocumentById(
                documentParameters.getHeaders(),
                documentParameters.getDocumentId(),
                documentParameters.getMemberId())
                .map(new Function<DocumentApiResponse, Optional<DocumentApiResponse>>() {
                    @Override
                    public Optional<DocumentApiResponse> apply(DocumentApiResponse documentApiResponse) throws Exception {
                        return Optional.of(documentApiResponse);
                    }
                });
    }

    @Override
    public void saveDocument(DocumentApiResponse documentApiResponse) {
        throw new UnsupportedOperationException(
                "MemberRemoteDatasource not support saveDocument");
    }

    @Override
    public Single<Optional<FileResponse>> getDocumentImageById(final DocumentParameters documentParameters) {
        return mMemberApi.getDocumentImageById(documentParameters.getHeaders(), documentParameters.getDocumentId(), documentParameters.getMemberId())
                .flatMap(new Function<Response<ResponseBody>, SingleSource<? extends Optional<FileResponse>>>() {
                    @Override
                    public SingleSource<? extends Optional<FileResponse>> apply(Response<ResponseBody> responseBodyResponse) throws Exception {
                        String fileName = documentParameters.getDocumentId();
                        try {
                            File file;
                            file = File.createTempFile(fileName, ".png", mCacheDirectory);
                            file.deleteOnExit();
                            BufferedSink sink = Okio.buffer(Okio.sink(file));
                            // you can access body of response
                            ResponseBody responseBody = responseBodyResponse.body();
                            if (responseBody != null) {
                                sink.writeAll(responseBody.source());
                                sink.close();
                            } else {
                                return Single.error(new Exception("Body is null"));
                            }
                            return Single.just(Optional.of(
                                    FileResponse.Create(file, documentParameters.getDocumentId())));
                        } catch (IOException e) {
                            return Single.error(e);
                        }
                    }
                });
    }

    @Override
    public Single<Optional<FileResponse>> getProfilePicture(ProfilePictureParameters parameters) {
        return mMemberApi.getProfilePicture(parameters.getHeaders()).flatMap(new Function<Response<ResponseBody>, SingleSource<? extends Optional<FileResponse>>>() {
            @Override
            public SingleSource<? extends Optional<FileResponse>> apply(Response<ResponseBody> responseBodyResponse) throws Exception {
                String fileName = parameters.getMemberId();
                try {
                    File file;
                    file = File.createTempFile(fileName, ".png", mCacheDirectory);
                    file.deleteOnExit();
                    BufferedSink sink = Okio.buffer(Okio.sink(file));
                    // you can access body of response
                    ResponseBody responseBody = responseBodyResponse.body();
                    if (responseBody != null) {
                        sink.writeAll(responseBody.source());
                        sink.close();
                    } else {
                        return Single.error(new Exception("Body is null"));
                    }
                    return Single.just(Optional.of(
                            FileResponse.Create(file, parameters.getMemberId())));
                } catch (IOException e) {
                    return Single.error(e);
                }
            }
        });
    }

    @Override
    public void saveImageDocument(FileResponse file) {
        throw new UnsupportedOperationException(
                "MemberRemoteDatasource not support saveImageDocument");
    }

    @Override
    public Single<ChangebankResponse> log(LogParameters logParameters) {
        return mMemberApi.log(logParameters.getHeaders(), logParameters);
    }

    @Override
    public Single<ChangebankResponse> emailAvailability(EmailValidationParameters evp) {
        return mMemberApi.emailAvailability(evp.getHeaders(), evp);
    }

    @Override
    public Single<IdscanResponse> submitIdscan(IdscanParameters idscanParameters) {
        return mMemberApi.submitIdscan(
                idscanParameters.getHeaders(),
                idscanParameters.getMemberId(),
                idscanParameters);
    }

    @Override
    public Single<VersionConfig> getVersionConfig(SettingsParameter settingsParameter,String url) {
        return mMemberApi.getVersionConfig(url+ Endpoints.Member.GET_VERSION_CONFIG,settingsParameter.getOS(),settingsParameter.getApplicationId());
    }

    @Override
    public Single<UserProfileResponse> getUserProfile(UserProfileParameter userProfileParameter) {
        return mMemberApi.getUserProfile(userProfileParameter.getHeaders());
    }

    @Override
    public Single<CardDetailResponse> getCardDetails(CardDetailParameter cardDetailParameter) {
        return mMemberApi.getCardDetails(cardDetailParameter.getHeaders(),cardDetailParameter.getCardNumber(),true);
    }

    @Override
    public Single<List<ProductDetailsReponseItem>> getCardRelation(SubCardRelationParameter cardParameters) {
        return mMemberApi.getSubCardRelation(cardParameters.getHeaders(),cardParameters.getCardProduct());
    }

    @Override
    public Single<RSAPublicKeyResponse> getRsaPublicKey(UserProfileParameter userProfileParameter) {
        return mMemberApi.getRSAPublicKey(userProfileParameter.getHeaders());
    }

    @Override
    public Single<ActivateCardResponse> getActivateCard(ActivateCardParameter activateCardParameter) {
        return mMemberApi.getAccountActivate(activateCardParameter.getHeaders(),activateCardParameter.getCardNumber(),activateCardParameter);
    }

    @Override
    public Completable forgotUsername(ForgotUsernameParameters forgotUsernameParameters, String appId) {
        return mMemberApi.forgotUsername(forgotUsernameParameters.getHeaders(), forgotUsernameParameters, appId);
    }

    @Override
    public Completable batchListConfirm(CreateBatchListMemberParameters createBatchListMemberParameters) {
        return mMemberApi.batchListConfirm(createBatchListMemberParameters.getHeaders(),
                createBatchListMemberParameters.getAdminNumber(),
                createBatchListMemberParameters.getDob(),
                createBatchListMemberParameters.getLast4SSN());
    }

    @Override
    public Completable getAdminNumberAvailability(CreateBatchListMemberParameters createBatchListMemberParameters) {
        return mMemberApi.getAdminNumberAvailability(createBatchListMemberParameters.getHeaders(),
                createBatchListMemberParameters);
    }

    @Override
    public Single<Session> batchListCreateMember(CreateBatchListMemberParameters createBatchListMemberParameters) {
        return mMemberApi.batchListCreateMember(createBatchListMemberParameters.getHeaders(),
                createBatchListMemberParameters);
    }

    @Override
    public Single<DocumentUploadResponse> uploadDocuments(UploadDocumentsParameters uploadDocuments) {
        return mMemberApi.uploadDocuments(uploadDocuments.getHeaders(),uploadDocuments.getMemberId(), uploadDocuments);
    }
}
