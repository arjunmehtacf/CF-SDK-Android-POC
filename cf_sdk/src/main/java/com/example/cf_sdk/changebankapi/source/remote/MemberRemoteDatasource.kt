package com.example.cf_sdk.changebankapi.source.remote


import com.example.cf_sdk.changebankapi.Endpoints
import com.example.cf_sdk.changebankapi.model.FileResponse
import com.example.cf_sdk.changebankapi.model.Session
import com.example.cf_sdk.changebankapi.model.account.ProductDetailsReponseItem
import com.example.cf_sdk.changebankapi.model.member.ActivateCardResponse
import com.example.cf_sdk.changebankapi.model.member.CardDetailResponse
import com.example.cf_sdk.changebankapi.model.member.DocumentApiResponse
import com.example.cf_sdk.changebankapi.model.member.MemberDetails
import com.example.cf_sdk.changebankapi.model.member.RSAPublicKeyResponse
import com.example.cf_sdk.changebankapi.model.member.UploadProfilePictureApiResponse
import com.example.cf_sdk.changebankapi.model.member.UserProfileResponse
import com.example.cf_sdk.defination.response.version.VersionConfig
import com.example.cf_sdk.changebankapi.model.oow.OowQuestions
import com.example.cf_sdk.changebankapi.network.api.MemberApi
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
import com.example.cf_sdk.changebankapi.sdkcore.CFSDKConstant
import com.google.common.base.Optional
import com.google.common.io.BaseEncoding
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Okio
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 *
 * Remote datasource to call Member endpoints.
 */
class MemberRemoteDatasource(private val mMemberApi: MemberApi, private val mCacheDirectory: File) :
    MemberDatasource {
    override fun sendPhoneCode(sendPhoneCodeParameters: SendPhoneCodeParameters?): Single<ChangebankResponse?>? {
        return mMemberApi.sendPhoneCode(
            sendPhoneCodeParameters!!.headers,
            sendPhoneCodeParameters
        )
    }

    override fun verifyPhoneCode(verifyPhoneCodeParameters: VerifyPhoneCodeParameters?): Completable? {
        return mMemberApi.verifyPhoneCode(
            verifyPhoneCodeParameters!!.headers,
            verifyPhoneCodeParameters
        )
    }

    override fun createMember(
        createMemberParameters: CreateMemberParameters?,
    ): Single<Session?>? {
        return mMemberApi.createMember(createMemberParameters!!.headers, createMemberParameters)
    }

    override fun addAddress(addAddressParameters: AddAddressParameters?): Completable? {
        return mMemberApi.addAddress(
            addAddressParameters!!.headers,
            addAddressParameters.memberId,
            addAddressParameters
        )
    }

    override fun addMemberInfoAndAddress(addMemberInfoAndAddressParameters: AddMemberInfoAndAddressParameters?): Single<MemberDetails?>? {
        return mMemberApi.updateMember(
            addMemberInfoAndAddressParameters!!.headers,
            addMemberInfoAndAddressParameters.memberId,
            addMemberInfoAndAddressParameters
        )
    }

    override fun addConfidential(addConfidentialParameters: AddConfidentialParameters?): Single<Optional<OowQuestions?>?>? {
        return mMemberApi.addConfidential(
            addConfidentialParameters!!.headers,
            addConfidentialParameters.memberId,
            addConfidentialParameters
        )
            .map(Function { oowQuestions -> Optional.of(oowQuestions) })
    }

    override fun saveOowQuestions(oowQuestions: OowQuestions?): Completable? {
        throw UnsupportedOperationException(
            "MemberRemoteDatasource not support saveOowQuestions"
        )
    }

    override fun phoneAvailability(
        phoneValidationParameters: PhoneValidationParameters?,
    ): Single<ChangebankResponse?>? {
        return mMemberApi.phoneAvailability(
            phoneValidationParameters!!.headers,
            phoneValidationParameters
        )
    }

    override fun getCardToCardAccountDetails(cardToCardTransferParameters: CardToCardTransferParameters?): Single<MemberDetails?>? {
        return mMemberApi.getAcountToAcount(
            cardToCardTransferParameters!!.headers,
            cardToCardTransferParameters.getmAdminNumber(),
            cardToCardTransferParameters.name,
            cardToCardTransferParameters.last4Phone
        )
    }

    override fun addCheckDeposit(checkDepositParam: CheckDepositParam?): Single<MemberDetails?>? {
        return mMemberApi.checkDeposit(
            checkDepositParam!!.headers,
            checkDepositParam.memberId,
            checkDepositParam
        )
    }

    override fun getMemberDetails(
        stringParameters: MemberDetailParameters?,
    ): Single<Optional<MemberDetails?>?>? {
        return mMemberApi.memberDetails(
            stringParameters!!.headers,
            stringParameters.customerNumber
        )
            .map(Function { memberDetails -> Optional.of(memberDetails) })
    }

    override fun verifyOowQuestion(verifyOutOfWalletParameters: VerifyOutOfWalletParameters?): Single<VerifyOowResponse?>? {
        return mMemberApi.verifyOowQuestions(
            verifyOutOfWalletParameters!!.headers,
            verifyOutOfWalletParameters.memberId,
            verifyOutOfWalletParameters
        )
    }

    override fun refreshOowQuestions(refreshOowParameters: RefreshOowParameters?): Single<OowQuestions?>? {
        return mMemberApi.refreshOowQuestions(
            refreshOowParameters!!.headers,
            refreshOowParameters.memberId
        )
    }

    override fun getAgreement(agreementParameters: AgreementParameters?): Single<File?>? {
        return mMemberApi.getAgreement(
            agreementParameters!!.headers,
            agreementParameters.agreementType.agreementTypeName
        )
            .flatMap(Function<Response<ResponseBody?>, SingleSource<out File>> { responseBodyResponse ->
                val fileName = agreementParameters.agreementType.agreementTypeName
                try {
                    val file: File
                    file = File.createTempFile(fileName, ".pdf", mCacheDirectory)
                    file.deleteOnExit()
                    val sink = Okio.buffer(Okio.sink(file))
                    // you can access body of response
                    val responseBody = responseBodyResponse.body()
                    if (responseBody != null) {
                        sink.writeAll(responseBody.source())
                        sink.close()
                    } else {
                        return@Function Single.error<File>(Exception("Body is null"))
                    }
                    return@Function Single.just<File>(file)
                } catch (e: IOException) {
                    return@Function Single.error<File>(e)
                }
            })
    }

    override fun saveMemberDetails(memberDetails: MemberDetails?) {
        throw UnsupportedOperationException(
            "MemberRemoteDatasource not support saveMemberDetails"
        )
    }

    override fun saveUserProfile(userProfileResponse: UserProfileResponse?) {
        UnsupportedOperationException(
            "MemberRemoteDatasource not support saveUserProfile"
        )
    }

    override fun clearMemberDetailsCache() {
        throw UnsupportedOperationException(
            "MemberRemoteDatasource not support clearMemberDetailsCache"
        )
    }

    override fun clearAllMemberDatasourceCache() {
        throw UnsupportedOperationException(
            "MemberRemoteDatasource not support clearAllMemberDatasourceCache"
        )
    }

    override fun setESignAgreementAsRead(setESignAgreementAcceptedParameters: SetESignAgreementAcceptedParameters?): Single<ChangebankResponse?>? {
        return mMemberApi.setESignAgreementAsRead(
            setESignAgreementAcceptedParameters!!.headers,
            setESignAgreementAcceptedParameters.memberId,
            setESignAgreementAcceptedParameters
        )
    }

    override fun forgotPassword(forgotPasswordParameters: ForgotPasswordParameters?): Single<Session?>? {
        return mMemberApi.forgotPassword(
            forgotPasswordParameters!!.headers,
            forgotPasswordParameters
        )
    }

    override fun resetPassword(resetPasswordParameters: ResetPasswordParameters?): Single<ChangebankResponse?>? {
        return mMemberApi.resetPassword(resetPasswordParameters!!.headers, resetPasswordParameters)
    }

    override fun uploadProfilePicture(addProfilePictureParameters: AddProfilePictureParameters?): Single<FileResponse?>? {
        val requestBody = RequestBody
            .create(MediaType.parse("image/png"), addProfilePictureParameters!!.body)
        return mMemberApi.uploadProfilePicture(
            addProfilePictureParameters.headers,
            requestBody
        )
            .flatMap(Function<UploadProfilePictureApiResponse, SingleSource<out FileResponse>> { uploadProfilePictureApiResponse ->
                val fileName = uploadProfilePictureApiResponse.documentId
                try {
                    val file: File
                    file = File.createTempFile(fileName, ".png", mCacheDirectory)
                    file.deleteOnExit()
                    if (addProfilePictureParameters.data != null) {
                        val pdfAsBytes = BaseEncoding.base64().decode(
                            addProfilePictureParameters.data
                        )
                        val os: FileOutputStream
                        if (pdfAsBytes != null) {
                            os = FileOutputStream(file, false)
                            os.write(pdfAsBytes)
                            os.flush()
                            os.close()
                        } else {
                            return@Function Single.error<FileResponse>(Exception("Body is null"))
                        }
                    } else {
                        return@Function Single.error<FileResponse>(Exception("Image Data is null"))
                    }
                    return@Function Single.just<FileResponse>(
                        FileResponse.Create(
                            file,
                            uploadProfilePictureApiResponse.documentId
                        )
                    )
                } catch (e: Exception) {
                    return@Function Single.error<FileResponse>(e)
                }
            })
    }

    override fun updateMemberPhone(verifyPhoneCodeParameters: VerifyPhoneCodeParameters?): Single<ChangebankResponse?>? {
        return mMemberApi.updateMemberPhone(
            verifyPhoneCodeParameters!!.headers,
            verifyPhoneCodeParameters
        )
    }

    override fun updateEmailAddress(updateEmailAddressParameters: UpdateEmailAddressParameters?): Single<ChangebankResponse?>? {
        return mMemberApi.updateEmailAddress(
            updateEmailAddressParameters!!.headers,
            updateEmailAddressParameters
        )
    }

    override fun deleteImageDocument(documentParameters: ProfilePictureParameters?): Completable? {
        return mMemberApi.deleteProfilePicture(documentParameters!!.headers)
    }

    override fun getDocumentById(documentParameters: DocumentParameters?): Single<Optional<DocumentApiResponse?>?>? {
        return mMemberApi.getDocumentById(
            documentParameters!!.headers,
            documentParameters.documentId,
            documentParameters.memberId
        )
            .map(Function { documentApiResponse -> Optional.of(documentApiResponse) })
    }

    override fun saveDocument(documentApiResponse: DocumentApiResponse?) {
        throw UnsupportedOperationException(
            "MemberRemoteDatasource not support saveDocument"
        )
    }

    override fun getDocumentImageById(documentParameters: DocumentParameters?): Single<out Optional<out FileResponse?>?> {
        documentParameters
            ?: return Single.error<Optional<FileResponse>>(IllegalArgumentException("documentParameters cannot be null"))

        return mMemberApi.getDocumentImageById(
            documentParameters.headers,
            documentParameters.documentId,
            documentParameters.memberId
        ).flatMap { responseBodyResponse ->
            if (responseBodyResponse.isSuccessful) {
                val fileName = documentParameters.documentId
                try {
                    val file: File = File.createTempFile(fileName, ".png", mCacheDirectory)
                    file.deleteOnExit()
                    val sink = Okio.buffer(Okio.sink(file))

                    val responseBody = responseBodyResponse.body()
                    if (responseBody != null) {
                        sink.writeAll(responseBody.source())
                        sink.close()
                    } else {
                        return@flatMap Single.error<Optional<FileResponse>>(Exception("Body is null"))
                    }

                    return@flatMap Single.just(
                        Optional.of(
                            FileResponse.Create(file, documentParameters.documentId)
                        )
                    )
                } catch (e: IOException) {
                    return@flatMap Single.error<Optional<FileResponse>>(e)
                }
            } else {
                return@flatMap Single.error<Optional<FileResponse>>(Exception("Request not successful: ${responseBodyResponse.code()}"))
            }
        }
    }


    override fun getProfilePicture(parameters: ProfilePictureParameters?): Single<out Optional<out FileResponse?>?> {
        parameters
            ?: return Single.error<Optional<FileResponse>>(IllegalArgumentException("parameters cannot be null"))

        return mMemberApi.getProfilePicture(parameters.headers).flatMap { responseBodyResponse ->
            if (responseBodyResponse.isSuccessful) {
                val fileName = parameters.memberId
                try {
                    val file: File = File.createTempFile(fileName, ".png", mCacheDirectory)
                    file.deleteOnExit()
                    val sink = Okio.buffer(Okio.sink(file))

                    val responseBody = responseBodyResponse.body()
                    if (responseBody != null) {
                        sink.writeAll(responseBody.source())
                        sink.close()
                    } else {
                        return@flatMap Single.error<Optional<FileResponse>>(Exception("Body is null"))
                    }

                    return@flatMap Single.just(
                        Optional.of(
                            FileResponse.Create(file, parameters.memberId)
                        )
                    )
                } catch (e: IOException) {
                    return@flatMap Single.error<Optional<FileResponse>>(e)
                }
            } else {
                return@flatMap Single.error<Optional<FileResponse>>(Exception("Request not successful: ${responseBodyResponse.code()}"))
            }
        }
    }

    override fun saveImageDocument(file: FileResponse?) {
        throw UnsupportedOperationException(
            "MemberRemoteDatasource not support saveImageDocument"
        )
    }

    override fun log(logParameters: LogParameters?): Single<ChangebankResponse?>? {
        return mMemberApi.log(logParameters!!.headers, logParameters)
    }

    override fun getVersionConfig(settingsParameter: SettingsParameter?): Single<VersionConfig?>? {
        return mMemberApi.getVersionConfig(
            settingsParameter?.headers?.get(CFSDKConstant.KEY_BASE_URL) + Endpoints.Member.GET_VERSION_CONFIG,
            settingsParameter?.os,
            settingsParameter?.applicationId
        )
    }

    override fun emailAvailability(evp: EmailValidationParameters?): Single<ChangebankResponse?>? {
        return mMemberApi.emailAvailability(evp!!.headers, evp)
    }

    override fun submitIdscan(idscanParameters: IdscanParameters?): Single<IdscanResponse?>? {
        return mMemberApi.submitIdscan(
            idscanParameters!!.headers,
            idscanParameters.memberId,
            idscanParameters
        )
    }

    override fun getUserProfile(userProfileParameter: UserProfileParameter?): Single<UserProfileResponse?>? {
        return mMemberApi.getUserProfile(userProfileParameter!!.headers)
    }

    override fun getCardDetails(cardDetailParameter: CardDetailParameter?): Single<CardDetailResponse?>? {
        return mMemberApi.getCardDetails(
            cardDetailParameter!!.headers,
            cardDetailParameter.cardNumber,
            true
        )
    }

    override fun getCardRelation(cardParameters: SubCardRelationParameter?): Single<List<ProductDetailsReponseItem?>?>? {
        return mMemberApi.getSubCardRelation(cardParameters!!.headers, cardParameters.cardProduct)
    }

    override fun getRsaPublicKey(userProfileParameter: UserProfileParameter?): Single<RSAPublicKeyResponse?>? {
        return mMemberApi.getRSAPublicKey(userProfileParameter!!.headers)
    }

    override fun getActivateCard(activateCardParameter: ActivateCardParameter?): Single<ActivateCardResponse?>? {
        return mMemberApi.getAccountActivate(
            activateCardParameter!!.headers,
            activateCardParameter.cardNumber,
            activateCardParameter
        )
    }

    override fun forgotUsername(
        forgotUsernameParameters: ForgotUsernameParameters?,
        appId: String?,
    ): Completable? {
        return mMemberApi.forgotUsername(
            forgotUsernameParameters!!.headers,
            forgotUsernameParameters,
            appId
        )
    }

    override fun batchListConfirm(createBatchListMemberParameters: CreateBatchListMemberParameters?): Completable? {
        return mMemberApi.batchListConfirm(
            createBatchListMemberParameters!!.headers,
            createBatchListMemberParameters.adminNumber,
            createBatchListMemberParameters.dob,
            createBatchListMemberParameters.last4SSN
        )
    }

    override fun getAdminNumberAvailability(createBatchListMemberParameters: CreateBatchListMemberParameters?): Completable? {
        return mMemberApi.getAdminNumberAvailability(
            createBatchListMemberParameters!!.headers,
            createBatchListMemberParameters
        )
    }

    override fun batchListCreateMember(createBatchListMemberParameters: CreateBatchListMemberParameters?): Single<Session?>? {
        return mMemberApi.batchListCreateMember(
            createBatchListMemberParameters!!.headers,
            createBatchListMemberParameters
        )
    }

    override fun uploadDocuments(uploadDocuments: UploadDocumentsParameters?): Single<DocumentUploadResponse?>? {
        return mMemberApi.uploadDocuments(
            uploadDocuments!!.headers,
            uploadDocuments.memberId,
            uploadDocuments
        )
    }
}