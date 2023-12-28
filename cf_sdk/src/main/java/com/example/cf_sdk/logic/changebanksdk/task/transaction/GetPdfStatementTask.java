package com.example.cf_sdk.logic.changebanksdk.task.transaction;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.transaction.PdfStatementsParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.TransactionDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for fetching pdf statements for a particular month.
 */

public class GetPdfStatementTask extends SingleUseCase<PdfStatementsParameters, File> {

    private AuthenticationDatasource mAuthenticationRepository;
    private TransactionDatasource mTransactionRepository;

    @Inject
    public GetPdfStatementTask(
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("repository") TransactionDatasource transactionRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
        mTransactionRepository = transactionRepository;
    }

    @Override
    protected Single<File> buildUseCaseObservable(final PdfStatementsParameters pdfStatementsParameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<File>>>() {
                    @Override
                    public SingleSource<? extends Optional<File>> apply(Session session) throws Exception {
                        PdfStatementsParameters updatedPdfStatementsParameters = pdfStatementsParameters.withMemberId(session.getAccountNumber());
                        updatedPdfStatementsParameters.addToken(session.getTokenType() + " " + session.getToken());

                        return mTransactionRepository.getPdfStatements(updatedPdfStatementsParameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<File>, MaybeSource<File>>() {
                    @Override
                    public MaybeSource<File> apply(Optional<File> fileOptional) throws Exception {
                        if (fileOptional.isPresent()) {
                            return Maybe.just(fileOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();

    }
}
