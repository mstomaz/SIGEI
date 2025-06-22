package org.sigei.dto.evento;

import org.sigei.model.evento.enums.ETipoEvento;

import java.time.LocalDateTime;

public class OficinaDTO extends EventoDTO {
    private final String tema;

    public OficinaDTO(int id, String nome, String descricao, String rua, String numero, String bairro, String cidade,
                    String uf, String referencia, int lotacao, LocalDateTime data, int vagasDisp, String tema) {
        super(id, nome, descricao, rua, numero, bairro, cidade, uf, referencia, lotacao, data, vagasDisp);
        tipoEvento = ETipoEvento.FEIRA;
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }
}
