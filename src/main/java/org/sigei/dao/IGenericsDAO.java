package org.sigei.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IGenericsDAO<C, K, T> {
    void inserir(C obj) throws SQLException, ClassNotFoundException;
    void apagar(K id) throws SQLException, ClassNotFoundException;
    void alterar(C obj) throws SQLException, ClassNotFoundException;
    ArrayList<T> buscarTodos() throws SQLException, ClassNotFoundException;
    T buscarPelaChave(K id) throws SQLException, ClassNotFoundException;
}
