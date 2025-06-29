package org.sigei.dao.evento;

import org.sigei.dao.IEscritaDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.dto.evento.EventoDTO;
import org.sigei.model.evento.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseEventosDAO<C extends Evento> implements IEscritaDAO<C, Integer> {
    @Override
    public abstract void inserir(C obj) throws SQLException, ClassNotFoundException;

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
    public void alterar(C obj)
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
                "dataEvento = ?,\n" +
                "vagasDisp = ?\n" +
                "WHERE idEvento = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, obj.getNome());
        pst.setString(2, obj.getDescricao());
        pst.setString(3, obj.getLocal().getEndereco().getRua());
        pst.setString(4, obj.getLocal().getEndereco().getNumero());
        pst.setString(5, obj.getLocal().getEndereco().getBairro());
        pst.setString(6, obj.getLocal().getEndereco().getCidade());
        pst.setString(7, obj.getLocal().getEndereco().getUf());
        pst.setString(8, obj.getLocal().getEndereco().getReferencia());
        pst.setObject(10, obj.getData());
        pst.setInt(11, obj.getVagasDisponiveis());
        pst.setInt(12, obj.getId());

        pst.execute();
    }
}
