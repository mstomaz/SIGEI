package org.sigei.dao.evento;

import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.evento.EventoDTO;
import org.sigei.dto.evento.OficinaDTO;
import org.sigei.model.evento.Oficina;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OficinaDAO extends BaseEventosDAO<Oficina> implements IGenericsEventoDAO {
    @Override
    public void inserir(Oficina oficina)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        c.setAutoCommit(false);

        try {
            String sql = "INSERT INTO evento\n" +
                    "(nome, descricao,\n" +
                    "rua, numero, bairro, cidade, uf, referencia, lotacao,\n" +
                    "dataEvento, vagasDisp, tipoEvento)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 3);";

            PreparedStatement pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, oficina.getNome());
            pst.setString(2, oficina.getDescricao());
            pst.setString(3, oficina.getLocal().getEndereco().getRua());
            pst.setString(4, oficina.getLocal().getEndereco().getNumero());
            pst.setString(5, oficina.getLocal().getEndereco().getBairro());
            pst.setString(6, oficina.getLocal().getEndereco().getCidade());
            pst.setString(7, oficina.getLocal().getEndereco().getUf());
            pst.setString(8, oficina.getLocal().getEndereco().getReferencia());
            pst.setInt(9, oficina.getLocal().getLotacao());
            pst.setObject(10, oficina.getData());
            pst.setInt(11, oficina.getVagasDisponiveis());

            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                oficina.setId(rs.getInt(1));
            }

            inserirTema(oficina, c);
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
    }

    private void inserirTema(Oficina oficina, Connection c)
            throws SQLException {

        String sql = "INSERT INTO Oficina\n" +
                "(idEvento, tema)\n" +
                "VALUES\n" +
                "(?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, oficina.getId());
        pst.setString(2, oficina.getTema());
        pst.execute();
    }

    @Override
    public ArrayList<EventoDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT e.*, tema FROM Evento e\n" +
                "JOIN Oficina o ON e.idEvento = o.idEvento;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<EventoDTO> eventos = new ArrayList<>();
        while (rs.next()) {
            OficinaDTO evento = new OficinaDTO(
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
                    LocalDateTime.parse(rs.getString("dataEvento").substring(0, 19),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    rs.getInt("vagasDisp"),
                    rs.getString("tema")
            );
            eventos.add(evento);
        }
        return eventos;
    }

    @Override
    public OficinaDTO buscarPelaChave(Integer id)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT e.*, tema FROM Evento e\n" +
                "JOIN Oficina o ON e.idEvento = o.idEvento\n" +
                "WHERE e.idEvento = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new OficinaDTO(
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
                LocalDateTime.parse(rs.getString("dataEvento").substring(0, 19),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                rs.getInt("vagasDisp"),
                rs.getString("tema")
        );
    }
}
