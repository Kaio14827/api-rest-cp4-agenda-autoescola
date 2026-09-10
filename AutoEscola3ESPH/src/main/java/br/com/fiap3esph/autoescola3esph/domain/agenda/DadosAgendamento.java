package br.com.fiap3esph.autoescola3esph.domain.agenda;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamento(
        @NotNull Long idAluno,
        Long idInstrutor,
        @NotNull @Future LocalDateTime data
) {
        // Alias para manter compatibilidade com os validadores existentes
        public LocalDateTime dataHora() {
                return this.data;
        }
}