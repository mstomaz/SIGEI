package org.sigei.dao.pessoa.factory;

import org.sigei.dao.pessoa.AdministradorDAO;
import org.sigei.dao.pessoa.IGenericsPessoaDAO;
import org.sigei.dao.pessoa.OrganizadorDAO;
import org.sigei.dao.pessoa.ParticipanteDAO;

public class PessoaDAOFactory {
    public static IGenericsPessoaDAO getPessoaDAO(int tipoPessoa) {
        switch (tipoPessoa) {
            case 1:
                return new AdministradorDAO();
            case 2:
                return new ParticipanteDAO();
            case 3:
                return new OrganizadorDAO();
            default:
                throw new IllegalArgumentException("Tipo de pessoa desconhecido.");
        }
    }
}
