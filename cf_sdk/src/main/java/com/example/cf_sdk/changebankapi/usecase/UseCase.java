package com.example.cf_sdk.changebankapi.usecase;


import com.example.cf_sdk.changebankapi.parameter.Parameters;

/**
 * Created by victorojeda on 10/19/16.
 * <p>
 * Base class for use cases.
 */

public abstract class UseCase<P extends Parameters, OBSERVABLE, OBSERVER> {

    protected abstract OBSERVABLE buildUseCaseObservable(P parameters);

    public abstract void execute(OBSERVER observer, P parameters);
}
