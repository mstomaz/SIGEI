package org.sigei.teste;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import org.sigei.dao.evento.FestaDAO;
import org.sigei.dao.evento.PalestraDAO;
import org.sigei.dto.EventoDTO;
import org.sigei.dto.FeiraDTO;
import org.sigei.dto.FestaDTO;
import org.sigei.dto.PalestraDTO;
import org.sigei.exception.EventoNaoEncontradoException;
import org.sigei.exception.ValidationException;
import org.sigei.model.Endereco;
import org.sigei.model.builder.evento.FestaBuilder;
import org.sigei.model.builder.evento.PalestraBuilder;
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
            PalestraDAO dao = new PalestraDAO();
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
            dao.apagar(1);
            ArrayList<PalestraDTO> dtos = dao.buscarTodos();
            for (PalestraDTO dto : dtos) {
                System.out.println(dto.getTipoEvento());
                System.out.println(dto.getNome());
                System.out.println(dto.getNomePalestrante());
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
