package org.sigei.teste;

import java.util.Map;

import org.sigei.exception.ValidationException;
import org.sigei.model.builder.pessoa.ParticipanteBuilder;
import org.sigei.model.pessoa.Pessoa;
import org.sigei.model.usuario.Credenciais;
import org.sigei.model.usuario.Usuario;
import org.sigei.model.*;
import org.sigei.validacao.MessageProvider;

import java.time.LocalDate;

public class prin {
    static Map<String, String> campos = Map.of(
      "Nome", "Nome da Pessoa",
      "Sobrenome", "Sobrenome da Pessoa",
            "DataNasc", "Data de nascimento",
            "Lotação", "Lotação"
    );
    public static void main(String[] args) {
        try {
            Pessoa<CPF> p = new ParticipanteBuilder<CPF>().criar(new CPF("15406187651"), "asdasd", "asdasd", LocalDate.of(2000, 2, 1));
            Usuario<CPF> usuario = new Usuario<>(p, new Credenciais("sdijasd", "sdfasd"));
            System.out.println(usuario.getTipoUsuario());
        } catch (ValidationException e) {
            e.getMessages().forEach((chave, valor) -> {
                System.out.println(MessageProvider.get(valor, campos.get(chave)));
            });
        }
    }
}
