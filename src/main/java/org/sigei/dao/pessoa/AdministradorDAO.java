package org.sigei.dao.pessoa;

import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.IEscritaDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.pessoa.AdministradorDTO;
import org.sigei.model.pessoa.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministradorDAO implements IEscritaDAO<Administrador, String>, ILeituraDAO<AdministradorDTO, String> {

    @Override
    public void inserir(Administrador adm)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO administrador\n" +
                "(cpf, nome, sobrenome)\n" +
                "VALUES\n" +
                "(?, ?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, adm.getCpf().getDigitos());
        pst.setString(2, adm.getNome());
        pst.setString(3, adm.getSobrenome());

        pst.execute();
    }

    @Override
    public void apagar(String cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "DELETE FROM administrador\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf);

        pst.execute();
    }

    @Override
    public void alterar(Administrador adm)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "UPDATE administrador\n" +
                "SET\n" +
                "nome = ?,\n" +
                "sobrenome = ?,\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, adm.getNome());
        pst.setString(2, adm.getSobrenome());
        pst.setString(3, adm.getCpf().getDigitos());

        pst.execute();
    }

    @Override
    public ArrayList<AdministradorDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM administrador;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<AdministradorDTO> admins = new ArrayList<>();
        while (rs.next()){
            AdministradorDTO adm = new AdministradorDTO(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("sobrenome")
            );
            admins.add(adm);
        }
        return admins;
    }

    @Override
    public AdministradorDTO buscarPelaChave(String cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM administrador\n" +
                "WHERE cpf = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new AdministradorDTO(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("sobrenome")
        );
    }
}
