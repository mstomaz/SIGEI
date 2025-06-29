package org.sigei.dao.pessoa;

import org.sigei.dao.IEscritaDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.pessoa.PessoaDTO;
import org.sigei.model.CPF;
import org.sigei.model.pessoa.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BasePessoaDAO<C extends Pessoa>
        implements IEscritaDAO<C, CPF> {
    @Override
    public abstract void inserir(C obj) throws SQLException, ClassNotFoundException;

    @Override
    public void apagar(CPF cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "DELETE FROM Pessoa\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf.getDigitos());

        pst.execute();
    }

    @Override
    public void alterar(Pessoa p)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "UPDATE Pessoa\n" +
                "SET\n" +
                "nome = ?,\n" +
                "sobrenome = ?,\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, p.getNome());
        pst.setString(2, p.getSobrenome());
        pst.setString(3, p.getCpf().getDigitos());

        pst.execute();
    }
}
