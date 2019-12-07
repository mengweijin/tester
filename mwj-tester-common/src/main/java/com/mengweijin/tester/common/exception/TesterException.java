package com.mengweijin.tester.common.exception;


/**
 * MWJTester Exception
 *
 * @author Meng Wei Jin
 **/
public class TesterException extends RuntimeException {

    public TesterException(String message) {
        super(message);
    }

    public TesterException(Throwable cause) {
        super(cause);
    }

    public TesterException(String message, Throwable cause) {
        super(message, cause);
    }
}
