package org.sigei.dto.evento;

import org.sigei.model.evento.enums.ETipoEvento;

import java.time.LocalDateTime;

public class PalestraDTO extends EventoDTO {
    private final String nomePalestrante;

    public PalestraDTO(int id, String nome, String descricao, String rua, String numero, String bairro, String cidade,
                      String uf, String referencia, int lotacao, LocalDateTime data, int vagasDisp, String nomePalestrante) {
        super(id, nome, descricao, rua, numero, bairro, cidade, uf, referencia, lotacao, data, vagasDisp);
        tipoEvento = ETipoEvento.FEIRA;
        this.nomePalestrante = nomePalestrante;
    }

    public String getNomePalestrante() {
        return nomePalestrante;
    }
}
