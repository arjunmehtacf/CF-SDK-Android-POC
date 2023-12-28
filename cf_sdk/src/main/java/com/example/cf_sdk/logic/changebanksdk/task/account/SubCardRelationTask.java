package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.ProductDetailsReponseItem;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.SubCardRelationParameter;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Use case for sub card relation data.
 */
public class SubCardRelationTask extends SingleUseCase<SubCardRelationParameter, List<ProductDetailsReponseItem>> {

    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public SubCardRelationTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationDatasource = authenticationDatasource;
        mMemberRepository = memberRepository;
    }

    @Override
    protected Single<List<ProductDetailsReponseItem>> buildUseCaseObservable(final SubCardRelationParameter subCardRelationParameter) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends List<ProductDetailsReponseItem>>>() {
                    @Override
                    public SingleSource<? extends List<ProductDetailsReponseItem>> apply(Session session) throws Exception {
                        subCardRelationParameter.addToken(session.getTokenType() + " " + session.getToken());
                        subCardRelationParameter.setCardProduct(session.getProgramId());
                        return mMemberRepository.getCardRelation(subCardRelationParameter);
                    }
                });
    }
}
