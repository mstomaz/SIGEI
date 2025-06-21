package org.sigei.exception;

public class EventoNaoEncontradoException extends RuntimeException {
    public EventoNaoEncontradoException() {
        this("Evento não encontrado.");
    }
    public EventoNaoEncontradoException(String message) {
        super(message);
    }
    public EventoNaoEncontradoException(Exception inner) {
        super(inner);
    }
    public EventoNaoEncontradoException(String message, Exception inner) {
        super(message, inner);
    }
}
