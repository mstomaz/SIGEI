package org.sigei.exception;

public class IngressosEsgotadosException extends Exception {
    public IngressosEsgotadosException() {
        this("Os ingressos para este evento estão esgotados.");
    };
    public IngressosEsgotadosException(String message) {
        super(message);
    }
    public IngressosEsgotadosException(Exception inner) {
        super(inner);
    }
    public IngressosEsgotadosException(String message, Exception inner) {
        super(message, inner);
    }
}
