package org.sigei.dao.evento;

import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.EventoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventoDAO implements ILeituraDAO<EventoDTO, Integer> {
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
