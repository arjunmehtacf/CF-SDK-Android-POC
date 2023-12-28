package com.example.cf_sdk.logic.changebanksdk;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

/**
 *
 * Base class for use cases.
 */

public abstract class UseCase<P extends Parameters, OBSERVABLE, OBSERVER> {

    protected abstract OBSERVABLE buildUseCaseObservable(P parameters);

    public abstract void execute(OBSERVER observer, P parameters);
}
