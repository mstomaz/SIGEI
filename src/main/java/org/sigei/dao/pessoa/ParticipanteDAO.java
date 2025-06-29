package org.sigei.dao.pessoa;

import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dao.ingresso.IngressoDAO;
import org.sigei.dto.ingresso.IngressoDTO;
import org.sigei.dto.pessoa.ParticipanteDTO;
import org.sigei.dto.pessoa.PessoaDTO;
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

public class ParticipanteDAO extends BasePessoaDAO<Participante> implements IGenericsPessoaDAO {
    @Override
    public void inserir(Participante prtc)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        c.setAutoCommit(false);

        try {
            String sql = "INSERT INTO Pessoa\n" +
                    "(cpf, nome, sobrenome, tipoPessoa)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, 2);";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, prtc.getCpf().getDigitos());
            pst.setString(2, prtc.getNome());
            pst.setString(3, prtc.getSobrenome());

            pst.execute();

            inserirParticipante(prtc, c);
            c.commit();
        } catch(SQLException e) {
            c.rollback();
            throw e;
        }
    }

    private void inserirParticipante(Participante prtc, Connection c)
            throws SQLException {
        String sql = "INSERT INTO Participante\n" +
                "(cpf, dataNasc)\n" +
                "VALUES\n" +
                "(?, ?);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, prtc.getCpf().getDigitos());
        pst.setObject(2, prtc.getDataNasc());

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
    public ArrayList<PessoaDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Participante;";

        PreparedStatement pst = c.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        ArrayList<PessoaDTO> participantes = new ArrayList<>();
        while (rs.next()) {
            CPF cpf = new CPF(rs.getString("cpf"));
            PessoaDTO pessoa = new PessoaDAO().buscarPelaChave(cpf);
            ParticipanteDTO prtc = new ParticipanteDTO(
                    cpf.getDigitos(),
                    pessoa.getNome(),
                    pessoa.getSobrenome(),
                    LocalDate.parse(rs.getString("dataNasc"),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    buscaIngressos(cpf.getDigitos(), c)
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
    public ParticipanteDTO buscarPelaChave(CPF cpf)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM Participante\n" +
                "WHERE cpf = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, cpf.getDigitos());

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            return null;
        }

        PessoaDTO pessoa = new PessoaDAO().buscarPelaChave(cpf);
        return new ParticipanteDTO(
                cpf.getDigitos(),
                pessoa.getNome(),
                pessoa.getSobrenome(),
                LocalDate.parse(rs.getString("dataNasc"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                buscaIngressos(cpf.getDigitos(), c)
        );
    }
}
