package org.sigei.dao.pessoa;

import org.sigei.dao.IEscritaDAO;
import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dao.evento.IGenericsEventoDAO;
import org.sigei.dao.evento.factory.EventoDAOFactory;
import org.sigei.dto.evento.EventoDTO;
import org.sigei.dto.pessoa.OrganizadorDTO;
import org.sigei.model.CPF;
import org.sigei.model.evento.Evento;
import org.sigei.model.pessoa.Organizador;
import java.sql.*;
import java.util.ArrayList;

public class OrganizadorDAO implements IEscritaDAO<Organizador, String>, ILeituraDAO<OrganizadorDTO, String> {
    @Override
    public void inserir(Organizador org)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO organizador\n" +
                "(cpf, nome, sobrenome)\n" +
                "VALUES\n" +
                "(?, ?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, org.getCpf().getDigitos());
        pst.setString(2, org.getNome());
        pst.setString(3, org.getSobrenome());
        pst.execute();
    }

    public void addEvento(Organizador org, Evento ev)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Organizador_eventos\n" +
                "(cpf, idEvento)\n" +
                "VALUES\n" +
                "(?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, org.getCpf().getDigitos());
        pst.setInt(2, ev.getId());
        pst.execute();
    }

    @Override
    public void apagar(String cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "DELETE FROM organizador\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf);

        pst.execute();
    }

    @Override
    public void alterar(Organizador org)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "UPDATE organizador\n" +
                "SET\n" +
                "nome = ?,\n" +
                "sobrenome = ?,\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, org.getNome());
        pst.setString(2, org.getSobrenome());
        pst.setString(3, org.getCpf().getDigitos());

        pst.execute();
    }

    @Override
    public ArrayList<OrganizadorDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM organizador;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<OrganizadorDTO> orgs = new ArrayList<>();
        while (rs.next()) {
            String cpf = rs.getString("cpf");
            OrganizadorDTO org = new OrganizadorDTO(
                    cpf,
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    buscarEventos(cpf, c)
            );
            orgs.add(org);
        }
        return orgs;
    }

    private ArrayList<EventoDTO> buscarEventos(String cpf, Connection c)
            throws SQLException, ClassNotFoundException {
        ArrayList<EventoDTO> eventos = new ArrayList<>();
        String sql = "SELECT e.*\n" +
                "FROM Evento e\n" +
                "JOIN Organizador_eventos oe ON e.idEvento = oe.idEvento\n" +
                "WHERE oe.cpf = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int idEvento = rs.getInt("e.idEvento");
            int tipoEvento = rs.getInt("e.tipoEvento");
            IGenericsEventoDAO dao = EventoDAOFactory.getEventoDAO(tipoEvento);
            EventoDTO evento = dao.buscarPelaChave(idEvento);
            eventos.add(evento);
            }
        return eventos;
    }

    @Override
    public OrganizadorDTO buscarPelaChave(String cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM organizador\n" +
                "WHERE cpf = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf);

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new OrganizadorDTO(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("sobrenome"),
                buscarEventos(cpf, c)
        );
    }
}
