package com.example.cf_sdk.changebankapi.model.transaction;


import com.example.cf_sdk.changebankapi.model.account.Account;
import com.example.cf_sdk.changebankapi.model.account.Card;
import com.example.cf_sdk.changebankapi.model.account.Money;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;

/**
 *
 * Represents a bank transaction.
 */

public class Transaction implements Serializable {

    @SerializedName("status")
    @Expose
    private TransactionStatus transactionStatus = TransactionStatus.UNKNOWN;
    @SerializedName("memberId")
    @Expose
    private Long memberId =0L;
    @SerializedName("member_id")
    @Expose
    private String id = "";
    @SerializedName("displayName")
    @Expose
    private String displayName = "";
    @SerializedName("category")
    @Expose
    private Long category=0L;
    @SerializedName("acquirerCountryCode")
    @Expose
    private String acquirerCountryCode = "";
    @SerializedName("acquirerInstitutionCode")
    @Expose
    private String acquirerInstitutionCode = "";
    @SerializedName("acquireDateEpoch")
    @Expose
    private Long acquireDateEpoch = 0L;
    @SerializedName("acquireDate")
    @Expose
    private String acquireDate = "";
    @SerializedName("authorizationID")
    @Expose
    private String authorizationID = "";
    @SerializedName("availableBalance")
    @Expose
    private Long availableBalance=0L;
    @SerializedName("cardPresentYN")
    @Expose
    private Boolean cardPresentYN=false;
    @SerializedName("processorCategory")
    @Expose
    private ProcessorCategory processorCategory = ProcessorCategory.UNKNOWN;
    @SerializedName("channel")
    @Expose
    private String channel = "";
    @SerializedName("currency")
    @Expose
    private String currency = "";
    @SerializedName("dateEpoch")
    @Expose
    private long dateEpoch=0L;
    @SerializedName("localDate")
    @Expose
    private String date = "";

    public String getRawTime() {
        return time;
    }

    public void setRawTime(String time) {
        this.time = time;
    }

    @SerializedName("localTime")
    @Expose
    private String time = "";


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @SerializedName("localCurrency")
    @Expose
    private String currencyCode = "";


    public void setRawDate(String localDate){
        date = localDate;
    }

    public String getRawDate(){
        return date;
    }

    @SerializedName("description")
    @Expose
    private String description = "";
    @SerializedName("merchantDescription1")
    @Expose
    private String transactionTypeDescription;
    @SerializedName("eventCode")
    @Expose
    private String eventCode = "";
    @SerializedName("exchangeRate")
    @Expose
    private Long exchangeRate=0L;
    @SerializedName("fwdInstitutionCode")
    @Expose
    private Object fwdInstitutionCode;
    @SerializedName("fromAccountRefId")
    @Expose
    private Object fromAccountRefId;
    @SerializedName("mcc")
    @Expose
    private Long mcc=0L;
    @SerializedName("merchantName")
    @Expose
    private String merchantName = "";
    @SerializedName("merchantCity")
    @Expose
    private String merchantCity = "";
    @SerializedName("merchantCode")
    @Expose
    private String merchantCode = "";
    @SerializedName("merchantDetails")
    @Expose
    private String merchantDetails = "";
    @SerializedName("merchantStateCountry")
    @Expose
    private String merchantStateCountry = "";
    @SerializedName("merchantType")
    @Expose
    private Object merchantType;
    @SerializedName("network")
    @Expose
    private String network = "";
    @SerializedName("originalAmount")
    @Expose
    private Long originalAmount=0L;
    @SerializedName("refId")
    @Expose
    private String refId = "";
    @SerializedName("rejectCode")
    @Expose
    private Object rejectCode;
    @SerializedName("cardholderRejectReason")
    @Expose
    private String rejectReason = "";
    @SerializedName("reportDate")
    @Expose
    private Object reportDate;
    @SerializedName("responseCode")
    @Expose
    private String responseCode = "";
    @SerializedName("retrievalReference")
    @Expose
    private String retrievalReferenceNumber = "";
    @SerializedName("settledBalance")
    @Expose
    private Object settledBalance;
    @SerializedName("settlementDateEpoch")
    @Expose
    private Long settlementDateEpoch = 0L;
    @SerializedName("settlementDate")
    @Expose
    private Object settlementDate;
    @SerializedName("source")
    @Expose
    private Object source;
    @SerializedName("authStatus")
    @Expose
    private String authStatus = "";
    @SerializedName("switchSerialNumber")
    @Expose
    private Object switchSerialNumber;
    @SerializedName("sysTraceAuditNumber")
    @Expose
    private String sysTraceAuditNumber;
    @SerializedName("toAccountRefId")
    @Expose
    private Object toAccountRefId;
    @SerializedName("toleranceAmount")
    @Expose
    private Long toleranceAmount=0L;
    @SerializedName("transactionCode")
    @Expose
    private String transactionCode = "";
    @SerializedName("transactionType")
    @Expose
    private String transactionType = "";
    @SerializedName("transmissionDate")
    @Expose
    private Object transmissionDate;
    @SerializedName("transactionAmount")
    @Expose
    private Money transactionAmount;

    public String getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(String amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    @SerializedName("localAmount")
    @Expose
    private String amountTransaction = "";

    @SerializedName("balance")
    @Expose
    private Money balance;
    @SerializedName("bankDateEpoch")
    @Expose
    private Object bankDateEpoch;
    @SerializedName("bankDate")
    @Expose
    private Object bankDate;
    @SerializedName("cancelDate")
    @Expose
    private Object cancelDate;
    @SerializedName("chargeAmount")
    @Expose
    private Object chargeAmount;
    @SerializedName("domInt")
    @Expose
    private String domInt = "";
    @SerializedName("fraudulentReturn")
    @Expose
    private Object fraudulentReturn;
    @SerializedName("originateAmount")
    @Expose
    private Object originateAmount;
    @SerializedName("originateCurrency")
    @Expose
    private Object originateCurrency;
    @SerializedName("parentTransactionId")
    @Expose
    private Object parentTransactionId;
    @SerializedName("provisional")
    @Expose
    private Boolean provisional=false;
    @SerializedName("purseType")
    @Expose
    private Object purseType;
    @SerializedName("refundAmount")
    @Expose
    private Long refundAmount=0L;
    @SerializedName("settlementFlag")
    @Expose
    private Object settlementFlag;
    @SerializedName("surcharge")
    @Expose
    private Long surcharge=0L;
    @SerializedName("accountRefId")
    @Expose
    private String accountRefId = "";
    @SerializedName("accountId")
    @Expose
    private String accountId = "";
    @SerializedName("cardRefId")
    @Expose
    private String cardRefId = "";
    @SerializedName("cardId")
    @Expose
    private String cardId = "";
    @SerializedName("partyMemberId")
    @Expose
    private Object partyMemberId;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setTransactionStatus(TransactionStatus status) {
        this.transactionStatus = status;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getAcquirerCountryCode() {
        return acquirerCountryCode;
    }

    public void setAcquirerCountryCode(String acquirerCountryCode) {
        this.acquirerCountryCode = acquirerCountryCode;
    }

    public String getAcquirerInstitutionCode() {
        return acquirerInstitutionCode;
    }

    public void setAcquirerInstitutionCode(String acquirerInstitutionCode) {
        this.acquirerInstitutionCode = acquirerInstitutionCode;
    }

    public void setAcquireDateEpoch(Long acquireDateEpoch) {
        this.acquireDateEpoch = acquireDateEpoch;
    }

    public String getAcquireDate() {
        return acquireDate;
    }

    public void setAcquireDate(String acquireDate) {
        this.acquireDate = acquireDate;
    }

    public String getAuthorizationID() {
        return authorizationID;
    }

    public void setAuthorizationID(String authorizationID) {
        this.authorizationID = authorizationID;
    }

    public Long getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Long availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Boolean getCardPresentYN() {
        return cardPresentYN;
    }

    public void setCardPresentYN(Boolean cardPresentYN) {
        this.cardPresentYN = cardPresentYN;
    }

    public Object getProcessorCategory() {
        return processorCategory;
    }

    public void setProcessorCategory(ProcessorCategory processorCategory) {
        this.processorCategory = processorCategory;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getDateEpoch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = sdf.parse(getRawDate()+" "+getRawTime());
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateEpoch * 1000;
    }

    public void setDateEpoch(Long dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        if(transactionTypeDescription != null && !transactionTypeDescription.trim().isEmpty()){
            return transactionTypeDescription;
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Long getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Long exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Object getFwdInstitutionCode() {
        return fwdInstitutionCode;
    }

    public void setFwdInstitutionCode(Object fwdInstitutionCode) {
        this.fwdInstitutionCode = fwdInstitutionCode;
    }

    public Object getFromAccountRefId() {
        return fromAccountRefId;
    }

    public void setFromAccountRefId(Object fromAccountRefId) {
        this.fromAccountRefId = fromAccountRefId;
    }

    public Long getMcc() {
        return mcc;
    }

    public void setMcc(Long mcc) {
        this.mcc = mcc;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCity() {
        return merchantCity;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public void setMerchantDetails(String merchantDetails) {
        this.merchantDetails = merchantDetails;
    }

    public String getMerchantStateCountry() {
        return merchantStateCountry;
    }

    public void setMerchantStateCountry(String merchantStateCountry) {
        this.merchantStateCountry = merchantStateCountry;
    }

    public Object getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Object merchantType) {
        this.merchantType = merchantType;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Long getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Long originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Object getRejectCode() {
        return rejectCode;
    }

    public void setRejectCode(Object rejectCode) {
        this.rejectCode = rejectCode;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Object getReportDate() {
        return reportDate;
    }

    public void setReportDate(Object reportDate) {
        this.reportDate = reportDate;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getRetrievalReferenceNumber() {
        return retrievalReferenceNumber;
    }

    public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
        this.retrievalReferenceNumber = retrievalReferenceNumber;
    }

    public Object getSettledBalance() {
        return settledBalance;
    }

    public void setSettledBalance(Object settledBalance) {
        this.settledBalance = settledBalance;
    }

    public Long getSettlementDateEpoch() {
        return settlementDateEpoch;
    }

    public void setSettlementDateEpoch(Long settlementDateEpoch) {
        this.settlementDateEpoch = settlementDateEpoch;
    }

    public Object getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Object settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String mAuthStatus) {
        this.authStatus = mAuthStatus;
    }

    public Object getSwitchSerialNumber() {
        return switchSerialNumber;
    }

    public void setSwitchSerialNumber(Object switchSerialNumber) {
        this.switchSerialNumber = switchSerialNumber;
    }

    public String getSysTraceAuditNumber() {
        return sysTraceAuditNumber;
    }

    public void setSysTraceAuditNumber(String sysTraceAuditNumber) {
        this.sysTraceAuditNumber = sysTraceAuditNumber;
    }

    public Object getToAccountRefId() {
        return toAccountRefId;
    }

    public void setToAccountRefId(Object toAccountRefId) {
        this.toAccountRefId = toAccountRefId;
    }

    public Long getToleranceAmount() {
        return toleranceAmount;
    }

    public void setToleranceAmount(Long toleranceAmount) {
        this.toleranceAmount = toleranceAmount;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Object getTransmissionDate() {
        return transmissionDate;
    }

    public void setTransmissionDate(Object transmissionDate) {
        this.transmissionDate = transmissionDate;
    }

    public Money getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Money transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Object getBankDateEpoch() {
        return bankDateEpoch;
    }

    public void setBankDateEpoch(Object bankDateEpoch) {
        this.bankDateEpoch = bankDateEpoch;
    }

    public Object getBankDate() {
        return bankDate;
    }

    public void setBankDate(Object bankDate) {
        this.bankDate = bankDate;
    }

    public Object getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Object cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Object getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Object chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getDomInt() {
        return domInt;
    }

    public void setDomInt(String domInt) {
        this.domInt = domInt;
    }

    public Object getFraudulentReturn() {
        return fraudulentReturn;
    }

    public void setFraudulentReturn(Object fraudulentReturn) {
        this.fraudulentReturn = fraudulentReturn;
    }

    public Object getOriginateAmount() {
        return originateAmount;
    }

    public void setOriginateAmount(Object originateAmount) {
        this.originateAmount = originateAmount;
    }

    public Object getOriginateCurrency() {
        return originateCurrency;
    }

    public void setOriginateCurrency(Object originateCurrency) {
        this.originateCurrency = originateCurrency;
    }

    public Object getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(Object parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public Boolean getProvisional() {
        return provisional;
    }

    public void setProvisional(Boolean provisional) {
        this.provisional = provisional;
    }

    public Object getPurseType() {
        return purseType;
    }

    public void setPurseType(Object purseType) {
        this.purseType = purseType;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Object getSettlementFlag() {
        return settlementFlag;
    }

    public void setSettlementFlag(Object settlementFlag) {
        this.settlementFlag = settlementFlag;
    }

    public Long getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Long surcharge) {
        this.surcharge = surcharge;
    }

    public String getAccountRefId() {
        return accountRefId;
    }

    public void setAccountRefId(String accountRefId) {
        this.accountRefId = accountRefId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCardRefId() {
        return cardRefId;
    }

    public void setCardRefId(String cardRefId) {
        this.cardRefId = cardRefId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Object getPartyMemberId() {
        return partyMemberId;
    }

    public void setPartyMemberId(Object partyMemberId) {
        this.partyMemberId = partyMemberId;
    }


    private transient Card mCard;

    private transient Account mAccount;

    public static Transaction create(String id) {
        return new Transaction(id);
    }

    private Transaction(String id) {
        this.id = id;
    }

    private Transaction(String id,
                        TransactionStatus transactionStatus,
                        Long memberId,
                        Long transactionCategoryId,
                        String merchantDetails,
                        Long dateEpoch,
                        String description,
                        Money transactionAmount,
                        String cardId,
                        String accountId,
                        ProcessorCategory processorCategory,
                        Long settlementDateEpoch,
                        String sysTraceAuditNumber,
                        Money balance,
                        Card card,
                        Account account,
                        String displayName,
                        String rejectReason) {
        this.id = id;
        this.transactionStatus = transactionStatus;
        this.memberId = memberId;
        this.category = transactionCategoryId;
        this.merchantDetails = merchantDetails;
        this.dateEpoch = dateEpoch;
        this.description = description;
        this.transactionAmount = transactionAmount;
        this.cardId = cardId;
        this.accountId = accountId;
        this.processorCategory = processorCategory;
        this.settlementDateEpoch = settlementDateEpoch;
        this.sysTraceAuditNumber = sysTraceAuditNumber;
        this.balance = balance;
        this.mCard = card;
        this.mAccount = account;
        this.displayName = displayName;
        this.rejectReason = rejectReason;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "mId='" + id + '\'' +
                ", mTransactionStatus=" + transactionStatus +
                ", mMemberId=" + memberId +
                ", mTransactionCategoryId=" + category +
                ", mMerchantDetails='" + merchantDetails + '\'' +
                ", dateEpoch=" + dateEpoch +
                ", mDescription='" + description + '\'' +
                ", mTransactionAmount=" + transactionAmount +
                ", mCardId='" + cardId + '\'' +
                ", mAccountId='" + accountId + '\'' +
                ", mProcessorCategory=" + processorCategory +
                ", mSettlementDateEpoch=" + settlementDateEpoch +
                ", mSysTraceAuditNumber='" + sysTraceAuditNumber + '\'' +
                ", mBalance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Transaction withTransactionStatus(TransactionStatus transactionStatus) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                mCard,
                mAccount,
                displayName,
                rejectReason);
    }

    public Transaction withTransactionAmount(Money transactionAmount) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                mCard,
                mAccount,
                displayName,
                rejectReason);
    }

    public Transaction withMerchantDetails(String merchantDetails) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                mCard,
                mAccount,
                displayName,
                rejectReason);
    }


    public Transaction withDescription(String description) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                mCard,
                mAccount,
                displayName,
                rejectReason);
    }

    public Transaction withAcquireDateEpoch(Long dateEpoch) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                mCard,
                mAccount,
                displayName,
                rejectReason);
    }

    public Transaction withProcessorCategory(ProcessorCategory processorCategory) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                mCard,
                mAccount,
                displayName,
                rejectReason);
    }

    public Transaction withCard(Card card) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                card,
                mAccount,
                displayName,
                rejectReason);
    }

    public Transaction withAccount(Account account) {
        return new Transaction(id,
                transactionStatus,
                memberId,
                category,
                merchantDetails,
                dateEpoch,
                description,
                transactionAmount,
                cardId,
                accountId,
                processorCategory,
                settlementDateEpoch,
                sysTraceAuditNumber,
                balance,
                mCard,
                account,
                displayName,
                rejectReason);
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public String getMerchantDetails() {
        if (merchantDetails == null) {
            return "";
        }

        return merchantDetails.trim();
    }

    public String getAmount() {
        if (transactionAmount != null) {
            if (isDebit()) {
                Money absoluteValue = new Money(transactionAmount.abs().toBigInteger());
                return "-" + absoluteValue.printMoneyString();
            }
            return transactionAmount.printMoneyString();
        }
        return "";
    }

    public long getAcquireDateEpoch() {
        return acquireDateEpoch * 1000;
    }

    @Nullable
    public Card getCard() {
        return mCard;
    }

    @Nullable
    public Account getAccount() {
        return mAccount;
    }

    public String getDate() {
        return new DateTime(dateEpoch * 1000, DateTimeZone.getDefault())
                .toString("EEE, MMMM d, YYYY hh:mm z");
    }

    public String getDateOnly() {
        return new DateTime(dateEpoch * 1000, DateTimeZone.getDefault())
                .toString("MMMM d, YYYY");
    }

    public boolean isDebit() {
        return amountTransaction.contains("-");
        //  return mProcessorCategory == ProcessorCategory.DEBIT;
    }

    public boolean isCredit() {
        return !amountTransaction.contains("-");
        // return mProcessorCategory == ProcessorCategory.CREDIT;
    }

    public boolean isNeutral() {
        return transactionStatus == TransactionStatus.CANCELLED
                || transactionStatus == TransactionStatus.REJECTED;
    }
}
