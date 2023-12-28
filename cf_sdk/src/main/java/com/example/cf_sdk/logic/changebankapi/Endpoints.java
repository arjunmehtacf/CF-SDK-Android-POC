package com.example.cf_sdk.logic.changebankapi;

/**
 *
 * API Endpoints.
 */

public class Endpoints {
    public static class Auth {
        public static final String AUTHENTICATION = "users/login";
        public static final String CREDENTIALS = "authentication/credentials/{memberId}";
        public static final String PASSWORD = "users/password/change";
        public static final String UPDATE_SECURITY_PROFILE = "authentication/securityProfile/{memberId}";
        public static final String GET_SECURITY_PROFILE = "authentication/securityProfile/{memberId}";
        public static final String LOGOUT = "users/logout";
        public static final String RESEND_CODE = "authentication/resendCode";
        public static final String RESET_PASSWORD = "authentication/resetPassword";
        public static final String TWO_FACTOR = "authentication/twoFactor";
        public static final String USERNAME_AVAILABILITY = "users/check";
    }

    public static class Member {
        public static final String SEND_CODE_PHONE = "users/phone/verify";
        public static final String VERIFY_PHONE_CODE = "verification/phone";
        public static final String VERIFY_OOW_QUESTIONS = "verification/{memberId}/oow";
        public static final String REFRESH_OOW_QUESTIONS = "verification/{memberId}/oow";
        public static final String CREATE_MEMBER = "users";
        public static final String ADD_ADDRESS = "customer/address/{customerNumber}";
        public static final String UPDATE_MEMBER = "users/email";
        public static final String IDSCAN_VERIFY = "idscan/{memberId}";
        public static final String ADD_CONFIDENTIAL = "member/{memberId}/confidential";
        public static final String PHONE_AVAILABILITY = "users/phone/check";
        public static final String EMAIL_AVAILABILITY = "users/email/check";
        public static final String MEMBER_DETAILS = "customer/{customerNumber}/mobileprofile";
        public static final String GET_AGREEMENT = "system/documents/{documentId}";
        public static final String FORGOT_PASSWORD = "users/password/forgot";
        public static final String RESET_PASSWORD = "users/password/forgot";
        public static final String LOG = "log";
        public static final String SUBMIT_IDSCAN = "idScanOnboard/{memberId}";
        public static final String UPLOAD_PROFILE_PICTURE = "users/profile/picture";
        public static final String UPDATE_MEMBER_PHONE = "users/phone";
        public static final String DELETE_PROFILE_PICTURE = "users/profile/picture";
        public static final String GET_DOCUMENT_BY_ID = "document/{documentId}/member/{memberId}";
        public static final String GET_DOCUMENT_IMAGE_BY_ID = "documentImage/{documentId}/member/{memberId}";

        public static final String GET_PROFILE_PICTURE= "users/profile/picture";
        public static final String GET_VERSION_CONFIG = "system/mobile-settings";

        public static final String GET_USER_PROFILE = "users/profile";

        public static final String GET_CARD_DETAILS= "card/{cardNumber}/cardRSAEncrypted";
        public static final String GET_RSA_PUBLIC_KEY = "system/rsa";

        public static final String GET_ACCOUNT_ACTIVATE = "card/{cardNumber}/activate";
        public static final String FORGOT_USERNAME = "users/forgotUsername";
        public static final String BATCH_LIST_CONFIRM = "batchlist/confirm";
        public static final String BATCH_LIST_CREATE_CREDENTIALS = "batchlist/credentials";
        public static final String BATCH_LIST_ADMIN_AVAILABLE = "batchlist/{adminNumber}/available";
        public static final String INSTANT_CARD_AVAILABLE = "verification/instatcard";

        public static final String CHECK_DEPOSIT = "account/checkdeposit/{memberId}";
    }

    public static class Account {
        public static final String GET_BANK_BY_ID = "bank/{bankId}";
        public static final String GET_BANKS = "banks";
        public static final String GET_ACCOUNT = "account/{accountNumber}/balance";
        public static final String GET_ACCOUNTS = "customer/{customerNumber}/cards";
        public static final String GET_ACH_ACCOUNTS = "eft/{accountNumber}/list";
        public static final String GET_DIRECT_DEPOSIT_FORM = "account/{account}/PDFDepositForm";
        public static final String GET_MFA_OR_BANK_CREDENTIALS = "achAccount/{id}/credentials";
        public static final String SUBMIT_MFA_OR_BANK_CREDENTIALS = "achAccount/{id}/credentials";
        public static final String POST_CARD_ACTIVATION = "card/{id}/activation";
        public static final String POST_CARD_UPDATE_PIN = "card/{cardNumber}/pin";
        public static final String UPDATE_CARD_STATUS = "card/{cardNumber}/block";
        public static final String GET_ACH_ACCOUNT = "achAccount/{id}";
        public static final String DELETE_ACH_ACCOUNT = "eft/{bankAccID}";
        public static final String LINK_ACH_ACCOUNT = "achAccount/{id}/link";
        public static final String RELINK_ACH_ACCOUNT = "achAccount/{id}/relink";
        public static final String UNLINK_ACH_ACCOUNT = "achAccount/{id}/unlink";
        public static final String CREATE_ACH_ACCOUNT = "eft/{accountNumber}";
        public static final String VERIFY_ACH_ACCOUNT = "eft/{bankAccID}";
        public static final String REFRESH_ACH_ACCOUNTS = "achAccounts/refresh";
        public static final String GET_ACH_HISTORY = "eft/{account}/transactions";
        public static final String GET_ACCOUNT_BALANCE = "account/{id}/balance";
        public static final String ACH_TRANSFER = "eft/{bankAccID}/withdrawal";
        public static final String ACH_TRANSFER_DELETE = "achTransfer/{id}";
        public static final String ACH_VERIFICATION = "achTransfer/verification";
        public static final String GET_BANK_CREDENTIALS = "bank/{bankId}/credentials";
        public static final String CARD_BY_ID = "card/{cardId}";
        public static final String CARD_DETAILS_BY_ID = "card/{cardId}/fullInfo";
        public static final String REQUEST_A_PHYSICAL_CARD = "card/{id}/requestPhysicalCard";
        public static final String FAKE_ACCOUNT_ID = "fakeAccountID/{id}";
        public static final String VALIDATE_ACCOUNT_NUMBER = "account/search";
        public static final String ACOOUTN_TO_ACCOUNT= "account/{account}/transaction/transfer/{toAccount}";
        public static final String CHECK_DEPOSITED_LIST = "account/depositedcheck";

        public static final String SUB_CARD_RELATION = "product";
        public static final String CREATE_SUB_CARD = "card/{cardNumber}/sub-card";
    }

    public static class Google {
        public static final String PLACE_DETAILS = "details/json";
        public static final String AUTOCOMPLETE_PREDICTIONS = "autocomplete/json";
    }

    public static class Transactions {
        public static final String GET_TRANSACTIONS_BY_DATE = "card/{cardNumber}/authorisations/{dateFrom}/{dateTo}";
        public static final String GET_TRANSACTION_BY_ID = "transactions/{transactionId}";
        public static final String SEARCH_TRANSACTIONS = "card/{cardNumber}/authorisations/{dateFrom}/{dateTo}";
        public static final String GET_TRANSACTIONS_BY_STATUS = "card/{cardNumber}/authorisations/{dateFrom}/{dateTo}";
        public static final String GET_PDF_STATEMENTS = "account/{account}/PDFStatement/{year}/{month}";

        public static final String GET_TRANSACTION_STATEMENTS="card/{cardNumber}/transactions";
    }

    public static class Ingo {
        public static final String CHECK_DEPOSIT = "checkDeposit";
    }
}
