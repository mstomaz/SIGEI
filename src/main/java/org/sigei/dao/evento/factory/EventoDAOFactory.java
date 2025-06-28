package org.sigei.dao.evento.factory;

import org.sigei.dao.evento.*;

public class EventoDAOFactory {
    public static IGenericsEventoDAO getEventoDAO(int tipoEvento) {
        switch (tipoEvento) {
            case 1:
                return new FeiraDAO();
            case 2:
                return new FestaDAO();
            case 3:
                return new OficinaDAO();
            case 4:
                return new PalestraDAO();
            case 5:
                return new ShowDAO();
            default:
                throw new IllegalArgumentException("Tipo de evento desconhecido.");
        }
    }
}
