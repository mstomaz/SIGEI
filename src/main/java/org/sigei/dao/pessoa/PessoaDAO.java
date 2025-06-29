package org.sigei.dao.pessoa;

import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.pessoa.PessoaDTO;
import org.sigei.model.CPF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaDAO implements ILeituraDAO<PessoaDTO, CPF> {
    @Override
    public ArrayList<PessoaDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM Pessoa";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<PessoaDTO> pessoas = new ArrayList<>();
        while (rs.next()){
            PessoaDTO p = new PessoaDTO(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("sobrenome")
            );
            pessoas.add(p);
        }
        return pessoas;
    }

    @Override
    public PessoaDTO buscarPelaChave(CPF cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM Pessoa\n" +
                "WHERE cpf = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf.getDigitos());

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new PessoaDTO(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("sobrenome")
        );
    }
}