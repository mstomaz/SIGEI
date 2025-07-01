package org.sigei.dao.usuario;

import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dao.pessoa.factory.PessoaDAOFactory;
import org.sigei.dto.usuario.UsuarioDTO;
import org.sigei.model.CPF;
import org.sigei.model.usuario.Credenciais;
import org.sigei.model.usuario.Usuario;
import org.sigei.model.usuario.enums.ETipoUsuario;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public void inserir(Usuario usuario)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Usuario\n" +
                "(cpf, login, senha, tipoUsuario)\n" +
                " VALUES\n" +
                "(?, ?, md5(?), ?)";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, usuario.getDadosPessoais().getCpf().getDigitos());
        pst.setString(2, usuario.getCredenciais().getLogin());
        pst.setString(3, usuario.getCredenciais().getSenha());
        pst.setInt(4, usuario.getTipoUsuario().getIdTipo());

        pst.execute();
    }

    public void apagar(String login)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "DELETE FROM Usuario\n" +
                "WHERE login = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, login);

        pst.execute();
    }

    public void mudaSenha(String login, String novaSenha)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "UPDATE Usuario\n" +
                "SET senha = md5(?)\n" +
                "WHERE logij = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, novaSenha);
        pst.setString(2, login);

        pst.execute();
    }

    public ArrayList<UsuarioDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM Usuario;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        while (rs.next()){
            UsuarioDTO user = new UsuarioDTO(
                    PessoaDAOFactory.getPessoaDAO(rs.getInt("tipoUsuario"))
                            .buscarPelaChave(new CPF(rs.getString("cpf"))),
                    new Credenciais(rs.getString("login"), ""),
                    ETipoUsuario.parseId(rs.getInt("tipoUsuario"))
            );
            usuarios.add(user);
        }
        return usuarios;
    }

    public UsuarioDTO buscarPelaChave(String login)
            throws SQLException, ClassNotFoundException {
            Connection c = ConnectionFactory.getConnection();

            String sql= "SELECT * FROM Usuario\n" +
                    "WHERE login = ?;";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, login);

            ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new UsuarioDTO(
                PessoaDAOFactory.getPessoaDAO(rs.getInt("tipoUsuario"))
                        .buscarPelaChave(new CPF(rs.getString("cpf"))),
                new Credenciais(rs.getString("login"), ""),
                ETipoUsuario.parseId(rs.getInt("tipoUsuario"))
        );
    }

    public UsuarioDTO buscarPorLoginESenha(String login, String senha)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Usuario WHERE login = ? AND senha = md5(?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, login);
        pst.setString(2, senha);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new UsuarioDTO(
                PessoaDAOFactory.getPessoaDAO(rs.getInt("tipoUsuario"))
                        .buscarPelaChave(new CPF(rs.getString("cpf"))),
                new Credenciais(rs.getString("login"), rs.getString("senha")),
                ETipoUsuario.parseId(rs.getInt("tipoUsuario"))
        );
    }
}
