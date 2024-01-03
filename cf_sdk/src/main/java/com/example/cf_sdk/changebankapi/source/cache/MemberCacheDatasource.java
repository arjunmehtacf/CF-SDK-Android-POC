package com.example.cf_sdk.changebankapi.source.cache;

import com.example.cf_sdk.changebankapi.model.FileResponse;
import com.example.cf_sdk.changebankapi.model.Session;
import com.example.cf_sdk.changebankapi.model.account.ProductDetailsReponseItem;
import com.example.cf_sdk.changebankapi.model.member.ActivateCardResponse;
import com.example.cf_sdk.changebankapi.model.member.CardDetailResponse;
import com.example.cf_sdk.changebankapi.model.member.DocumentApiResponse;
import com.example.cf_sdk.changebankapi.model.member.MemberDetails;
import com.example.cf_sdk.changebankapi.model.member.RSAPublicKeyResponse;
import com.example.cf_sdk.changebankapi.model.member.UserProfileResponse;
import com.example.cf_sdk.changebankapi.model.member.version.VersionConfig;
import com.example.cf_sdk.changebankapi.model.oow.OowQuestions;
import com.example.cf_sdk.changebankapi.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.changebankapi.parameter.account.CheckDepositParam;
import com.example.cf_sdk.changebankapi.parameter.account.SubCardRelationParameter;
import com.example.cf_sdk.changebankapi.parameter.member.ActivateCardParameter;
import com.example.cf_sdk.changebankapi.parameter.member.AddAddressParameters;
import com.example.cf_sdk.changebankapi.parameter.member.AddConfidentialParameters;
import com.example.cf_sdk.changebankapi.parameter.member.AddMemberInfoAndAddressParameters;
import com.example.cf_sdk.changebankapi.parameter.member.AddProfilePictureParameters;
import com.example.cf_sdk.changebankapi.parameter.member.AgreementParameters;
import com.example.cf_sdk.changebankapi.parameter.member.CardDetailParameter;
import com.example.cf_sdk.changebankapi.parameter.member.CreateBatchListMemberParameters;
import com.example.cf_sdk.changebankapi.parameter.member.CreateMemberParameters;
import com.example.cf_sdk.changebankapi.parameter.member.DocumentParameters;
import com.example.cf_sdk.changebankapi.parameter.member.ForgotPasswordParameters;
import com.example.cf_sdk.changebankapi.parameter.member.ForgotUsernameParameters;
import com.example.cf_sdk.changebankapi.parameter.member.IdscanParameters;
import com.example.cf_sdk.changebankapi.parameter.member.LogParameters;
import com.example.cf_sdk.changebankapi.parameter.member.MemberDetailParameters;
import com.example.cf_sdk.changebankapi.parameter.member.ProfilePictureParameters;
import com.example.cf_sdk.changebankapi.parameter.member.RefreshOowParameters;
import com.example.cf_sdk.changebankapi.parameter.member.ResetPasswordParameters;
import com.example.cf_sdk.changebankapi.parameter.member.SendPhoneCodeParameters;
import com.example.cf_sdk.changebankapi.parameter.member.SetESignAgreementAcceptedParameters;
import com.example.cf_sdk.changebankapi.parameter.member.SettingsParameter;
import com.example.cf_sdk.changebankapi.parameter.member.UpdateEmailAddressParameters;
import com.example.cf_sdk.changebankapi.parameter.member.UploadDocumentsParameters;
import com.example.cf_sdk.changebankapi.parameter.member.UserProfileParameter;
import com.example.cf_sdk.changebankapi.parameter.member.VerifyOutOfWalletParameters;
import com.example.cf_sdk.changebankapi.parameter.member.VerifyPhoneCodeParameters;
import com.example.cf_sdk.changebankapi.parameter.validation.EmailValidationParameters;
import com.example.cf_sdk.changebankapi.parameter.validation.PhoneValidationParameters;
import com.example.cf_sdk.changebankapi.response.ChangebankResponse;
import com.example.cf_sdk.changebankapi.response.DocumentUploadResponse;
import com.example.cf_sdk.changebankapi.response.IdscanResponse;
import com.example.cf_sdk.changebankapi.response.VerifyOowResponse;
import com.example.sdk_no_dagger.changebankapi.source.remote.MemberDatasource;
import com.google.common.base.Optional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *
 * Cache datasource for member repository.
 */

public class MemberCacheDatasource implements MemberDatasource {
    private OowQuestions mOowQuestions;
    private MemberDetails mMemberDetails;
    private Map<String, DocumentApiResponse> mDocuments;
    private Map<String, File> mImageDocuments;

    private UserProfileResponse mUserProfileResponse;

    public MemberCacheDatasource() {
        mDocuments = new HashMap<>();
        mImageDocuments = new HashMap<>();
    }

    @Override
    public Single<ChangebankResponse> sendPhoneCode(SendPhoneCodeParameters sendPhoneCodeParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support sendPhoneCode");
    }

    @Override
    public Completable verifyPhoneCode(VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        throw new UnsupportedOperationException(
                "MemberCacheDatasource not support verifyPhoneCode");
    }

    @Override
    public Single<Session> createMember(
            CreateMemberParameters createMemberParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support createMember");
    }

    @Override
    public Single<MemberDetails> getCardToCardAccountDetails(CardToCardTransferParameters cardToCardTransferParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support phoneAvailability");
    }

    @Override
    public Single<MemberDetails> addCheckDeposit(CheckDepositParam checkDepositParam) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support phoneAvailability");
    }

    @Override
    public Single<DocumentUploadResponse> uploadDocuments(UploadDocumentsParameters idScanDocuments) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support uploadDocuments");
    }

    @Override
    public Completable addAddress(AddAddressParameters addAddressParameters) {
        if (mMemberDetails == null) {
            mMemberDetails = MemberDetails.create(addAddressParameters.getMemberId())
                    .withAddress(addAddressParameters.getAddress());
        } else {
            mMemberDetails = mMemberDetails.withAddress(addAddressParameters.getAddress());
        }
        return Completable.complete();
    }

    @Override
    public Single<MemberDetails> addMemberInfoAndAddress(AddMemberInfoAndAddressParameters addMemberInfoAndAddressParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support addMemberInfoAndAddress");
    }

    @Override
    public Single<Optional<OowQuestions>> addConfidential(AddConfidentialParameters addConfidentialParameters) {
        return Single.just(Optional.fromNullable(mOowQuestions));
    }

    @Override
    public Completable saveOowQuestions(OowQuestions oowQuestions) {
        mOowQuestions = oowQuestions;
        return Completable.complete();
    }

    @Override
    public Single<ChangebankResponse> phoneAvailability(
            PhoneValidationParameters phoneValidationParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support phoneAvailability");
    }

    @Override
    public Single<ChangebankResponse> emailAvailability(EmailValidationParameters evp) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support emailAvailability");
    }

    @Override
    public Single<Optional<MemberDetails>> getMemberDetails(MemberDetailParameters parameters) {
        if (parameters.getShouldClearCache()) {
            mMemberDetails = null;
        }

        return Single.just(Optional.fromNullable(mMemberDetails));
    }

    @Override
    public Single<VerifyOowResponse> verifyOowQuestion(VerifyOutOfWalletParameters verifyOutOfWalletParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support verifyOowQuestion");
    }

    @Override
    public Single<OowQuestions> refreshOowQuestions(RefreshOowParameters refreshOowParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support refreshOowQuestions");
    }

    @Override
    public Single<File> getAgreement(AgreementParameters agreementParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support getAgreement");
    }

    @Override
    public void saveMemberDetails(MemberDetails memberDetails) {
        mMemberDetails = memberDetails;
    }

    @Override
    public void saveUserProfile(UserProfileResponse userProfileResponse)
    {
        mUserProfileResponse = userProfileResponse;
    }

    @Override
    public void clearMemberDetailsCache() {
        mMemberDetails = null;
        mUserProfileResponse = null;
    }

    @Override
    public void clearAllMemberDatasourceCache() {
        mOowQuestions = null;
        mMemberDetails = null;
        mDocuments = new HashMap<>();
        mImageDocuments = new HashMap<>();
        mUserProfileResponse = null;
    }

    @Override
    public Single<ChangebankResponse> setESignAgreementAsRead(SetESignAgreementAcceptedParameters setESignAgreementAcceptedParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support setESignAgreementAsRead");
    }

    @Override
    public Single<Session> forgotPassword(ForgotPasswordParameters forgotPasswordParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support forgotPassword");
    }

    @Override
    public Single<ChangebankResponse> resetPassword(ResetPasswordParameters resetPasswordParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support resetPassword");
    }

    @Override
    public Single<IdscanResponse> submitIdscan(IdscanParameters idscanParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support submitIdscan");
    }

    @Override
    public Single<ChangebankResponse> updateMemberPhone(VerifyPhoneCodeParameters verifyPhoneCodeParameters) {
        mMemberDetails = mMemberDetails.withPhone(verifyPhoneCodeParameters.getPhone());
        return Single.just(new ChangebankResponse());
    }

    @Override
    public Single<ChangebankResponse> updateEmailAddress(UpdateEmailAddressParameters updateEmailAddressParameters) {
        mMemberDetails = mMemberDetails.withEmail(updateEmailAddressParameters.getEmail());
        return Single.just(new ChangebankResponse());
    }

    @Override
    public Single<Optional<DocumentApiResponse>> getDocumentById(DocumentParameters documentParameters) {
        return Single.just(Optional.fromNullable(mDocuments.get(documentParameters.getDocumentId())));
    }

    @Override
    public void saveDocument(DocumentApiResponse documentApiResponse) {
        if (mDocuments != null) {
            mDocuments.put(documentApiResponse.getDocumentId(), documentApiResponse);
        }
    }

    @Override
    public Single<FileResponse> uploadProfilePicture(AddProfilePictureParameters addProfilePictureParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support uploadProfilePicture");
    }

    @Override
    public Completable deleteImageDocument(final ProfilePictureParameters documentParameters) {
        if (mMemberDetails != null) {
            mMemberDetails = mMemberDetails.withProfilePicture(null);
        }
        mImageDocuments.remove(documentParameters.getDocumentId());

        return Completable.complete();
    }

    @Override
    public Single<Optional<FileResponse>> getDocumentImageById(DocumentParameters documentParameters) {
        if (mImageDocuments.containsKey(documentParameters.getDocumentId())) {
            File file = mImageDocuments.get(documentParameters.getDocumentId());
            return Single.just(Optional.of(FileResponse.Create(file, documentParameters.getDocumentId())));
        }

        return Single.just(Optional.<FileResponse>absent());
    }

    @Override
    public Single<Optional<FileResponse>> getProfilePicture(ProfilePictureParameters documentParameters) {
        if (mImageDocuments.containsKey(documentParameters.getDocumentId())) {
            File file = mImageDocuments.get(documentParameters.getDocumentId());
            return Single.just(Optional.of(FileResponse.Create(file, documentParameters.getDocumentId())));
        }

        return Single.just(Optional.<FileResponse>absent());
    }

    @Override
    public void saveImageDocument(FileResponse fileResponse) {
        //Save the image file with just the document id as the key.
        String documentId = fileResponse.getFileName();
        if (mMemberDetails != null) {
            mMemberDetails = mMemberDetails.withProfilePicture(documentId);
        }

        mImageDocuments.put(documentId, fileResponse.getFile());
    }

    @Override
    public Single<ChangebankResponse> log(LogParameters logParameters) {
        throw new UnsupportedOperationException("MemberCacheDatasource not support log");
    }

    @Override
    public Single<VersionConfig> getVersionConfig(SettingsParameter settingsParameter) {
        throw new UnsupportedOperationException(
                "MemberCacheDatasource not support getVersionConfig");
    }

    @Override
    public Single<UserProfileResponse> getUserProfile(UserProfileParameter userProfileParameter) {
        return null;
    }

    @Override
    public Single<CardDetailResponse> getCardDetails(CardDetailParameter cardDetailParameter) {
        return null;
    }

    @Override
    public Single<List<ProductDetailsReponseItem>> getCardRelation(SubCardRelationParameter cardParameters) {
        return null;
    }

    @Override
    public Single<RSAPublicKeyResponse> getRsaPublicKey(UserProfileParameter userProfileParameter) {
        return null;
    }

    @Override
    public Single<ActivateCardResponse> getActivateCard(ActivateCardParameter activateCardParameter) {
        return null;
    }

    @Override
    public Completable forgotUsername(ForgotUsernameParameters forgotUsernameParameters, String appId) {
        throw new UnsupportedOperationException(
                "MemberCacheDatasource not support forgotUsername");
    }

    @Override
    public Completable batchListConfirm(CreateBatchListMemberParameters createBatchListMemberParameters) {
        throw new UnsupportedOperationException(
                "MemberCacheDatasource not support batchListConfirm");
    }

    @Override
    public Completable getAdminNumberAvailability(CreateBatchListMemberParameters createBatchListMemberParameters) {
        throw new UnsupportedOperationException(
                "MemberCacheDatasource not support getAdminNumberAvailability");
    }

    @Override
    public Single<Session> batchListCreateMember(CreateBatchListMemberParameters createBatchListMemberParameters) {
        throw new UnsupportedOperationException(
                "MemberCacheDatasource not support batchListCreateMember");
    }


}
