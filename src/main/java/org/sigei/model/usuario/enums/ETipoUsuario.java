package org.sigei.model.usuario.enums;

import java.util.ArrayList;

public enum ETipoUsuario {
    ADMINISTRADOR(1, "Administrador"),
    PARTICIPANTE(2, "Participante"),
    ORGANIZADOR(3, "Organizador");

    private final int idTipo;
    private final String descTipo;
    ETipoUsuario(int idTipo, String descTipo) {
        this.idTipo = idTipo;
        this.descTipo = descTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }
    public String getDescTipo() {
        return descTipo;
    }
    public static ETipoUsuario parseId(int value) {
        return switch (value) {
            case 1 -> ETipoUsuario.ADMINISTRADOR;
            case 2 -> ETipoUsuario.PARTICIPANTE;
            case 3 -> ETipoUsuario.ORGANIZADOR;
            default -> throw new IllegalArgumentException("Tipo de pessoa não encontrado");
        };
    }

    public static ETipoUsuario[] selecionaveis() {
        return new ETipoUsuario[] {
                ETipoUsuario.PARTICIPANTE,
                ETipoUsuario.ORGANIZADOR
        };
    }

    @Override
    public String toString() {
        return descTipo;
    }
}
