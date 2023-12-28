package com.example.cf_sdk.logic.changebanksdk.util;

import com.example.cf_sdk.logic.changebanksdk.log.Logger;

/**
 *
 * Base creator
 */

public interface Creator<P, R> {
    R create(Logger logger, P parameter);
}
