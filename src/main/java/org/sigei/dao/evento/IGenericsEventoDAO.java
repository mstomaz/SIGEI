package org.sigei.dao.evento;

import org.sigei.dto.evento.EventoDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IGenericsEventoDAO {
    ArrayList<EventoDTO> buscarTodos() throws SQLException, ClassNotFoundException;
    EventoDTO buscarPelaChave(Integer id) throws SQLException, ClassNotFoundException;
}
