package org.sigei.dto.evento;

import org.sigei.model.evento.enums.ETipoEvento;

import java.time.LocalDateTime;

public class FeiraDTO extends EventoDTO {
    public FeiraDTO(int id, String nome, String descricao, String rua, String numero, String bairro, String cidade,
                    String uf, String referencia, int lotacao, LocalDateTime data, int vagasDisp) {
        super(id, nome, descricao, rua, numero, bairro, cidade, uf, referencia, lotacao, data, vagasDisp);
        tipoEvento = ETipoEvento.FEIRA;
    }
}
