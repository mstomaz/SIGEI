package org.sigei.dto.pessoa;

import org.sigei.dto.ingresso.IngressoDTO;
import java.time.LocalDate;
import java.util.ArrayList;

public class ParticipanteDTO extends PessoaDTO {
    private final ArrayList<IngressoDTO> ingressos;
    private final LocalDate dataNasc;
    public ParticipanteDTO(String cpf, String nome, String sobrenome, LocalDate dataNasc, ArrayList<IngressoDTO> ingressos) {
        super(cpf, nome, sobrenome);
        this.dataNasc = dataNasc;
        this.ingressos = ingressos;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public ArrayList<IngressoDTO> getIngressos() {
        return ingressos;
    }
}
