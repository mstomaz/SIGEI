package org.sigei.model.evento;

import org.sigei.model.evento.enums.ETipoEvento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Show extends Evento {
    private ArrayList<String> lineUp;

    public Show(String nome, String descricao, LocalEvento local, LocalDateTime data) {
        super(nome, descricao, local, data);
        tipoEvento = ETipoEvento.SHOW;
        lineUp = new ArrayList<String>();
    }

    public Show(String nome, String descricao, LocalEvento local, LocalDateTime data, ArrayList<String> lineUp) {
        this(nome, descricao, local, data);
        this.lineUp = lineUp;
    }

    public Show(String nome, String descricao, LocalEvento local, LocalDateTime data, String... lineUp) {
        this(nome, descricao, local, data);
        this.lineUp = (ArrayList<String>) Arrays.asList(lineUp);
    }

    public ArrayList<String> getLineUp() {
        return lineUp;
    }

    public void addArtista(String nomeArtista) {
        lineUp.add(nomeArtista);
    }

    public void addArtistas(ArrayList<String> artistas) {
        this.lineUp.addAll(artistas);
    }

    public void addArtistas(String... artistas) {
        addArtistas((ArrayList<String>)Arrays.asList(artistas));
    }

    public void rmvArtista(String nomeArtistas) {
        lineUp.remove(nomeArtistas);
    }
}
