package org.sigei.teste;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import org.sigei.dao.evento.FestaDAO;
import org.sigei.dto.FeiraDTO;
import org.sigei.dto.FestaDTO;
import org.sigei.exception.EventoNaoEncontradoException;
import org.sigei.exception.ValidationException;
import org.sigei.model.Endereco;
import org.sigei.model.builder.evento.FestaBuilder;
import org.sigei.model.evento.LocalEvento;
import org.sigei.validacao.MessageProvider;

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
            FestaDAO dao = new FestaDAO();
//            dao.inserir(new FestaBuilder().criar("festa country", "muito sertanjeo e bebida (festa sem alcool)",
//                    new LocalEvento(new Endereco("sdasd", "lalsds", "aeeasd", "asdasd", "as"),50000),
//                    LocalDateTime.of(2026, 5, 2, 22, 0, 0),
//                     50000, "SLAYER", "TWICE", "Larissa Manoela", "John Lennon"
//                    ));
//            System.out.println("Evento criado. Cheque o banco");
//            EventoDTO dto = dao.buscarPelaChave(1);
//            if (dto == null) {
//                throw new EventoNaoEncontradoException();
//            }
            dao.apagar(3);
            ArrayList<FestaDTO> dtos = dao.buscarTodos();
            for (FestaDTO dto : dtos) {
                System.out.println(dto.getTipoEvento());
                System.out.println(dto.getNome());
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
