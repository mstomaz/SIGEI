package org.sigei.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ILeituraDAO<C, K> {
    ArrayList<C> buscarTodos() throws SQLException, ClassNotFoundException;
    C buscarPelaChave(K id) throws SQLException, ClassNotFoundException;
}
