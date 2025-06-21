package org.sigei.teste;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.sigei.dao.evento.EventoDAO;
import org.sigei.dto.EventoDTO;
import org.sigei.exception.EventoNaoEncontradoException;
import org.sigei.exception.ValidationException;
import org.sigei.model.builder.EnderecoBuilder;
import org.sigei.model.builder.evento.FeiraBuilder;
import org.sigei.model.builder.evento.LocalEventoBuilder;
import org.sigei.model.builder.pessoa.ParticipanteBuilder;
import org.sigei.model.builder.usuario.UsuarioBuilder;
import org.sigei.model.evento.LocalEvento;
import org.sigei.model.pessoa.Pessoa;
import org.sigei.model.builder.usuario.CredenciasBuilder;
import org.sigei.model.usuario.Usuario;
import org.sigei.model.*;
import org.sigei.validacao.MessageProvider;

import java.time.LocalDate;

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
            EventoDAO dao = new EventoDAO();
//            dao.inserir(new FeiraBuilder().criar("lol", "asdkadiajs",
//                    new LocalEvento(new Endereco("sdasd", "lalsds", "aeeasd", "asdasd", "as"), 200),
//                    LocalDateTime.of(2026, 11, 17, 0, 0, 0), 200
//                    ));
//            System.out.println("Evento criado. Cheque o banco");
//            EventoDTO dto = dao.buscarPelaChave(1);
//            if (dto == null) {
//                throw new EventoNaoEncontradoException();
//            }
            ArrayList<EventoDTO> dtos = dao.buscarTodos();
            for (EventoDTO dto : dtos) {
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
