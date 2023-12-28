package com.example.cf_sdk.logic.changebankapi.network.api;

import com.example.cf_sdk.logic.changebankapi.Endpoints;
import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchTransfer;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.model.account.Card;
import com.example.cf_sdk.logic.changebanksdk.model.account.CardToCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.account.CreateNewSubCardResponse;
import com.example.cf_sdk.logic.changebanksdk.model.account.DepositedCheck;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardActivationParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CheckDepositedParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.ConfirmAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateMicroDepositAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateSubCardParameter;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.SubmitMfaOrNewBankCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.UpdateCardStatusParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.VerifyAchTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.VerifyMicroDepositAchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AccountBalanceResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchAccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchHistoryResponse;
import com.example.cf_sdk.logic.changebanksdk.response.AchTransferResponse;
import com.example.cf_sdk.logic.changebanksdk.response.BankCredentialsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.BanksApiResponse;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 *
 * Retrofit interface to connect with Account API.
 */

public interface AccountApi {
    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<Account> getAccountById(@HeaderMap Map<String, String> headers,
                                   @Path("accountNumber") String accountId);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_ACCOUNTS)
    @Headers("Content-Type: application/json")
    Single<AccountsApiResponse> getMemberAccounts(@HeaderMap Map<String, String> headers,
                                                  @Path("customerNumber") String memberId);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_ACH_ACCOUNTS)
    @Headers("Content-Type: application/json")
    Single<AchAccountsApiResponse> getMemberAchAccounts(@HeaderMap Map<String, String> headers,
                                                        @Path("accountNumber") String accountNumber);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_ACCOUNT_BALANCE)
    @Headers("Content-Type: application/json")
    Single<AccountBalanceResponse> getAccountBalance(@HeaderMap Map<String, String> headers,
                                                     @Path("account_id") String accountId);

    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.ACH_TRANSFER)
    @Headers("Content-Type: application/json")
    Single<AchTransferResponse> postAchTransfer(@HeaderMap Map<String, String> headers,
                                                @Path("bankAccID")String bankAccId,
                                                @Body AchTransferParameters achTransferParameters);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_ACH_HISTORY)
    @Headers("Content-Type: application/json")
    Single<AchHistoryResponse> getMemberAchHistory(@HeaderMap Map<String, String> headers,
                                                   @Path("account") String memberId,
                                                   @Query("dateFrom") String dateFrom, @Query("dateTo") String dateTo);

    @DELETE(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.ACH_TRANSFER_DELETE)
    @Headers("Content-Type: application/json")
    Single<AchTransfer> deleteAchTransfer(@HeaderMap Map<String, String> headers,
                                          @Path("transfer_id") String achTransferId);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_BANKS)
    @Headers("Content-Type: application/json")
    Single<BanksApiResponse> getBanks(@HeaderMap Map<String, String> headers);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_BANK_BY_ID)
    @Headers("Content-Type: application/json")
    Single<Bank> getBank(@HeaderMap Map<String, String> headers, @Path("bankId") String bankId);

    @POST(ApiConfig.MEMBER_BASE_ENDPOINT + Endpoints.Account.CHECK_DEPOSITED_LIST)
    @Headers("Content-Type: application/json")
    Single<List<DepositedCheck>> getDepositedCheck(@HeaderMap Map<String, String> headers, @Body CheckDepositedParameters checkDepositedParameters);


    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.ACH_VERIFICATION)
    @Headers("Content-Type: application/json")
    Single<AchTransfer> verifyAchTransfer(@HeaderMap Map<String, String> headers,
                                          @Body VerifyAchTransferParameters verifyAchTransferParameters);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_BANK_CREDENTIALS)
    @Headers("Content-Type: application/json")
    Single<BankCredentialsApiResponse> getBankCredentials(@HeaderMap Map<String, String> headers,
                                                          @Path("bankId") String bankId);

    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.CREATE_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<AchAccount> createAchAccount(@HeaderMap Map<String, String> headers,
                                        @Body CreateAchAccountParameters createAchAccountParameters);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<AchAccount> getAchAccount(@HeaderMap Map<String, String> headers,
                                     @Path("account_id") String achAccountId);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.VALIDATE_ACCOUNT_NUMBER)
    @Headers("Content-Type: application/json")
    Single<AchAccount> getAcountToAcount(@HeaderMap Map<String, String> headers,
                                         @Path("account_id") String accountId,
                                         @Query("adminNumber") String adminNumber,
                                         @Query("amount") double amount,
                                         @Query("last4Phone") String last$Digit);

    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.ACOOUTN_TO_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<CardToCardResponse> cardToCardTransfer(@HeaderMap Map<String, String> headers,
                                                  @Path("account")String account,
                                                  @Path("toAccount")String toAccount,
                                                  @Body CardToCardTransferParameters cardToCardTransferParameters);


    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_MFA_OR_BANK_CREDENTIALS)
    @Headers("Content-Type: application/json")
    Single<BankCredentialsApiResponse> getMfaOrNewBankCredentials(@HeaderMap Map<String, String> headers,
                                                                  @Path("account_id") String achAccountId);

    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.SUBMIT_MFA_OR_BANK_CREDENTIALS)
    @Headers("Content-Type: application/json")
    Single<AchAccount> submitMfaOrNewBankCredentials(@HeaderMap Map<String, String> headers,
                                                     @Path("account_id") String achAccountId,
                                                     @Body SubmitMfaOrNewBankCredentialsParameters submitMfaOrNewBankCredentialsParameters);

    @DELETE(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.DELETE_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<AchAccount> deleteAchAccount(@HeaderMap Map<String, String> headers,
                                        @Path("bankAccID") String achAccountId);

    @PUT(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.LINK_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> linkAchAccount(@HeaderMap Map<String, String> headers,
                                              @Path("account_id") String achAccountId,
                                              @Body ConfirmAchAccountParameters confirmAchAccountParameters);

    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.CREATE_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<AchAccount> createMicroDepositAccount(@HeaderMap Map<String, String> headers,
                                                 @Body CreateMicroDepositAchAccountParameters createMicroDepositAchAccountParameters,@Path("accountNumber")String accountNumber);

    @PUT(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.VERIFY_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<AchAccount> verifyMicroDepositAccount(@HeaderMap Map<String, String> headers,
                                                 @Path("bankAccID") String achAccountId, @Body VerifyMicroDepositAchAccountParameters verifyMicroDepositAchAccountParameters);

    @PUT(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.RELINK_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<AchAccount> reLinkAchAccount(@HeaderMap Map<String, String> headers,
                                        @Path("account_id") String achAccountId,
                                        @Body Parameters parameters);

    @PUT(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.UNLINK_ACH_ACCOUNT)
    @Headers("Content-Type: application/json")
    Single<AchAccount> unLinkAchAccount(@HeaderMap Map<String, String> headers,
                                        @Path("account_id") String achAccountId);


    @PUT(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.REFRESH_ACH_ACCOUNTS)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> refreshAchAccounts(@HeaderMap Map<String, String> headers,
                                                  @Query("memberId") String memberId);

    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.POST_CARD_ACTIVATION)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> activateCard(@HeaderMap Map<String, String> headers,
                                            @Path("account_id") String cardId,
                                            @Body CardActivationParameters cardActivationParameters);

    @PUT(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.POST_CARD_UPDATE_PIN)
    @Headers("Content-Type: application/json")
    Single<ChangebankResponse> updateCardPan(@HeaderMap Map<String, String> headers,
                                             @Path("cardNumber") String cardId,
                                             @Body CardActivationParameters cardActivationParameters);

    @PUT(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.UPDATE_CARD_STATUS)
    @Headers("Content-Type: application/json")
    Single<Card> updateCardStatus(@HeaderMap Map<String, String> headers,
                                  @Path("cardNumber") String cardId,
                                  @Body UpdateCardStatusParameters updateCardStatusParameters);

    @Streaming
    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.GET_DIRECT_DEPOSIT_FORM)
    Single<Response<ResponseBody>> getDirectDepositForm(@HeaderMap Map<String, String> headers,
                                                        @Path("account") String accountId);

    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.CARD_BY_ID)
    Single<Card> getCard(@HeaderMap Map<String, String> headers,
                         @Path("cardId") String cardId);


    @GET(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.CARD_DETAILS_BY_ID)
    Single<Card> getCardByCardId(@HeaderMap Map<String, String> headers,
                                 @Path("cardId") String cardId);


    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.REQUEST_A_PHYSICAL_CARD)
    Single<ChangebankResponse> requestPhysicalCard(@HeaderMap Map<String,String> headers,
                                                       @Path("card_id") String cardID);

    @POST(ApiConfig.ACCOUNT_BASE_ENDPOINT + Endpoints.Account.CREATE_SUB_CARD)
    Single<CreateNewSubCardResponse> createNewSubCard(@HeaderMap Map<String, String> headers,
                                                      @Path("cardNumber") String cardId, @Body CreateSubCardParameter createSubCardParameter);
}
