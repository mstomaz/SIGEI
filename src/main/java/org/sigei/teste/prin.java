package org.sigei.teste;

import org.sigei.exception.IngressoDuplicadoException;
import org.sigei.exception.IngressosEsgotadosException;
import org.sigei.exception.ValidationException;
import org.sigei.model.builder.*;
import java.time.LocalDateTime;
import java.util.Map;
import org.sigei.model.evento.Evento;
import org.sigei.model.pessoa.Participante;
import org.sigei.model.pessoa.Pessoa;
import org.sigei.validacao.*;
import org.sigei.model.*;
import java.time.LocalDate;

public class prin {
    static Map<String, String> campos = Map.of(
      "Nome", "Nome da Pessoa",
      "Sobrenome", "Sobrenome da Pessoa",
            "DataNasc", "Data de nascimento",
            "Lotação", "Lotação"
    );
    public static void main(String[] args) {
//        try {
//            Evento e = new PalestraBuilder().criar(0, "sadas",
//                    new LocalEventoBuilder().criar(new EnderecoBuilder().criar("asd", "dasd", "asf", "asfasf"), 2),
//                    LocalDateTime.of(2026, 1, 1, 17, 30, 5),
//                    2);
//            Ingresso i = new Ingresso(e);
//            Pessoa<CPF> p = new ParticipanteBuilder<CPF>().criar(new CPF("154.061.876-51"), "mateus", "tomaz", LocalDate.of(2000, 2, 1));
//            try {
//                ((Participante<?>)p).comprarIngresso(e, new Ingresso(e));
//                ((Participante<?>)p).comprarIngresso(e, new Ingresso(e));
//                System.out.println(e.getVagasDisponiveis());
//            } catch (IngressosEsgotadosException | IngressoDuplicadoException ex) {
//                System.out.println(ex.getMessage());
//            }
//        } catch (ValidationException e) {
//            e.getMessages().forEach((chave, valor) -> {
//                System.out.println(MessageProvider.get(valor, campos.get(chave)));
//            });
//        }
    }
}
