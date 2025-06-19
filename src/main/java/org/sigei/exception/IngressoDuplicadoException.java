package org.sigei.exception;

public class IngressoDuplicadoException extends Exception {
    public IngressoDuplicadoException() {
        this("Tentativa de compra duplicada do mesmo ingresso.");
    };
    public IngressoDuplicadoException(String message) {
        super(message);
    }
    public IngressoDuplicadoException(Exception inner) {
        super(inner);
    }
    public IngressoDuplicadoException(String message, Exception inner) {
        super(message, inner);
    }
}
