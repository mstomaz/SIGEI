package org.sigei.model.pessoa;

import org.sigei.exception.IngressoDuplicadoException;
import org.sigei.exception.IngressosEsgotadosException;
import org.sigei.model.Ingresso;
import org.sigei.model.evento.Evento;
import java.time.LocalDate;
import java.util.ArrayList;

public class Participante<CPF> extends Pessoa<CPF> {
    private final ArrayList<Ingresso> ingressos;

    public Participante() {
        super();
        ingressos = new ArrayList<>();
    };
    public Participante(CPF cpf, String nome, String sobrenome, LocalDate dataNasc) {
        super(cpf, nome, sobrenome, dataNasc);
        ingressos = new ArrayList<>();
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public void comprarIngresso(Evento evento, Ingresso ingresso)
            throws IngressosEsgotadosException, IngressoDuplicadoException {
        if (ingressos.contains(ingresso)) {
            throw new IngressoDuplicadoException();
        }
        try {
            evento.diminuiVagasDisp();
            ingressos.add(ingresso);
        } catch (IngressosEsgotadosException ex) {
            throw new IngressosEsgotadosException(ex.getMessage());
        }
    }
}
