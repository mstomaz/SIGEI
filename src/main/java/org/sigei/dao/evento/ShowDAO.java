package org.sigei.dao.evento;

import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.evento.ShowDTO;
import org.sigei.model.evento.Show;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ShowDAO extends BaseEventosDAO<Show, ShowDTO> {
    @Override
    public void inserir(Show show)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        c.setAutoCommit(false);

        try {
            String sql = "INSERT INTO evento\n" +
                    "(nome, descricao,\n" +
                    "rua, numero, bairro, cidade, uf, referencia, lotacao,\n" +
                    "data, vagasDisp, tipoEvento)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 5);";

            PreparedStatement pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, show.getNome());
            pst.setString(2, show.getDescricao());
            pst.setString(3, show.getLocal().getEndereco().getRua());
            pst.setString(4, show.getLocal().getEndereco().getNumero());
            pst.setString(5, show.getLocal().getEndereco().getBairro());
            pst.setString(6, show.getLocal().getEndereco().getCidade());
            pst.setString(7, show.getLocal().getEndereco().getUf());
            pst.setString(8, show.getLocal().getEndereco().getReferencia());
            pst.setInt(9, show.getLocal().getLotacao());
            pst.setObject(10, show.getData());
            pst.setInt(11, show.getVagasDisponiveis());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                show.setId(rs.getInt(1));
            }

            addLineUp(show, c);
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
    }

    private void addLineUp(Show show, Connection c)
            throws SQLException {
        String sql = "INSERT INTO Show_artistas\n" +
                "(idEvento, artista)\n" +
                "VALUES\n" +
                "(?, ?);";

        for (String artista : show.getLineUp()) {
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, show.getId());
            pst.setString(2, artista);
            pst.execute();
        }
    }

    @Override
    public ArrayList<ShowDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Evento\n" +
                "WHERE tipoEvento = 5";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<ShowDTO> eventos = new ArrayList<>();
        while (rs.next()) {
            int idEvento = rs.getInt("idEvento");
            ArrayList<String> lineUp = buscaLineUp(idEvento, c);
            ShowDTO evento = new ShowDTO(
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
                    lineUp
            );
            eventos.add(evento);
        }
        return eventos;
    }

    @Override
    public ShowDTO buscarPelaChave(Integer id)
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
        ArrayList<String> lineUp = buscaLineUp(idEvento, c);
        return new ShowDTO(
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
                lineUp
        );
    }

    private ArrayList<String> buscaLineUp(int idEvento, Connection c)
            throws SQLException {
        ArrayList<String> lineUp = new ArrayList<>();
        String sql = "SELECT * FROM Show_artistas\n" +
                "WHERE idEvento = ?\n" +
                "AND ativo = 1";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, idEvento);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            lineUp.add(rs.getString("artista"));
        }

        return lineUp;
    }
}
