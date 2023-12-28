package com.example.cf_sdk.logic.sdk_module.singleton;

import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.sanitization.AddressSanitizer;
import com.example.cf_sdk.logic.changebanksdk.sanitization.EmailSanitizer;
import com.example.cf_sdk.logic.changebanksdk.sanitization.PhoneSanitizer;
import com.example.cf_sdk.logic.changebanksdk.sanitization.StringSanitizer;
import com.example.cf_sdk.logic.changebanksdk.sanitization.UsernameSanitizer;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.IngoDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.example.cf_sdk.logic.changebanksdk.task.account.GetDespositedCheckTask;
import com.example.cf_sdk.logic.changebanksdk.task.account.PostAchTransferTask;
import com.example.cf_sdk.logic.changebanksdk.task.authentication.CheckUsernameExistsTask;
import com.example.cf_sdk.logic.changebanksdk.task.authentication.LoginPinTask;
import com.example.cf_sdk.logic.changebanksdk.task.authentication.LoginTask;
import com.example.cf_sdk.logic.changebanksdk.task.authentication.UpdatePinCredentialsTask;
import com.example.cf_sdk.logic.changebanksdk.task.ingo.GetIngoSSOTokenTask;
import com.example.cf_sdk.logic.changebanksdk.task.ingo.VerifyIngoSSNTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.ActivateCardTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.AddAddressTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.AddPlaceAddressTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.CheckDepositTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.GetTransferCardDetailsTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.SendCardToCardTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.UpdateMemberTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.UploadDocumentTask;
import com.example.cf_sdk.logic.changebanksdk.task.transaction.TransactionStatementsTask;
import com.example.cf_sdk.logic.changebanksdk.task.validation.EmailValidationOnlyTask;
import com.example.cf_sdk.logic.changebanksdk.task.validation.EmailValidationTask;
import com.example.cf_sdk.logic.changebanksdk.task.validation.NameValidationTask;
import com.example.cf_sdk.logic.changebanksdk.task.validation.PasswordValidationTask;
import com.example.cf_sdk.logic.changebanksdk.task.validation.PhoneValidationTask;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.AchTransferValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.AddressValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.EmailValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.LoginValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.NameValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.PasscodeValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.PasswordValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.PhoneValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.StringValidator;
import com.example.cf_sdk.logic.changebanksdk.validation.UsernameValidator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Use case module.
 */

@Module
public class UseCaseModule {
    @Provides
    PhoneValidationTask providePhoneValidationTask(
            @Named("repository") MemberDatasource repository,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new PhoneValidationTask(
                new PhoneSanitizer(),
                new PhoneValidator(),
                repository,
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    UpdatePinCredentialsTask provideUpdateCredentialsTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new UpdatePinCredentialsTask(
                new PasscodeValidator(new StringValidator()),
                authenticationDatasource,
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    AddPlaceAddressTask provideAddPlaceAddressTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") GoogleDatasource googleRepository,
            @Named("repository") MemberDatasource repository,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new AddPlaceAddressTask(
                new AddressSanitizer(new StringSanitizer()),
                new AddressValidator(new StringValidator()),
                authenticationDatasource,
                googleRepository,
                repository,
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    EmailValidationTask provideEmailValidationTask(
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new EmailValidationTask(
                new EmailSanitizer(),
                new EmailValidator(),
                memberRepository,
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    EmailValidationOnlyTask provideEmailValidationOnlyTask(
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new EmailValidationOnlyTask(
                new EmailSanitizer(),
                new EmailValidator(),
                memberRepository,
                backgroundThread,
                uiThread,
                errorHandler);
    }


    @Provides
    NameValidationTask provideNameValidationTask(
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new NameValidationTask(
                new StringSanitizer(),
                new NameValidator(new StringValidator()),
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    CheckUsernameExistsTask provideCheckUsernameExistsTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new CheckUsernameExistsTask(
                new UsernameSanitizer(new StringSanitizer()),
                new UsernameValidator(new StringValidator()),
                authenticationDatasource,
                backgroundThread,
                uiThread,
                errorHandler);
    }


    @Provides
    LoginTask provideLoginTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new LoginTask(
                new LoginValidator(new StringValidator()),
                authenticationDatasource,
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    LoginPinTask provideLoginPinTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new LoginPinTask(
                new LoginValidator(new StringValidator()),
                authenticationDatasource,
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    PasswordValidationTask providePasswordValidationTask(
            @Named("io") ExecutionThread backgroundThread,
            @Named("ui") ExecutionThread uiThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new PasswordValidationTask(
                new PasswordValidator(new StringValidator()),
                backgroundThread,
                uiThread,
                errorHandler);
    }

    @Provides
    AddAddressTask provideAddAddressTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new AddAddressTask(
                new AddressSanitizer(new StringSanitizer()),
                new AddressValidator(new StringValidator()),
                authenticationDatasource,
                memberRepository,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);
    }

    @Provides
    UpdateMemberTask provideUpdateMemberTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new UpdateMemberTask(
                new AddressSanitizer(new StringSanitizer()),
                new AddressValidator(new StringValidator()),
                authenticationDatasource,
                memberRepository,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);
    }

    @Provides
    GetTransferCardDetailsTask provideGetTransferCardDetailsTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new GetTransferCardDetailsTask(
                authenticationDatasource,
                memberRepository,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);
    }

    @Provides
    SendCardToCardTask provideSendCardToCardTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") AccountDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new SendCardToCardTask(
                authenticationDatasource,
                memberRepository,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);

    }

    @Provides
    CheckDepositTask provideCheckDepositTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new CheckDepositTask(
                authenticationDatasource,
                memberRepository,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);

    }

    @Provides
    TransactionStatementsTask provideTransactionStatementsTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                                               @Named("repository") TransactionDatasource transactionRepository,
                                                               @Named("io") ExecutionThread backgroundExecutionThread,
                                                               @Named("ui") ExecutionThread uiExecutionThread,
                                                               @Named("ErrorHandler") ChangebankError errorHandler){
        return new TransactionStatementsTask(authenticationRepository,transactionRepository,backgroundExecutionThread,uiExecutionThread,errorHandler);
    }

    @Provides
    ActivateCardTask provideActivateCardTask(@Named("repository") AuthenticationDatasource authenticationDatasource,
                                             @Named("repository") MemberDatasource memberRepository,
                                             @Named("io") ExecutionThread backgroundExecutionThread,
                                             @Named("ui") ExecutionThread uiExecutionThread,
                                             @Named("ErrorHandler") ChangebankError errorHandler) {
        return new ActivateCardTask(authenticationDatasource, memberRepository, backgroundExecutionThread, uiExecutionThread, errorHandler);
    }

    @Provides
    GetDespositedCheckTask providegetDipositedTask(
            @Named("repository") AccountDatasource authenticationDatasource,
            @Named("repository") AuthenticationDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new GetDespositedCheckTask(
                authenticationDatasource,
                memberRepository,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);

    }


    @Provides
    UploadDocumentTask provideUploadDocumentTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        return new UploadDocumentTask(authenticationDatasource, memberRepository, backgroundExecutionThread, uiExecutionThread, errorHandler);
    }

    @Provides
    PostAchTransferTask providePostAchTransferTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") AccountDatasource accountDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new PostAchTransferTask(
                new AchTransferValidator(),
                accountDatasource,
                authenticationDatasource,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);
    }


    @Provides
    GetIngoSSOTokenTask provideGetIngoSSOTokenTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") IngoDatasource ingoDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new GetIngoSSOTokenTask(
                authenticationDatasource,
                ingoDatasource,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);
    }

    @Provides
    VerifyIngoSSNTask provideVerifyIngoSSNTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") IngoDatasource ingoDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        return new VerifyIngoSSNTask(
                authenticationDatasource,
                ingoDatasource,
                backgroundExecutionThread,
                uiExecutionThread,
                errorHandler);
    }


}

