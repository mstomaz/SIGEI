package org.sigei.model.usuario.enums;

public enum ETipoUsuario {
    ADMINISTRADOR(1),
    PARTICIPANTE(2),
    ORGANIZADOR(3);

    private int idTipo;
    ETipoUsuario(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }
}
