package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.model.FileResponse;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.ProductDetailsReponseItem;
import com.example.cf_sdk.logic.changebanksdk.model.member.ActivateCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.CardDetailResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.DocumentApiResponse;
import com.example.cf_sdk.logic.changebanksdk.model.member.MemberDetails;
import com.example.cf_sdk.logic.changebanksdk.model.member.RSAPublicKeyResponse;
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

import java.io.File;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.NoSuchElementException;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 *
 * Member repository is in charge of choosing the correct {@link MemberDatasource}
 */

public class MemberRepository implements MemberDatasource {
    private MemberDatasource mRemoteDatasource;
    private MemberDatasource mCacheDatasource;

    private final Consumer<UserProfileResponse> saveUserProfile = new Consumer<UserProfileResponse>() {
        @Override
        public void accept(UserProfileResponse userProfileResponse) throws Exception {
            saveUserProfile(userProfileResponse);
        }
    };

    @Inject
    public MemberRepository(@Named("remote") MemberDatasource remoteDatasource,
                            @Named("cache") MemberDatasource cacheDatasource) {
        mRemoteDatasource = remoteDatasource;
        mCacheDatasource = cacheDatasource;
    }

    @Override
    public Single<ChangebankResponse> sendPhoneCode(
            SendPhoneCodeParameters sendPhoneCodeParameters) {
        return mRemoteDatasource.sendPhoneCode(sendPhoneCodeParameters);
    }

    @Override
    public Completable verifyPhoneCode(
            VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        return mRemoteDatasource.verifyPhoneCode(verifyPhoneCodeParameters);
    }

    @Override
    public Single<Session> createMember(CreateMemberParameters createMemberParameters) {
        return mRemoteDatasource.createMember(createMemberParameters);
    }

    @Override
    public Completable addAddress(final AddAddressParameters addAddressParameters) {
        return mRemoteDatasource.addAddress(addAddressParameters)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mCacheDatasource.addAddress(addAddressParameters);
                    }
                });
    }

    @Override
    public Single<MemberDetails> addMemberInfoAndAddress(final AddMemberInfoAndAddressParameters addMemberInfoAndAddressParameters) {
        return mRemoteDatasource.addMemberInfoAndAddress(addMemberInfoAndAddressParameters)
                .doOnSuccess(new Consumer<MemberDetails>() {
                    @Override
                    public void accept(MemberDetails memberDetails) throws Exception {
                        mCacheDatasource.saveMemberDetails(memberDetails);
                    }
                });
    }

    @Override
    public Single<Optional<OowQuestions>> addConfidential(final AddConfidentialParameters acp) {
  /*      Single<Optional<OowQuestions>> cache = mCacheDatasource.addConfidential(acp);

        Single<Optional<OowQuestions>> remote = mRemoteDatasource.addConfidential(acp)
                .doOnSuccess(new Consumer<Optional<OowQuestions>>() {
                    @Override
                    public void accept(Optional<OowQuestions> oowQuestionsOptional) throws Exception {
                        if (oowQuestionsOptional.isPresent()) {
                            mCacheDatasource.saveOowQuestions(oowQuestionsOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<OowQuestions>>() {
                    @Override
                    public boolean test(Optional<OowQuestions> oowQuestionsOptional) throws Exception {
                        return oowQuestionsOptional.isPresent() && !oowQuestionsOptional.get().isExpired();
                    }
                })
                .firstOrError();*/

        return mRemoteDatasource.addConfidential(acp);
    }

    @Override
    public Completable saveOowQuestions(OowQuestions oowQuestions) {
        return mCacheDatasource.saveOowQuestions(oowQuestions);
    }

    @Override
    public Single<ChangebankResponse> phoneAvailability(PhoneValidationParameters pvp) {
        return mRemoteDatasource.phoneAvailability(pvp);
    }

    @Override
    public Single<ChangebankResponse> emailAvailability(EmailValidationParameters evp) {
        return mRemoteDatasource.emailAvailability(evp);
    }

    @Override
    public Single<MemberDetails> getCardToCardAccountDetails(CardToCardTransferParameters cardToCardTransferParameters) {
        return mRemoteDatasource.getCardToCardAccountDetails(cardToCardTransferParameters);
    }

    @Override
    public Single<MemberDetails> addCheckDeposit(CheckDepositParam checkDepositParam) {
        return mRemoteDatasource.addCheckDeposit(checkDepositParam);
    }

    @Override
    public Single<Optional<MemberDetails>> getMemberDetails(MemberDetailParameters parameters) {
        //Check cache first then remote
        Single<Optional<MemberDetails>> cache = mCacheDatasource.getMemberDetails(parameters);

        Single<Optional<MemberDetails>> remote = mRemoteDatasource.getMemberDetails(parameters)
                .doOnSuccess(new Consumer<Optional<MemberDetails>>() {
                    @Override
                    public void accept(Optional<MemberDetails> memberDetailsOptional) throws Exception {
                        if (memberDetailsOptional.isPresent()) {
                            saveMemberDetails(memberDetailsOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<MemberDetails>>() {
                    @Override
                    public boolean test(Optional<MemberDetails> memberDetailsOptional) throws Exception {
                        return memberDetailsOptional.isPresent();
                    }
                })
                .firstOrError();
    }

    @Override
    public Single<VerifyOowResponse> verifyOowQuestion(VerifyOutOfWalletParameters verifyOutOfWalletParameters) {
        return mRemoteDatasource.verifyOowQuestion(verifyOutOfWalletParameters);
    }

    @Override
    public Single<OowQuestions> refreshOowQuestions(RefreshOowParameters refreshOowParameters) {
        return mRemoteDatasource.refreshOowQuestions(refreshOowParameters);
    }

    @Override
    public Single<File> getAgreement(AgreementParameters agreementParameters) {
        return mRemoteDatasource.getAgreement(agreementParameters);
    }


    @Override
    public void saveMemberDetails(MemberDetails memberDetails) {
        mCacheDatasource.saveMemberDetails(memberDetails);
    }

    @Override
    public void saveUserProfile(UserProfileResponse userProfileResponse) {
        mCacheDatasource.saveUserProfile(userProfileResponse);
    }

    @Override
    public void clearMemberDetailsCache() {
        mCacheDatasource.clearMemberDetailsCache();
    }

    @Override
    public void clearAllMemberDatasourceCache() {
        mCacheDatasource.clearAllMemberDatasourceCache();
    }

    @Override
    public Single<ChangebankResponse> setESignAgreementAsRead(SetESignAgreementAcceptedParameters setESignAgreementAcceptedParameters) {
        return mRemoteDatasource.setESignAgreementAsRead(setESignAgreementAcceptedParameters);
    }

    @Override
    public Single<Session> forgotPassword(ForgotPasswordParameters forgotPasswordParameters) {
        return mRemoteDatasource.forgotPassword(forgotPasswordParameters);
    }

    @Override
    public Single<ChangebankResponse> resetPassword(ResetPasswordParameters resetPasswordParameters) {
        return mRemoteDatasource.resetPassword(resetPasswordParameters);
    }

    @Override
    public Single<IdscanResponse> submitIdscan(IdscanParameters idscanParameters) {
        return mRemoteDatasource.submitIdscan(idscanParameters);
    }


    @Override
    public Single<FileResponse> uploadProfilePicture(final AddProfilePictureParameters addProfilePictureParameters) {
        return mRemoteDatasource.uploadProfilePicture(addProfilePictureParameters)
                .doOnSuccess(new Consumer<FileResponse>() {
                    @Override
                    public void accept(FileResponse fileResponse) throws Exception {
                        saveImageDocument(fileResponse);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateMemberPhone(final VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        return mRemoteDatasource.updateMemberPhone(verifyPhoneCodeParameters).onErrorResumeNext(new Function<Throwable, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof NoSuchElementException) {
                            ChangebankResponse error = new ChangebankResponse();
                            error.setHttpCode(HttpURLConnection.HTTP_NO_CONTENT);
                            error.setMessage("No Content");
                            mCacheDatasource.updateMemberPhone(verifyPhoneCodeParameters);
                            return Single.error(error);
                        }
                        return Single.error(throwable);
                    }
                })
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse changebankResponse) throws Exception {
                        mCacheDatasource.updateMemberPhone(verifyPhoneCodeParameters);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateEmailAddress(final UpdateEmailAddressParameters updateEmailAddressParameters) {
        return mRemoteDatasource.updateEmailAddress(updateEmailAddressParameters).onErrorResumeNext(new Function<Throwable, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(Throwable throwable) throws Exception {

                        if (throwable instanceof NoSuchElementException) {
                            ChangebankResponse error = new ChangebankResponse();
                            error.setHttpCode(HttpURLConnection.HTTP_NO_CONTENT);
                            error.setMessage("No Content");
                            mCacheDatasource.updateEmailAddress(updateEmailAddressParameters);
                            return Single.error(error);
                        }
                        return Single.error(throwable);
                    }
                })
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse changebankResponse) throws Exception {
                        mCacheDatasource.updateEmailAddress(updateEmailAddressParameters);
                    }
                });
    }

    @Override
    public Single<Optional<DocumentApiResponse>> getDocumentById(DocumentParameters documentParameters) {

        //Check cache first then remote
        Single<Optional<DocumentApiResponse>> cache = mCacheDatasource.getDocumentById(documentParameters);

        Single<Optional<DocumentApiResponse>> remote = mRemoteDatasource.getDocumentById(documentParameters)
                .doOnSuccess(new Consumer<Optional<DocumentApiResponse>>() {
                    @Override
                    public void accept(Optional<DocumentApiResponse> documentApiResponseOptional) throws Exception {
                        if (documentApiResponseOptional.isPresent()) {
                            saveDocument(documentApiResponseOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<DocumentApiResponse>>() {
                    @Override
                    public boolean test(Optional<DocumentApiResponse> documentApiResponseOptional) throws Exception {
                        return documentApiResponseOptional.isPresent();
                    }
                })
                .firstOrError();
    }

    @Override
    public void saveDocument(DocumentApiResponse documentApiResponse) {
        mCacheDatasource.saveDocument(documentApiResponse);
    }

    @Override
    public Single<Optional<FileResponse>> getDocumentImageById(final DocumentParameters documentParameters) {
        //Check cache first then remote
        Single<Optional<FileResponse>> cache = mCacheDatasource.getDocumentImageById(documentParameters);

        Single<Optional<FileResponse>> remote = mRemoteDatasource.getDocumentImageById(documentParameters)
                .doOnSuccess(new Consumer<Optional<FileResponse>>() {
                    @Override
                    public void accept(Optional<FileResponse> fileResponseOptional) throws Exception {
                        if (fileResponseOptional.isPresent()) {
                            saveImageDocument(fileResponseOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<FileResponse>>() {
                    @Override
                    public boolean test(Optional<FileResponse> fileResponseOptional) throws Exception {
                        return fileResponseOptional.isPresent();
                    }
                })
                .firstOrError();
    }

    @Override
    public Single<Optional<FileResponse>> getProfilePicture(ProfilePictureParameters parameters) {
        //Check cache first then remote
        Single<Optional<FileResponse>> cache = mCacheDatasource.getProfilePicture(parameters);

        Single<Optional<FileResponse>> remote = mRemoteDatasource.getProfilePicture(parameters)
                .doOnSuccess(new Consumer<Optional<FileResponse>>() {
                    @Override
                    public void accept(Optional<FileResponse> fileResponseOptional) throws Exception {
                        if (fileResponseOptional.isPresent()) {
                            saveImageDocument(fileResponseOptional.get());
                        }
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<FileResponse>>() {
                    @Override
                    public boolean test(Optional<FileResponse> fileResponseOptional) throws Exception {
                        return fileResponseOptional.isPresent();
                    }
                })
                .firstOrError();
    }

    @Override
    public void saveImageDocument(FileResponse file) {
        mCacheDatasource.saveImageDocument(file);
    }

    @Override
    public Single<ChangebankResponse> log(LogParameters logParameters) {
        return mRemoteDatasource.log(logParameters);
    }

    @Override
    public Single<VersionConfig> getVersionConfig(SettingsParameter settingsParameter,String url) {
        return mRemoteDatasource.getVersionConfig(settingsParameter,url);
    }

    @Override
    public Single<UserProfileResponse> getUserProfile(UserProfileParameter parameters) {
        return mRemoteDatasource.getUserProfile(parameters).doOnSuccess(saveUserProfile);
    }

    @Override
    public Single<CardDetailResponse> getCardDetails(CardDetailParameter cardDetailParameter) {
        return mRemoteDatasource.getCardDetails(cardDetailParameter);
    }

    @Override
    public Single<List<ProductDetailsReponseItem>> getCardRelation(SubCardRelationParameter cardParameters) {
        return mRemoteDatasource.getCardRelation(cardParameters);
    }

    @Override
    public Single<RSAPublicKeyResponse> getRsaPublicKey(UserProfileParameter userProfileParameter) {
        return mRemoteDatasource.getRsaPublicKey(userProfileParameter);
    }

    @Override
    public Single<ActivateCardResponse> getActivateCard(ActivateCardParameter activateCardParameter) {
        return mRemoteDatasource.getActivateCard(activateCardParameter);
    }

    @Override
    public Completable forgotUsername(ForgotUsernameParameters forgotUsernameParameters,String appId) {
        return mRemoteDatasource.forgotUsername(forgotUsernameParameters,appId);
    }

    @Override
    public Completable batchListConfirm(CreateBatchListMemberParameters createBatchListMemberParameters) {
        return mRemoteDatasource.batchListConfirm(createBatchListMemberParameters);
    }

    @Override
    public Completable getAdminNumberAvailability(CreateBatchListMemberParameters createBatchListMemberParameters) {
        return mRemoteDatasource.getAdminNumberAvailability(createBatchListMemberParameters);
    }

    @Override
    public Single<Session> batchListCreateMember(CreateBatchListMemberParameters createBatchListMemberParameters) {
        return mRemoteDatasource.batchListCreateMember(createBatchListMemberParameters);
    }


    @Override
    public Completable deleteImageDocument(final ProfilePictureParameters documentParameters) {
        return mRemoteDatasource.deleteImageDocument(documentParameters)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mCacheDatasource.deleteImageDocument(documentParameters);
                    }
                });
    }


    // upload ID scan docs font,back selfie images
    @Override
    public Single<DocumentUploadResponse> uploadDocuments(final UploadDocumentsParameters idScanDocuments) {
        return mRemoteDatasource.uploadDocuments(idScanDocuments);

    }

}
