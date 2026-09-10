package br.com.fiap3esph.autoescola3esph.domain.agenda;

import java.time.LocalDateTime;

public record DetalhamentoAgendamento(
        Long id,
        Long idInstrutor,
        Long idAluno,
        LocalDateTime data
) {
    public DetalhamentoAgendamento(Instrucao instrucao) {
        this(
                instrucao.getId(),
                instrucao.getInstrutor() != null ? instrucao.getInstrutor().getId() : null,
                instrucao.getAluno().getId(),
                instrucao.getDataHora()
        );
    }
}