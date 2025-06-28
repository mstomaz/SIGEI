package org.sigei.dto.evento;

import org.sigei.model.evento.enums.ETipoEvento;
import java.time.LocalDateTime;

public class EventoDTO {
    private final int id;
    private final String nome;
    private final String descricao;
    private final String rua;
    private final String numero;
    private final String bairro;
    private final String cidade;
    private final String uf;
    private final String referencia;
    private final int lotacao;
    private final LocalDateTime data;
    private final int vagasDisp;
    protected ETipoEvento tipoEvento;

    protected EventoDTO(int id, String nome, String descricao, String rua, String numero, String bairro, String cidade,
                        String uf, String referencia, int lotacao, LocalDateTime data, int vagasDisp) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.referencia = referencia;
        this.lotacao = lotacao;
        this.data = data;
        this.vagasDisp = vagasDisp;
    }
    public EventoDTO(int id, String nome, String descricao, String rua, String numero, String bairro, String cidade,
                     String uf, String referencia, int lotacao, LocalDateTime data, int vagasDisp, int tipoEvento) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.referencia = referencia;
        this.lotacao = lotacao;
        this.data = data;
        this.vagasDisp = vagasDisp;
        this.tipoEvento = defineTipoEvento(tipoEvento);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getReferencia() {
        return referencia;
    }

    public int getLotacao() {
        return lotacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getVagasDisp() {
        return vagasDisp;
    }

    public ETipoEvento getTipoEvento() {
        return tipoEvento;
    }

    private ETipoEvento defineTipoEvento(int idTipo) {
        for (ETipoEvento tipo : ETipoEvento.values()) {
            if (tipo.getIdTipo() == idTipo)
                return tipo;
        }
        return null;
    }
}
