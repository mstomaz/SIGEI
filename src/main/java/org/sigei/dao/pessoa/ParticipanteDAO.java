package org.sigei.dao.pessoa;

import org.sigei.dao.IEscritaDAO;
import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dao.evento.IGenericsEventoDAO;
import org.sigei.dao.evento.factory.EventoDAOFactory;
import org.sigei.dao.ingresso.IngressoDAO;
import org.sigei.dto.evento.EventoDTO;
import org.sigei.dto.ingresso.IngressoDTO;
import org.sigei.dto.pessoa.OrganizadorDTO;
import org.sigei.dto.pessoa.ParticipanteDTO;
import org.sigei.model.CPF;
import org.sigei.model.Ingresso;
import org.sigei.model.pessoa.Participante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParticipanteDAO implements IEscritaDAO<Participante, String>, ILeituraDAO<ParticipanteDTO, String> {
    @Override
    public void inserir(Participante prtc)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO participante\n" +
                "(cpf, nome, sobrenome, dataNasc)\n" +
                "VALUES\n" +
                "(?, ?, ?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, prtc.getCpf().getDigitos());
        pst.setString(2, prtc.getNome());
        pst.setString(3, prtc.getSobrenome());
        pst.setObject(4, prtc.getDataNasc());

        pst.execute();
    }

    public void addIngresso(Participante prtc, Ingresso ing)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO Participante_ingressos\n" +
                "(cpf, idIngresso)\n" +
                "VALUES\n" +
                "(?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, prtc.getCpf().getDigitos());
        pst.setInt(2, ing.getId());
        pst.execute();
    }

    @Override
    public void apagar(String cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "DELETE FROM participante\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf);

        pst.execute();
    }

    @Override
    public void alterar(Participante prtc)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "UPDATE participante\n" +
                "SET\n" +
                "nome = ?,\n" +
                "sobrenome = ?,\n" +
                "dataNasc = ?\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, prtc.getCpf().getDigitos());
        pst.setString(2, prtc.getNome());
        pst.setString(3, prtc.getSobrenome());
        pst.setObject(4, prtc.getDataNasc());

        pst.execute();
    }

    @Override
    public ArrayList<ParticipanteDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Participante;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<ParticipanteDTO> participantes = new ArrayList<>();
        while (rs.next()) {
            String cpf = rs.getString("cpf");
            ParticipanteDTO prtc = new ParticipanteDTO(
                    cpf,
                    rs.getString("nome"),
                    rs.getString("sobrenome"),
                    LocalDate.parse(rs.getString("dataNasc"),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    buscaIngressos(cpf, c)
            );
            participantes.add(prtc);
        }
        return participantes;
    }

    private ArrayList<IngressoDTO> buscaIngressos(String cpf, Connection c)
            throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Participante_ingressos\n" +
                "WHERE cpf = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf);

        ResultSet rs = pst.executeQuery();

        ArrayList<IngressoDTO> ingressos = new ArrayList<>();
        while (rs.next()) {
            IngressoDTO ing = new IngressoDAO().buscarPelaChave(rs.getInt(2));
            ingressos.add(ing);
        }
        return ingressos;
    }

    @Override
    public ParticipanteDTO buscarPelaChave(String cpf)
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

        return new ParticipanteDTO(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("sobrenome"),
                LocalDate.parse(rs.getString("dataNasc"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                buscaIngressos(cpf, c)
        );
    }
}
