package org.sigei.dao.evento;

import org.sigei.dao.IGenericsDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.EventoDTO;
import org.sigei.model.evento.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventoDAO implements IGenericsDAO<Evento, Integer, EventoDTO> {

    @Override
    public void inserir(Evento evento)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO evento\n" +
                "(nome, descricao,\n" +
                "rua, numero, bairro, cidade, uf, referencia, lotacao,\n" +
                "data, vagasDisp, tipoEvento)\n" +
                "VALUES\n" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, evento.getNome());
        pst.setString(2, evento.getDescricao());
        pst.setString(3, evento.getLocal().getEndereco().getRua());
        pst.setString(4, evento.getLocal().getEndereco().getNumero());
        pst.setString(5, evento.getLocal().getEndereco().getBairro());
        pst.setString(6, evento.getLocal().getEndereco().getCidade());
        pst.setString(7, evento.getLocal().getEndereco().getUf());
        pst.setString(8, evento.getLocal().getEndereco().getReferencia());
        pst.setInt(9, evento.getLocal().getLotacao());
        pst.setObject(10, evento.getData());
        pst.setInt(11, evento.getVagasDisponiveis());
        pst.setInt(12, evento.getTipoEvento().getIdTipo());

        pst.execute();
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
    public void alterar(Evento evento)
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
        pst.setString(1, evento.getNome());
        pst.setString(2, evento.getDescricao());
        pst.setString(3, evento.getLocal().getEndereco().getRua());
        pst.setString(4, evento.getLocal().getEndereco().getNumero());
        pst.setString(5, evento.getLocal().getEndereco().getBairro());
        pst.setString(6, evento.getLocal().getEndereco().getCidade());
        pst.setString(7, evento.getLocal().getEndereco().getUf());
        pst.setString(8, evento.getLocal().getEndereco().getReferencia());
        pst.setObject(10, evento.getData());
        pst.setInt(11, evento.getVagasDisponiveis());
        pst.setInt(12, evento.getId());

        pst.execute();
    }

    @Override
    public ArrayList<EventoDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM Evento";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<EventoDTO> eventos = new ArrayList<>();
        while (rs.next()){
            EventoDTO evento = new EventoDTO(
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
                    LocalDateTime.parse(rs.getString("data").substring(0,19),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    rs.getInt("vagasDisp"),
                    rs.getInt("tipoEvento")
            );
            eventos.add(evento);
        }
        return eventos;
    }

    @Override
    public EventoDTO buscarPelaChave(Integer id)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM Evento\n" +
                "WHERE idEvento = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new EventoDTO(
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
                LocalDateTime.parse(rs.getString("data").substring(0,19),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                rs.getInt("vagasDisp"),
                rs.getInt("tipoEvento")
        );
    }
}
