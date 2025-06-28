package org.sigei.dao.evento;

import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.evento.EventoDTO;
import org.sigei.model.evento.Feira;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.sigei.dto.evento.FeiraDTO;

public class FeiraDAO extends BaseEventosDAO<Feira, FeiraDTO> implements IGenericsEventoDAO {
    @Override
    public void inserir(Feira feira)
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql = "INSERT INTO evento\n" +
                "(nome, descricao,\n" +
                "rua, numero, bairro, cidade, uf, referencia, lotacao,\n" +
                "dataEvento, vagasDisp, tipoEvento)\n" +
                "VALUES\n" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1);";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, feira.getNome());
        pst.setString(2, feira.getDescricao());
        pst.setString(3, feira.getLocal().getEndereco().getRua());
        pst.setString(4, feira.getLocal().getEndereco().getNumero());
        pst.setString(5, feira.getLocal().getEndereco().getBairro());
        pst.setString(6, feira.getLocal().getEndereco().getCidade());
        pst.setString(7, feira.getLocal().getEndereco().getUf());
        pst.setString(8, feira.getLocal().getEndereco().getReferencia());
        pst.setInt(9, feira.getLocal().getLotacao());
        pst.setObject(10, feira.getData());
        pst.setInt(11, feira.getVagasDisponiveis());

        pst.execute();
    }

    @Override
    public ArrayList<EventoDTO> buscarTodos()
            throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnection();

        String sql= "SELECT * FROM Evento\n" +
                "WHERE tipoEvento = 1";

        PreparedStatement pst = c.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        ArrayList<EventoDTO> eventos = new ArrayList<>();
        while (rs.next()){
            FeiraDTO evento = new FeiraDTO(
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
                    LocalDateTime.parse(rs.getString("dataEvento").substring(0,19),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    rs.getInt("vagasDisp")
            );
            eventos.add(evento);
        }
        return eventos;
    }

    @Override
    public FeiraDTO buscarPelaChave(Integer id)
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

        return new FeiraDTO(
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
                LocalDateTime.parse(rs.getString("dataEvento").substring(0,19),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                rs.getInt("vagasDisp")
        );
    }
}
