package org.sigei.dao.evento;

import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.evento.FestaDTO;
import org.sigei.model.evento.Festa;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FestaDAO extends BaseEventosDAO<Festa, FestaDTO> {
    @Override
    public void inserir(Festa festa)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        c.setAutoCommit(false);

        try {
            String sql = "INSERT INTO evento\n" +
                    "(nome, descricao,\n" +
                    "rua, numero, bairro, cidade, uf, referencia, lotacao,\n" +
                    "data, vagasDisp, tipoEvento)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 2);";

            PreparedStatement pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, festa.getNome());
            pst.setString(2, festa.getDescricao());
            pst.setString(3, festa.getLocal().getEndereco().getRua());
            pst.setString(4, festa.getLocal().getEndereco().getNumero());
            pst.setString(5, festa.getLocal().getEndereco().getBairro());
            pst.setString(6, festa.getLocal().getEndereco().getCidade());
            pst.setString(7, festa.getLocal().getEndereco().getUf());
            pst.setString(8, festa.getLocal().getEndereco().getReferencia());
            pst.setInt(9, festa.getLocal().getLotacao());
            pst.setObject(10, festa.getData());
            pst.setInt(11, festa.getVagasDisponiveis());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                festa.setId(rs.getInt(1));
            }

            addAtracoes(festa, c);
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
    }

    private void addAtracoes(Festa festa, Connection c)
            throws SQLException {
        String sql = "INSERT INTO Festa_atracoes\n" +
                "(idEvento, atracao)\n" +
                "VALUES\n" +
                "(?, ?);";

        for (String atracao : festa.getAtracoes()) {
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, festa.getId());
            pst.setString(2, atracao);
            pst.execute();
        }
    }

    @Override
    public ArrayList<FestaDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Evento\n" +
                "WHERE tipoEvento = 2";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<FestaDTO> eventos = new ArrayList<>();
        while (rs.next()) {
            int idEvento = rs.getInt("idEvento");
            ArrayList<String> atracoes = buscaAtracoes(idEvento, c);
            FestaDTO evento = new FestaDTO(
                    idEvento,
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("rua"),
                    rs.getString("numero"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("uf"),
                    rs.getString("referencia"),
                    rs.getInt("lotacao"),
                    LocalDateTime.parse(rs.getString("data").substring(0, 19),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    rs.getInt("vagasDisp"),
                    atracoes
            );
            eventos.add(evento);
        }
        return eventos;
    }

    @Override
    public FestaDTO buscarPelaChave(Integer id)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Evento\n" +
                "WHERE idEvento = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        int idEvento = rs.getInt("idEvento");
        ArrayList<String> atracoes = buscaAtracoes(idEvento, c);
        return new FestaDTO(
                idEvento,
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getString("rua"),
                rs.getString("numero"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("uf"),
                rs.getString("referencia"),
                rs.getInt("lotacao"),
                LocalDateTime.parse(rs.getString("data").substring(0, 19),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                rs.getInt("vagasDisp"),
                atracoes
        );
    }

    private ArrayList<String> buscaAtracoes(int idEvento, Connection c)
            throws SQLException {
        ArrayList<String> atracoes = new ArrayList<>();
        String sql = "SELECT * FROM Festa_atracoes\n" +
                "WHERE idEvento = ?\n" +
                "AND ativo = 1";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, idEvento);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            atracoes.add(rs.getString("atracao"));
        }

        return atracoes;
    }
}
