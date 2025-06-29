package org.sigei.dao.pessoa;

import org.sigei.dto.pessoa.PessoaDTO;
import org.sigei.model.CPF;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IGenericsPessoaDAO {
    ArrayList<PessoaDTO> buscarTodos() throws SQLException, ClassNotFoundException;
    PessoaDTO buscarPelaChave(CPF cpf) throws SQLException, ClassNotFoundException;
}
