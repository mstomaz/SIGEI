package org.sigei.model.evento.enums;

public enum ETipoEvento {
    FEIRA(1),
    FESTA(2),
    OFICINA(3),
    PALESTRA(4),
    SHOW(5);

    private int idTipo;
    ETipoEvento(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }
}
