package com.example.cf_sdk.changebankapi.source


import com.example.cf_sdk.changebankapi.model.FileResponse
import com.example.cf_sdk.defination.response.Session
import com.example.cf_sdk.changebankapi.model.account.ProductDetailsReponseItem
import com.example.cf_sdk.changebankapi.model.member.ActivateCardResponse
import com.example.cf_sdk.changebankapi.model.member.CardDetailResponse
import com.example.cf_sdk.changebankapi.model.member.DocumentApiResponse
import com.example.cf_sdk.changebankapi.model.member.MemberDetails
import com.example.cf_sdk.changebankapi.model.member.RSAPublicKeyResponse
import com.example.cf_sdk.defination.response.UserProfileResponse
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
import com.example.cf_sdk.defination.request.UserProfileParameter
import com.example.cf_sdk.changebankapi.parameter.member.VerifyOutOfWalletParameters
import com.example.cf_sdk.changebankapi.parameter.member.VerifyPhoneCodeParameters
import com.example.cf_sdk.changebankapi.parameter.validation.EmailValidationParameters
import com.example.cf_sdk.changebankapi.parameter.validation.PhoneValidationParameters
import com.example.cf_sdk.defination.response.ChangebankResponse
import com.example.cf_sdk.changebankapi.response.DocumentUploadResponse
import com.example.cf_sdk.changebankapi.response.IdscanResponse
import com.example.cf_sdk.changebankapi.response.VerifyOowResponse
import com.example.cf_sdk.changebankapi.source.remote.MemberDatasource
import com.example.cf_sdk.defination.request.AccessTokenParameter
import com.example.cf_sdk.defination.request.AuthCodeParameter
import com.example.cf_sdk.defination.response.AuthCodeResponse

import com.google.common.base.Optional
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import java.io.File
import java.net.HttpURLConnection

/**
 *
 * Member repository is in charge of choosing the correct [MemberDatasource]
 */
class MemberRepository(
    private val mRemoteDatasource: MemberDatasource,
    private val mCacheDatasource: MemberDatasource
) : MemberDatasource {
    private val saveUserProfile: Consumer<UserProfileResponse> =
        Consumer { userProfileResponse -> saveUserProfile(userProfileResponse) }

    override fun sendPhoneCode(
        sendPhoneCodeParameters: SendPhoneCodeParameters?
    ): Single<ChangebankResponse?>? {
        return mRemoteDatasource.sendPhoneCode(sendPhoneCodeParameters)
    }

    override fun verifyPhoneCode(
        verifyPhoneCodeParameters: VerifyPhoneCodeParameters?
    ): Completable? {
        return mRemoteDatasource.verifyPhoneCode(verifyPhoneCodeParameters)
    }

    override fun createMember(createMemberParameters: CreateMemberParameters?): Single<Session?>? {
        return mRemoteDatasource.createMember(createMemberParameters)
    }

    override fun addAddress(addAddressParameters: AddAddressParameters?): Completable? {
        return mRemoteDatasource.addAddress(addAddressParameters)
            ?.doOnComplete(Action { mCacheDatasource.addAddress(addAddressParameters) })
    }

    override fun addMemberInfoAndAddress(addMemberInfoAndAddressParameters: AddMemberInfoAndAddressParameters?): Single<MemberDetails?>? {
        return mRemoteDatasource.addMemberInfoAndAddress(addMemberInfoAndAddressParameters)
            ?.doOnSuccess(Consumer { memberDetails ->
                mCacheDatasource.saveMemberDetails(
                    memberDetails
                )
            })
    }

    override fun addConfidential(acp: AddConfidentialParameters?): Single<Optional<OowQuestions?>?>? {
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
        return mRemoteDatasource.addConfidential(acp)
    }

    override fun saveOowQuestions(oowQuestions: OowQuestions?): Completable? {
        return mCacheDatasource.saveOowQuestions(oowQuestions)
    }

    override fun phoneAvailability(pvp: PhoneValidationParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.phoneAvailability(pvp)
    }

    override fun emailAvailability(evp: EmailValidationParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.emailAvailability(evp)
    }

    override fun getCardToCardAccountDetails(cardToCardTransferParameters: CardToCardTransferParameters?): Single<MemberDetails?>? {
        return mRemoteDatasource.getCardToCardAccountDetails(cardToCardTransferParameters)
    }

    override fun addCheckDeposit(checkDepositParam: CheckDepositParam?): Single<MemberDetails?>? {
        return mRemoteDatasource.addCheckDeposit(checkDepositParam)
    }

    override fun getMemberDetails(parameters: MemberDetailParameters?): Single<Optional<MemberDetails?>?>? {
        //Check cache first then remote
        val cache = mCacheDatasource.getMemberDetails(parameters)
        val remote = mRemoteDatasource.getMemberDetails(parameters)
            ?.doOnSuccess(Consumer { memberDetailsOptional ->
                if (memberDetailsOptional?.isPresent == true) {
                    saveMemberDetails(memberDetailsOptional.get())
                }
            })
        return Single
            .concat(cache, remote)
            .filter { memberDetailsOptional -> memberDetailsOptional.isPresent }
            .firstOrError()
    }

    override fun verifyOowQuestion(verifyOutOfWalletParameters: VerifyOutOfWalletParameters?): Single<VerifyOowResponse?>? {
        return mRemoteDatasource.verifyOowQuestion(verifyOutOfWalletParameters)
    }

    override fun refreshOowQuestions(refreshOowParameters: RefreshOowParameters?): Single<OowQuestions?>? {
        return mRemoteDatasource.refreshOowQuestions(refreshOowParameters)
    }

    override fun getAgreement(agreementParameters: AgreementParameters?): Single<File?>? {
        return mRemoteDatasource.getAgreement(agreementParameters)
    }

    override fun saveMemberDetails(memberDetails: MemberDetails?) {
        mCacheDatasource.saveMemberDetails(memberDetails)
    }

    override fun saveUserProfile(userProfileResponse: UserProfileResponse?) {
        mCacheDatasource.saveUserProfile(userProfileResponse)
    }

    override fun clearMemberDetailsCache() {
        mCacheDatasource.clearMemberDetailsCache()
    }

    override fun clearAllMemberDatasourceCache() {
        mCacheDatasource.clearAllMemberDatasourceCache()
    }

    override fun setESignAgreementAsRead(setESignAgreementAcceptedParameters: SetESignAgreementAcceptedParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.setESignAgreementAsRead(setESignAgreementAcceptedParameters)
    }

    override fun forgotPassword(forgotPasswordParameters: ForgotPasswordParameters?): Single<Session?>? {
        return mRemoteDatasource.forgotPassword(forgotPasswordParameters)
    }

    override fun resetPassword(resetPasswordParameters: ResetPasswordParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.resetPassword(resetPasswordParameters)
    }

    override fun submitIdscan(idscanParameters: IdscanParameters?): Single<IdscanResponse?>? {
        return mRemoteDatasource.submitIdscan(idscanParameters)
    }

    override fun uploadProfilePicture(addProfilePictureParameters: AddProfilePictureParameters?): Single<FileResponse?>? {
        return mRemoteDatasource.uploadProfilePicture(addProfilePictureParameters)
            ?.doOnSuccess(Consumer { fileResponse -> saveImageDocument(fileResponse) })
    }

    override fun updateMemberPhone(verifyPhoneCodeParameters: VerifyPhoneCodeParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.updateMemberPhone(verifyPhoneCodeParameters)!!
            .onErrorResumeNext(object : Function<Throwable?, SingleSource<out ChangebankResponse>> {
                @Throws(Exception::class)


                override fun apply(t: Throwable): SingleSource<out ChangebankResponse> {
                    if (t is NoSuchElementException) {
                        val error =
                            ChangebankResponse()
                        error.httpCode = HttpURLConnection.HTTP_NO_CONTENT
                        error.setMessage("No Content")
                        mCacheDatasource.updateMemberPhone(verifyPhoneCodeParameters)
                        return Single.error(error)
                    }
                    return Single.error(t)
                }
            })
            .doOnSuccess { mCacheDatasource.updateMemberPhone(verifyPhoneCodeParameters) }
    }

    override fun updateEmailAddress(updateEmailAddressParameters: UpdateEmailAddressParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.updateEmailAddress(updateEmailAddressParameters)!!
            .onErrorResumeNext(object : Function<Throwable?, SingleSource<out ChangebankResponse>> {
                @Throws(Exception::class)
                override fun apply(t: Throwable): SingleSource<out ChangebankResponse> {
                    if (t is NoSuchElementException) {
                        val error =
                            ChangebankResponse()
                        error.httpCode = HttpURLConnection.HTTP_NO_CONTENT
                        error.setMessage("No Content")
                        mCacheDatasource.updateEmailAddress(updateEmailAddressParameters)
                        return Single.error(error)
                    }
                    return Single.error(t)
                }
            })
            .doOnSuccess { mCacheDatasource.updateEmailAddress(updateEmailAddressParameters) }
    }

    override fun getDocumentById(documentParameters: DocumentParameters?): Single<Optional<DocumentApiResponse?>?>? {

        //Check cache first then remote
        val cache = mCacheDatasource.getDocumentById(documentParameters)
        val remote = mRemoteDatasource.getDocumentById(documentParameters)
            ?.doOnSuccess(Consumer { documentApiResponseOptional ->
                if (documentApiResponseOptional?.isPresent == true) {
                    saveDocument(documentApiResponseOptional.get())
                }
            })
        return Single
            .concat(cache, remote)
            .filter { documentApiResponseOptional -> documentApiResponseOptional.isPresent }
            .firstOrError()
    }

    override fun saveDocument(documentApiResponse: DocumentApiResponse?) {
        mCacheDatasource.saveDocument(documentApiResponse)
    }

    override fun getDocumentImageById(documentParameters: DocumentParameters?): Single<out Optional<out FileResponse?>?> {
        //Check cache first then remote
        val cache = mCacheDatasource.getDocumentImageById(documentParameters)
        val remote = mRemoteDatasource.getDocumentImageById(documentParameters)
            ?.doOnSuccess(Consumer { fileResponseOptional ->
                if (fileResponseOptional?.isPresent == true) {
                    saveImageDocument(fileResponseOptional.get())
                }
            })
        return Single
            .concat(cache, remote)
            .filter { fileResponseOptional -> fileResponseOptional.isPresent }
            .firstOrError()
    }

    override fun getProfilePicture(parameters: ProfilePictureParameters?): Single<out Optional<out FileResponse?>?> {
        //Check cache first then remote
        val cache = mCacheDatasource.getProfilePicture(parameters)
        val remote = mRemoteDatasource.getProfilePicture(parameters)
            ?.doOnSuccess(Consumer { fileResponseOptional ->
                if (fileResponseOptional?.isPresent == true) {
                    saveImageDocument(fileResponseOptional.get())
                }
            })
        return Single
            .concat(cache, remote)
            .filter { fileResponseOptional -> fileResponseOptional.isPresent }
            .firstOrError()
    }

    override fun saveImageDocument(file: FileResponse?) {
        mCacheDatasource.saveImageDocument(file)
    }

    override fun log(logParameters: LogParameters?): Single<ChangebankResponse?>? {
        return mRemoteDatasource.log(logParameters)
    }

    override fun getVersionConfig(settingsParameter: SettingsParameter?): Single<VersionConfig?>? {
        return mRemoteDatasource.getVersionConfig(settingsParameter)
    }

    override fun getAuthCode(authCodeParameter: AuthCodeParameter?): Single<AuthCodeResponse?>? {
        return mRemoteDatasource.getAuthCode(authCodeParameter)
    }


    override fun getUserProfile(parameters: UserProfileParameter?): Single<UserProfileResponse?>? {
        return mRemoteDatasource.getUserProfile(parameters)
    }

    override fun getCardDetails(cardDetailParameter: CardDetailParameter?): Single<CardDetailResponse?>? {
        return mRemoteDatasource.getCardDetails(cardDetailParameter)
    }

    override fun getCardRelation(cardParameters: SubCardRelationParameter?): Single<List<ProductDetailsReponseItem?>?>? {
        return mRemoteDatasource.getCardRelation(cardParameters)
    }

    override fun getRsaPublicKey(userProfileParameter: UserProfileParameter?): Single<RSAPublicKeyResponse?>? {
        return mRemoteDatasource.getRsaPublicKey(userProfileParameter)
    }

    override fun getActivateCard(activateCardParameter: ActivateCardParameter?): Single<ActivateCardResponse?>? {
        return mRemoteDatasource.getActivateCard(activateCardParameter)
    }

    override fun forgotUsername(
        forgotUsernameParameters: ForgotUsernameParameters?,
        appId: String?
    ): Completable? {
        return mRemoteDatasource.forgotUsername(forgotUsernameParameters, appId)
    }

    override fun batchListConfirm(createBatchListMemberParameters: CreateBatchListMemberParameters?): Completable? {
        return mRemoteDatasource.batchListConfirm(createBatchListMemberParameters)
    }

    override fun getAdminNumberAvailability(createBatchListMemberParameters: CreateBatchListMemberParameters?): Completable? {
        return mRemoteDatasource.getAdminNumberAvailability(createBatchListMemberParameters)
    }

    override fun batchListCreateMember(createBatchListMemberParameters: CreateBatchListMemberParameters?): Single<Session?>? {
        return mRemoteDatasource.batchListCreateMember(createBatchListMemberParameters)
    }

    override fun deleteImageDocument(documentParameters: ProfilePictureParameters?): Completable? {
        return mRemoteDatasource.deleteImageDocument(documentParameters)
            ?.doOnComplete(Action { mCacheDatasource.deleteImageDocument(documentParameters) })
    }

    // upload ID scan docs font,back selfie images
    override fun uploadDocuments(idScanDocuments: UploadDocumentsParameters?): Single<DocumentUploadResponse?>? {
        return mRemoteDatasource.uploadDocuments(idScanDocuments)
    }
}