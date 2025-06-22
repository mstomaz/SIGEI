package org.sigei.dao.evento;

import org.sigei.dao.IEscritaDAO;
import org.sigei.dao.ILeituraDAO;
import org.sigei.dao.conexao.ConnectionFactory;
import org.sigei.model.evento.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseEventosDAO<C, K> implements IEscritaDAO<C, Integer>, ILeituraDAO<K, Integer> {
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
                "data = ?,\n" +
                "vagasDisp = ?\n" +
                "WHERE `idEvento` = ?;";

        PreparedStatement pst = c.prepareStatement(sql);
        Evento evento = (Evento)obj;
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
    public abstract ArrayList<K> buscarTodos() throws SQLException, ClassNotFoundException;

    @Override
    public abstract K buscarPelaChave(Integer id) throws SQLException, ClassNotFoundException;
}
