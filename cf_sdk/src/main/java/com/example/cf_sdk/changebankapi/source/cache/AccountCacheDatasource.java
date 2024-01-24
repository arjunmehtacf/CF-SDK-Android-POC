package com.example.cf_sdk.changebankapi.source.cache;

import androidx.annotation.Nullable;

import com.example.cf_sdk.changebankapi.model.account.Account;
import com.example.cf_sdk.changebankapi.model.account.AchAccount;
import com.example.cf_sdk.changebankapi.model.account.Bank;
import com.example.cf_sdk.changebankapi.model.account.Card;
import com.example.cf_sdk.changebankapi.parameter.StringParameters;
import com.example.cf_sdk.defination.response.AccountsApiResponse;
import com.example.cf_sdk.changebankapi.response.BankCredentialsApiResponse;
import com.example.cf_sdk.changebankapi.source.datasource.AccountDatasource;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * Created by gunveernatt on 10/4/17.
 * <p>
 * Account cache datasource to provide in-memory data.
 */

public class AccountCacheDatasource implements AccountDatasource {
    private Map<String, Bank> mBanks;
    private Map<String, BankCredentialsApiResponse> mBankCredentialsApiResponse;
    private Map<String, Account> mAccountsCacheMap;
    private Multimap<String, Card> mCardsCacheMultimap;
    private Map<String, AchAccount> mAchAccounts;
    private File mDirectDepositForm;

    public AccountCacheDatasource() {
        mBanks = new HashMap<>();
        mBankCredentialsApiResponse = new HashMap<>();
        mAccountsCacheMap = new HashMap<>();
        mAchAccounts = new HashMap<>();
        mCardsCacheMultimap = HashMultimap.create();
    }

    @Override
    public Single<AccountsApiResponse> getMemberAccounts(StringParameters stringParameters) {
        throw new UnsupportedOperationException(
                "AccountCacheDatasource not support getMemberAccounts");
    }


    @Override
    public void saveAccountsApiResponse(@Nullable AccountsApiResponse accountsApiResponse) {
        List<Account> accounts = accountsApiResponse.getAccounts();
        if (accounts != null) {
            for (Account account : accounts) {
                mAccountsCacheMap.put(account.getId(), account);
                if (!account.getCardsList().isEmpty()) {
                    mCardsCacheMultimap.putAll(account.getLast4AccountNumber(), account.getCardsList());
                }
            }
        }

    }
}
