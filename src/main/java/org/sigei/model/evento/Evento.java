package org.sigei.model.evento;

import org.sigei.exception.IngressosEsgotadosException;
import org.sigei.exception.ValidationException;
import org.sigei.model.Ingresso;
import org.sigei.model.builder.evento.LocalEventoBuilder;

import java.time.LocalDateTime;

public abstract class Evento {
    private int id;
    private String nome;
    private String descricao;
    private LocalEvento local;
    private LocalDateTime data;
    private Ingresso[] ingressos;
    private int vagasDisp;

    public Evento(String nome, String descricao, LocalEvento local, LocalDateTime data) {
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        ingressos = new Ingresso[local.getLotacao()];
        vagasDisp = ingressos.length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id == 0) {
            this.id = id;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ValidationException {
        if (nome.isBlank()) {
            throw new ValidationException("Nome", "campo.obrigatorio");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalEvento getLocal() {
        return local;
    }

    public void setLocal(LocalEvento local) throws ValidationException {
        this.local = new LocalEventoBuilder().criar(
                local.getEndereco(),
                local.getLotacao());
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) throws ValidationException {
        if (data.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Data", "campo.invalido");
        }
    }

    public Ingresso[] getIngressos() {
        return ingressos;
    }

    public int getVagasDisponiveis() { return vagasDisp; }

    public void diminuiVagasDisp() throws IngressosEsgotadosException {
        if (vagasDisp == 0) {
            throw new IngressosEsgotadosException();
        }
        vagasDisp--;
    }

    public boolean jaRealizado() {
        return data.isAfter(LocalDateTime.now());
    }
}
