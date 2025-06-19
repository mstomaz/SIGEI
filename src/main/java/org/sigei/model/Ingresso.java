package org.sigei.model;

import org.sigei.model.evento.Evento;

public class Ingresso {
    private int id;
    private Evento evento;

    public Ingresso() {}
    public Ingresso(Evento evento) {
        this.evento = evento;
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (this.id == 0) {
            this.id = id;
        }
    }
    public Evento getEvento() {
        return evento;
    }
}
