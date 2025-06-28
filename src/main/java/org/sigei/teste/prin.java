package org.sigei.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.sigei.dao.evento.PalestraDAO;
import org.sigei.dao.pessoa.OrganizadorDAO;
import org.sigei.dto.evento.EventoDTO;
import org.sigei.dto.evento.PalestraDTO;
import org.sigei.dto.pessoa.OrganizadorDTO;
import org.sigei.exception.EventoNaoEncontradoException;

public class prin {
    static Map<String, String> campos = Map.of(
      "Nome", "Nome da Pessoa",
      "Sobrenome", "Sobrenome da Pessoa",
            "DataNasc", "Data de nascimento",
            "Lotação", "Lotação",
            "Login", "Login",
            "Senha", "Senha",
            "Rua", "Rua",
            "UF", "UF"
    );
    public static void main(String[] args) {
        try {
            OrganizadorDAO dao = new OrganizadorDAO();
//            dao.inserir(new PalestraBuilder().criar("Como conseguir um emprego de salário mínimo das 7 às 17", "mediocre",
//                    new LocalEvento(new Endereco("sim", "tbm", "aham", "pois é", "ss"),500),
//                    LocalDateTime.of(2025, 12, 2, 5, 0, 0),
//                     150, "Roberto"
//                    ));
//            System.out.println("Evento criado. Cheque o banco");
//            EventoDTO dto = dao.buscarPelaChave(1);
//            if (dto == null) {
//                throw new EventoNaoEncontradoException();
//            }
            for (OrganizadorDTO ev : dao.buscarTodos()) {
                System.out.println(ev.getEventos());
            }
        } catch (EventoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } //catch (ValidationException e) {
//            e.getMessages().forEach((chave, valor) -> {
//                System.out.println(MessageProvider.get(valor, campos.get(chave)));
//            });}
        catch (ClassNotFoundException e) {
            System.out.println("Erro ao procurar o driver");
        } catch (SQLException e) {
            System.out.println("Erro SQL");
        }
    }
}
