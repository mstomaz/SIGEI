package org.sigei.dto;

import org.sigei.model.evento.enums.ETipoEvento;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShowDTO extends EventoDTO {
    private final ArrayList<String> lineUp;
    public ShowDTO(int id, String nome, String descricao, String rua, String numero, String bairro, String cidade,
                    String uf, String referencia, int lotacao, LocalDateTime data, int vagasDisp, ArrayList<String> lineUp) {
        super(id, nome, descricao, rua, numero, bairro, cidade, uf, referencia, lotacao, data, vagasDisp);
        tipoEvento = ETipoEvento.SHOW;
        this.lineUp = lineUp;
    }

    public ArrayList<String> getLineUp() {
        return lineUp;
    }
}
