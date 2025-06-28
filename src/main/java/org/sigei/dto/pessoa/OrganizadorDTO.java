package org.sigei.dto.pessoa;

import org.sigei.dto.evento.EventoDTO;
import java.util.ArrayList;

public class OrganizadorDTO extends PessoaDTO {
    private final ArrayList<EventoDTO> eventos;
    public OrganizadorDTO(String cpf, String nome, String sobrenome, ArrayList<EventoDTO> eventos) {
        super(cpf, nome, sobrenome);
        this.eventos = eventos;
    }

    public ArrayList<EventoDTO> getEventos() {
        return eventos;
    }
}
