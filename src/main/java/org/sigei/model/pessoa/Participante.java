package org.sigei.model.pessoa;

import org.sigei.exception.IngressoDuplicadoException;
import org.sigei.exception.IngressosEsgotadosException;
import org.sigei.model.CPF;
import org.sigei.model.Ingresso;
import org.sigei.model.evento.Evento;
import java.time.LocalDate;
import java.util.ArrayList;

public class Participante extends Pessoa {
    private final ArrayList<Ingresso> ingressos;
    private LocalDate dataNasc;

    public Participante() {
        super();
        ingressos = new ArrayList<>();
    };
    public Participante(CPF cpf, String nome, String sobrenome, LocalDate dataNasc) {
        super(cpf, nome, sobrenome);
        this.dataNasc = dataNasc;
        ingressos = new ArrayList<>();
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
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
