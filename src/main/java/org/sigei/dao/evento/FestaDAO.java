package org.sigei.dao.evento;

import org.sigei.dao.IEscritaDAO;
import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.FestaDTO;
import org.sigei.model.evento.Festa;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FestaDAO implements IEscritaDAO<Festa, Integer>, ILeituraDAO<FestaDTO, Integer> {
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
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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
            pst.setInt(12, festa.getTipoEvento().getIdTipo());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                festa.setId(rs.getInt(1));
            }

            if (addAtracoes(festa, c)) {
                c.commit();
            } else {
                c.rollback();
            }
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
    }

    private boolean addAtracoes(Festa festa, Connection c)
            throws SQLException {
        try {
            String sql = "INSERT INTO Festa_atracoes\n" +
                    "(idFesta, atracao)\n" +
                    "VALUES\n" +
                    "(?, ?);";

            for (String atracao : festa.getAtracoes()) {
                PreparedStatement pst = c.prepareStatement(sql);
                pst.setInt(1, festa.getId());
                pst.setString(2, atracao);
                pst.execute();
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public void apagar(Integer id)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "DELETE FROM evento\n" +
                "WHERE idEvento = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, id);

        pst.execute();
    }

    @Override
    public void alterar(Festa festa)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "UPDATE evento\n" +
                "SET\n" +
                "nome = ?,\n" +
                "descricao = ?,\n" +
                "rua = ?,\n" +
                "numero = ?,\n" +
                "bairro = ?,\n" +
                "cidade = ?,\n" +
                "uf = ?,\n" +
                "referencia = ?,\n" +
                "data = ?,\n" +
                "vagasDisp = ?\n" +
                "WHERE `idEvento` = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, festa.getNome());
        pst.setString(2, festa.getDescricao());
        pst.setString(3, festa.getLocal().getEndereco().getRua());
        pst.setString(4, festa.getLocal().getEndereco().getNumero());
        pst.setString(5, festa.getLocal().getEndereco().getBairro());
        pst.setString(6, festa.getLocal().getEndereco().getCidade());
        pst.setString(7, festa.getLocal().getEndereco().getUf());
        pst.setString(8, festa.getLocal().getEndereco().getReferencia());
        pst.setObject(10, festa.getData());
        pst.setInt(11, festa.getVagasDisponiveis());
        pst.setInt(12, festa.getId());

        pst.execute();
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

    private ArrayList<String> buscaAtracoes(int idFesta, Connection c)
            throws SQLException {
        ArrayList<String> atracoes = new ArrayList<>();
        String sql = "SELECT * FROM Festa_atracoes\n" +
                "WHERE idFesta = ?\n" +
                "AND ativo = 1";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, idFesta);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            atracoes.add(rs.getString("atracao"));
        }

        return atracoes;
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
}
