package org.sigei.exception;

public class TipoUsuarioNaoEncontradoException extends RuntimeException {
    public TipoUsuarioNaoEncontradoException() {
        this("Tipo de usuário não encontrado.");
    }
    public TipoUsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
