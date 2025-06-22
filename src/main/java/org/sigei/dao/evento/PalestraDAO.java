package org.sigei.dao.evento;

import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.model.evento.Palestra;
import org.sigei.dto.PalestraDTO;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PalestraDAO extends BaseEventosDAO<Palestra, PalestraDTO> {
    @Override
    public void inserir(Palestra palestra)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        c.setAutoCommit(false);

        try {
            String sql = "INSERT INTO evento\n" +
                    "(nome, descricao,\n" +
                    "rua, numero, bairro, cidade, uf, referencia, lotacao,\n" +
                    "data, vagasDisp, tipoEvento)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 4);";

            PreparedStatement pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, palestra.getNome());
            pst.setString(2, palestra.getDescricao());
            pst.setString(3, palestra.getLocal().getEndereco().getRua());
            pst.setString(4, palestra.getLocal().getEndereco().getNumero());
            pst.setString(5, palestra.getLocal().getEndereco().getBairro());
            pst.setString(6, palestra.getLocal().getEndereco().getCidade());
            pst.setString(7, palestra.getLocal().getEndereco().getUf());
            pst.setString(8, palestra.getLocal().getEndereco().getReferencia());
            pst.setInt(9, palestra.getLocal().getLotacao());
            pst.setObject(10, palestra.getData());
            pst.setInt(11, palestra.getVagasDisponiveis());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                palestra.setId(rs.getInt(1));
            }

            inserirPalestrante(palestra, c);
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
    }

    private void inserirPalestrante(Palestra palestra, Connection c)
            throws SQLException {

        String sql = "INSERT INTO Palestra\n" +
                "(idEvento, palestrante)\n" +
                "VALUES\n" +
                "(?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, palestra.getId());
        pst.setString(2, palestra.getNomePalestrante());
        pst.execute();
    }

    @Override
    public ArrayList<PalestraDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT e.*, palestrante FROM Evento e\n" +
                "JOIN Palestra p ON e.idEvento = p.idEvento;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<PalestraDTO> eventos = new ArrayList<>();
        while (rs.next()) {
            PalestraDTO evento = new PalestraDTO(
                    rs.getInt("idEvento"),
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
                    rs.getString("palestrante")
            );
            eventos.add(evento);
        }
        return eventos;
    }

    @Override
    public PalestraDTO buscarPelaChave(Integer id)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT e.*, palestrante FROM Evento e\n" +
                "JOIN Palestra p ON e.idEvento = p.idEvento\n" +
                "WHERE e.idEvento = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new PalestraDTO(
                rs.getInt("idEvento"),
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
                rs.getString("palestrante")
        );
    }
}
