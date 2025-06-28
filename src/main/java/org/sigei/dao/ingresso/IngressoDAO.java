package org.sigei.dao.ingresso;

import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dao.evento.factory.EventoDAOFactory;
import org.sigei.dto.ingresso.IngressoDTO;
import org.sigei.model.Ingresso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngressoDAO implements ILeituraDAO<IngressoDTO, Integer> {
    public void inserir(Ingresso ing)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO ingresso\n" +
                "(idEvento)\n" +
                "VALUES\n" +
                "(?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, ing.getEvento().getId());

        pst.execute();
    }

    @Override
    public ArrayList<IngressoDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT i.*, tipoEvento\n" +
                "FROM ingresso i\n" +
                "JOIN evento e ON i.idEvento = e.idEvento;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<IngressoDTO> ingressos = new ArrayList<>();
        while (rs.next()){
            IngressoDTO ing = new IngressoDTO(
                    rs.getInt(1),
                    EventoDAOFactory.getEventoDAO(rs.getInt(3)).buscarPelaChave(rs.getInt(2))
            );
            ingressos.add(ing);
        }
        return ingressos;
    }

    @Override
    public IngressoDTO buscarPelaChave(Integer id)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT i.*, tipoEvento\n" +
                "FROM ingresso i\n" +
                "JOIN evento e ON i.idEvento = e.idEvento" +
                "WHERE idIngresso = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setInt(1, id);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new IngressoDTO(
                rs.getInt(1),
                EventoDAOFactory.getEventoDAO(rs.getInt(3)).buscarPelaChave(rs.getInt(2))
        );
    }
}
