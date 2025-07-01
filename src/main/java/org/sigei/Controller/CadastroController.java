package org.sigei.controller;

import org.sigei.dao.pessoa.OrganizadorDAO;
import org.sigei.dao.pessoa.ParticipanteDAO;
import org.sigei.dao.usuario.UsuarioDAO;
import org.sigei.exception.ValidationException;
import org.sigei.model.builder.pessoa.OrganizadorBuilder;
import org.sigei.model.builder.pessoa.ParticipanteBuilder;
import org.sigei.model.builder.usuario.CredenciasBuilder;
import org.sigei.model.builder.usuario.UsuarioBuilder;
import org.sigei.model.pessoa.Organizador;
import org.sigei.model.pessoa.Participante;
import org.sigei.model.pessoa.Pessoa;
import org.sigei.model.usuario.Usuario;
import java.sql.SQLException;

public class CadastroController {
    public void cadastrar(Pessoa p, String login, String senha)
            throws ValidationException, SQLException, ClassNotFoundException {
        if (p instanceof Participante) {
            Participante prtc = new ParticipanteBuilder().criar(p.getCpf(), p.getNome(), p.getSobrenome(),
                    ((Participante) p).getDataNasc());

            Usuario u = new UsuarioBuilder().criar(
                    prtc, new CredenciasBuilder().criar(login, senha));

            new ParticipanteDAO().inserir(prtc);
            new UsuarioDAO().inserir(new UsuarioBuilder().criar(
                    prtc, new CredenciasBuilder().criar(login, senha)
            ));
        } else if (p instanceof Organizador) {
            Organizador org = new OrganizadorBuilder().criar(p.getCpf(), p.getNome(), p.getSobrenome());

            Usuario u = new UsuarioBuilder().criar(
                    org, new CredenciasBuilder().criar(login, senha));

            new OrganizadorDAO().inserir(org);
            new UsuarioDAO().inserir(new UsuarioBuilder().criar(
                    org, new CredenciasBuilder().criar(login, senha)
            ));
        }
    }
}
