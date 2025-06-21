package org.sigei.dto;

import org.sigei.model.evento.enums.ETipoEvento;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FestaDTO extends EventoDTO {
    private final ArrayList<String> atracoes;
    public FestaDTO(int id, String nome, String descricao, String rua, String numero, String bairro, String cidade,
                    String uf, String referencia, int lotacao, LocalDateTime data, int vagasDisp, ArrayList<String> atracoes) {
        super(id, nome, descricao, rua, numero, bairro, cidade, uf, referencia, lotacao, data, vagasDisp);
        tipoEvento = ETipoEvento.FESTA;
        this.atracoes = atracoes;
    }

    public ArrayList<String> getAtracoes() {
        return atracoes;
    }
}
