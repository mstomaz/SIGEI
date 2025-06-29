package org.sigei.model.usuario.enums;

public enum ETipoUsuario {
    ADMINISTRADOR(1),
    PARTICIPANTE(2),
    ORGANIZADOR(3);

    private final int idTipo;
    ETipoUsuario(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }
    public static ETipoUsuario parseId(int value) {
        return switch (value) {
            case 1 -> ETipoUsuario.ADMINISTRADOR;
            case 2 -> ETipoUsuario.PARTICIPANTE;
            case 3 -> ETipoUsuario.ORGANIZADOR;
            default -> throw new IllegalArgumentException("Tipo de pessoa não encontrado");
        };
    }
}
