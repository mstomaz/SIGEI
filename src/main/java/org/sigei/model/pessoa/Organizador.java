package org.sigei.model.pessoa;

import java.time.LocalDate;
import org.sigei.model.evento.Evento;
import java.util.ArrayList;

public class Organizador<C> extends Pessoa<C> {
    private final ArrayList<Evento> eventos;

    public Organizador() {
        super();
        eventos = new ArrayList<>();
    };
    public Organizador(C chave, String nome, String sobrenome, LocalDate dataNasc) {
        super(chave, nome, sobrenome, dataNasc);
        eventos = new ArrayList<>();
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void addEvento(Evento evento) {
        eventos.add(evento);
    }

    public void rmvEvento(Evento evento) {
        eventos.remove(evento);
    }
}
