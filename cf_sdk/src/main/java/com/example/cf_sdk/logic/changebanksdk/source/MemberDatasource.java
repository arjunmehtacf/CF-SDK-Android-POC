package com.example.cf_sdk.logic.changebanksdk.source;

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
import com.google.common.base.Optional;

import java.io.File;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 *
 * Datasource to provide member data.
 */

public interface MemberDatasource {
    Single<ChangebankResponse> sendPhoneCode(SendPhoneCodeParameters sendPhoneCodeParameters);

    Completable verifyPhoneCode(VerifyPhoneCodeParameters verifyPhoneCodeParameters);

    Single<Session> createMember(CreateMemberParameters createMemberParameters);

    Completable addAddress(AddAddressParameters addAddressParameters);

    Single<MemberDetails> addMemberInfoAndAddress(AddMemberInfoAndAddressParameters addMemberInfoAndAddressParameters);

    Single<Optional<OowQuestions>> addConfidential(AddConfidentialParameters addConfidentialParameters);

    Completable saveOowQuestions(OowQuestions oowQuestions);

    Single<ChangebankResponse> phoneAvailability(
            PhoneValidationParameters phoneValidationParameters);

    Single<ChangebankResponse> emailAvailability(EmailValidationParameters evp);

    Single<Optional<MemberDetails>> getMemberDetails(MemberDetailParameters stringParameters);

    Single<VerifyOowResponse> verifyOowQuestion(VerifyOutOfWalletParameters verifyOutOfWalletParameters);

    Single<OowQuestions> refreshOowQuestions(RefreshOowParameters refreshOowParameters);

    Single<File> getAgreement(AgreementParameters agreementParameters);

    void saveMemberDetails(MemberDetails memberDetails);

    void saveUserProfile(UserProfileResponse userProfileResponse);

    void clearMemberDetailsCache();

    void clearAllMemberDatasourceCache();

    Single<ChangebankResponse> setESignAgreementAsRead(SetESignAgreementAcceptedParameters setESignAgreementAcceptedParameters);

    Single<Session> forgotPassword(ForgotPasswordParameters forgotPasswordParameters);

    Single<ChangebankResponse> resetPassword(ResetPasswordParameters resetPasswordParameters);

    Single<IdscanResponse> submitIdscan(IdscanParameters idscanParameters);

    Single<FileResponse> uploadProfilePicture(AddProfilePictureParameters addProfilePictureParameters);

    Single<ChangebankResponse> updateMemberPhone(VerifyPhoneCodeParameters verifyPhoneCodeParameters);

    Single<ChangebankResponse> updateEmailAddress(UpdateEmailAddressParameters updateEmailAddressParameters);

    Single<Optional<DocumentApiResponse>> getDocumentById(DocumentParameters documentParameters);

    Single<MemberDetails> getCardToCardAccountDetails(CardToCardTransferParameters cardToCardTransferParameters);

    Single<MemberDetails> addCheckDeposit(CheckDepositParam checkDepositParam);

    void saveDocument(DocumentApiResponse documentApiResponse);

    Completable deleteImageDocument(ProfilePictureParameters documentParameters);

    Single<Optional<FileResponse>> getDocumentImageById(DocumentParameters documentParameters);

    Single<Optional<FileResponse>> getProfilePicture(ProfilePictureParameters parameters);

    void saveImageDocument(FileResponse fileResponse);

    Single<ChangebankResponse> log(LogParameters logParameters);

    Single<VersionConfig> getVersionConfig(SettingsParameter settingsParameter,String url);

    Single<UserProfileResponse> getUserProfile(UserProfileParameter userProfileParameter);

    Single<CardDetailResponse> getCardDetails(CardDetailParameter cardDetailParameter);

    Single<List<ProductDetailsReponseItem>> getCardRelation(SubCardRelationParameter cardParameters);

    Single<RSAPublicKeyResponse> getRsaPublicKey(UserProfileParameter userProfileParameter);

    Single<ActivateCardResponse> getActivateCard(ActivateCardParameter activateCardParameter);

    Completable forgotUsername(ForgotUsernameParameters forgotUsernameParameters, String appId);

    Completable batchListConfirm(CreateBatchListMemberParameters createBatchListMemberParameters);

    Single<DocumentUploadResponse> uploadDocuments(UploadDocumentsParameters idScanDocuments);

    Completable getAdminNumberAvailability(CreateBatchListMemberParameters createBatchListMemberParameters);

    Single<Session> batchListCreateMember(CreateBatchListMemberParameters createBatchListMemberParameters);

}
