package org.sigei.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEscritaDAO<C, K> {
    void inserir(C obj) throws SQLException, ClassNotFoundException;
    void apagar(K id) throws SQLException, ClassNotFoundException;
    void alterar(C obj) throws SQLException, ClassNotFoundException;
}
