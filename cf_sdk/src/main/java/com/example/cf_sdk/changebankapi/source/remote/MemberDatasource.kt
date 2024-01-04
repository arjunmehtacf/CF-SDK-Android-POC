package com.example.cf_sdk.changebankapi.source.remote


import com.example.cf_sdk.changebankapi.model.FileResponse
import com.example.cf_sdk.changebankapi.model.Session
import com.example.cf_sdk.changebankapi.model.account.ProductDetailsReponseItem
import com.example.cf_sdk.changebankapi.model.member.ActivateCardResponse
import com.example.cf_sdk.changebankapi.model.member.CardDetailResponse
import com.example.cf_sdk.changebankapi.model.member.DocumentApiResponse
import com.example.cf_sdk.changebankapi.model.member.MemberDetails
import com.example.cf_sdk.changebankapi.model.member.RSAPublicKeyResponse
import com.example.cf_sdk.changebankapi.model.member.UserProfileResponse
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.changebankapi.model.oow.OowQuestions
import com.example.cf_sdk.changebankapi.parameter.account.CardToCardTransferParameters
import com.example.cf_sdk.changebankapi.parameter.account.CheckDepositParam
import com.example.cf_sdk.changebankapi.parameter.account.SubCardRelationParameter
import com.example.cf_sdk.changebankapi.parameter.member.ActivateCardParameter
import com.example.cf_sdk.changebankapi.parameter.member.AddAddressParameters
import com.example.cf_sdk.changebankapi.parameter.member.AddConfidentialParameters
import com.example.cf_sdk.changebankapi.parameter.member.AddMemberInfoAndAddressParameters
import com.example.cf_sdk.changebankapi.parameter.member.AddProfilePictureParameters
import com.example.cf_sdk.changebankapi.parameter.member.AgreementParameters
import com.example.cf_sdk.changebankapi.parameter.member.CardDetailParameter
import com.example.cf_sdk.changebankapi.parameter.member.CreateBatchListMemberParameters
import com.example.cf_sdk.changebankapi.parameter.member.CreateMemberParameters
import com.example.cf_sdk.changebankapi.parameter.member.DocumentParameters
import com.example.cf_sdk.changebankapi.parameter.member.ForgotPasswordParameters
import com.example.cf_sdk.changebankapi.parameter.member.ForgotUsernameParameters
import com.example.cf_sdk.changebankapi.parameter.member.IdscanParameters
import com.example.cf_sdk.changebankapi.parameter.member.LogParameters
import com.example.cf_sdk.changebankapi.parameter.member.MemberDetailParameters
import com.example.cf_sdk.changebankapi.parameter.member.ProfilePictureParameters
import com.example.cf_sdk.changebankapi.parameter.member.RefreshOowParameters
import com.example.cf_sdk.changebankapi.parameter.member.ResetPasswordParameters
import com.example.cf_sdk.changebankapi.parameter.member.SendPhoneCodeParameters
import com.example.cf_sdk.changebankapi.parameter.member.SetESignAgreementAcceptedParameters
import com.example.cf_sdk.defination.request.SettingsParameter
import com.example.cf_sdk.changebankapi.parameter.member.UpdateEmailAddressParameters
import com.example.cf_sdk.changebankapi.parameter.member.UploadDocumentsParameters
import com.example.cf_sdk.changebankapi.parameter.member.UserProfileParameter
import com.example.cf_sdk.changebankapi.parameter.member.VerifyOutOfWalletParameters
import com.example.cf_sdk.changebankapi.parameter.member.VerifyPhoneCodeParameters
import com.example.cf_sdk.changebankapi.parameter.validation.EmailValidationParameters
import com.example.cf_sdk.changebankapi.parameter.validation.PhoneValidationParameters
import com.example.cf_sdk.defination.response.ChangebankResponse
import com.example.cf_sdk.changebankapi.response.DocumentUploadResponse
import com.example.cf_sdk.changebankapi.response.IdscanResponse
import com.example.cf_sdk.changebankapi.response.VerifyOowResponse
import com.google.common.base.Optional
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File

/**
 * Created by victorojeda on 11/23/16.
 *
 *
 * Datasource to provide member data.
 */
interface MemberDatasource {
    fun sendPhoneCode(sendPhoneCodeParameters: SendPhoneCodeParameters?): Single<ChangebankResponse?>?
    fun verifyPhoneCode(verifyPhoneCodeParameters: VerifyPhoneCodeParameters?): Completable?
    fun createMember(createMemberParameters: CreateMemberParameters?): Single<Session?>?
    fun addAddress(addAddressParameters: AddAddressParameters?): Completable?
    fun addMemberInfoAndAddress(addMemberInfoAndAddressParameters: AddMemberInfoAndAddressParameters?): Single<MemberDetails?>?
    fun addConfidential(addConfidentialParameters: AddConfidentialParameters?): Single<Optional<OowQuestions?>?>?
    fun saveOowQuestions(oowQuestions: OowQuestions?): Completable?
    fun phoneAvailability(
        phoneValidationParameters: PhoneValidationParameters?
    ): Single<ChangebankResponse?>?

    fun emailAvailability(evp: EmailValidationParameters?): Single<ChangebankResponse?>?
    fun getMemberDetails(stringParameters: MemberDetailParameters?): Single<Optional<MemberDetails?>?>?
    fun verifyOowQuestion(verifyOutOfWalletParameters: VerifyOutOfWalletParameters?): Single<VerifyOowResponse?>?
    fun refreshOowQuestions(refreshOowParameters: RefreshOowParameters?): Single<OowQuestions?>?
    fun getAgreement(agreementParameters: AgreementParameters?): Single<File?>?
    fun saveMemberDetails(memberDetails: MemberDetails?)
    fun saveUserProfile(userProfileResponse: UserProfileResponse?)
    fun clearMemberDetailsCache()
    fun clearAllMemberDatasourceCache()
    fun setESignAgreementAsRead(setESignAgreementAcceptedParameters: SetESignAgreementAcceptedParameters?): Single<ChangebankResponse?>?
    fun forgotPassword(forgotPasswordParameters: ForgotPasswordParameters?): Single<Session?>?
    fun resetPassword(resetPasswordParameters: ResetPasswordParameters?): Single<ChangebankResponse?>?
    fun submitIdscan(idscanParameters: IdscanParameters?): Single<IdscanResponse?>?
    fun uploadProfilePicture(addProfilePictureParameters: AddProfilePictureParameters?): Single<FileResponse?>?
    fun updateMemberPhone(verifyPhoneCodeParameters: VerifyPhoneCodeParameters?): Single<ChangebankResponse?>?
    fun updateEmailAddress(updateEmailAddressParameters: UpdateEmailAddressParameters?): Single<ChangebankResponse?>?
    fun getDocumentById(documentParameters: DocumentParameters?): Single<Optional<DocumentApiResponse?>?>?
    fun getCardToCardAccountDetails(cardToCardTransferParameters: CardToCardTransferParameters?): Single<MemberDetails?>?
    fun addCheckDeposit(checkDepositParam: CheckDepositParam?): Single<MemberDetails?>?
    fun saveDocument(documentApiResponse: DocumentApiResponse?)
    fun deleteImageDocument(documentParameters: ProfilePictureParameters?): Completable?
    fun getDocumentImageById(documentParameters: DocumentParameters?):Single<out Optional<out FileResponse?>?>
    fun getProfilePicture(parameters: ProfilePictureParameters?): Single<out Optional<out FileResponse?>?>
    fun saveImageDocument(fileResponse: FileResponse?)
    fun log(logParameters: LogParameters?): Single<ChangebankResponse?>?
    fun getVersionConfig(settingsParameter: SettingsParameter?): Single<VersionConfig?>?
    fun getUserProfile(userProfileParameter: UserProfileParameter?): Single<UserProfileResponse?>?
    fun getCardDetails(cardDetailParameter: CardDetailParameter?): Single<CardDetailResponse?>?
    fun getCardRelation(cardParameters: SubCardRelationParameter?): Single<List<ProductDetailsReponseItem?>?>?
    fun getRsaPublicKey(userProfileParameter: UserProfileParameter?): Single<RSAPublicKeyResponse?>?
    fun getActivateCard(activateCardParameter: ActivateCardParameter?): Single<ActivateCardResponse?>?
    fun forgotUsername(
        forgotUsernameParameters: ForgotUsernameParameters?,
        appId: String?
    ): Completable?

    fun batchListConfirm(createBatchListMemberParameters: CreateBatchListMemberParameters?): Completable?
    fun uploadDocuments(idScanDocuments: UploadDocumentsParameters?): Single<DocumentUploadResponse?>?
    fun getAdminNumberAvailability(createBatchListMemberParameters: CreateBatchListMemberParameters?): Completable?
    fun batchListCreateMember(createBatchListMemberParameters: CreateBatchListMemberParameters?): Single<Session?>?
}