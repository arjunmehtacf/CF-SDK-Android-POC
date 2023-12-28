package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.MemberDetails;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddMemberInfoAndAddressParameters;
import com.example.cf_sdk.logic.changebanksdk.sanitization.AddressSanitizer;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.AddressValidator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 *
 * Use case for Updating memeber info.
 */

public class UpdateMemberTask extends SingleUseCase<AddMemberInfoAndAddressParameters, MemberDetails> {
    private final AddressSanitizer mAddressSanitizer;
    private final AddressValidator mAddressValidator;
    private final AuthenticationDatasource mAuthenticationRepository;
    private final MemberDatasource mMemberRepository;

    @Inject
    public UpdateMemberTask(
            AddressSanitizer addressSanitizer,
            AddressValidator addressValidator,
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAddressSanitizer = addressSanitizer;
        mAddressValidator = addressValidator;
        mMemberRepository = memberRepository;
        mAuthenticationRepository = authenticationDatasource;
    }

    @Override
    protected Single<MemberDetails> buildUseCaseObservable(final AddMemberInfoAndAddressParameters parameters) {
        return mAddressSanitizer.sanitize(parameters.getAddressParameters())
                .flatMap(new Function<AddAddressParameters, SingleSource<? extends AddAddressParameters>>() {
                    @Override
                    public SingleSource<? extends AddAddressParameters> apply(AddAddressParameters aap) throws Exception {
                        return Single.zip(
                                mAddressValidator.validate(aap),
                                mAuthenticationRepository.getSession(),
                                new BiFunction<AddAddressParameters, Session, AddAddressParameters>() {
                                    @Override
                                    public AddAddressParameters apply(AddAddressParameters addAddressParameters, Session session) throws Exception {
                                        parameters.addToken(session.getToken());
                                        parameters.setMemberId(session.getMemberId());
                                        return parameters.getAddressParameters();
                                    }
                                }
                        );
                    }
                })
                .flatMap(new Function<AddAddressParameters, Single<MemberDetails>>() {
                    @Override
                    public Single<MemberDetails> apply(AddAddressParameters addAddressParameters) throws Exception {
                        parameters.setAddressParameters(addAddressParameters);
                        return mMemberRepository.addMemberInfoAndAddress(parameters);
                    }
                });
    }
}
