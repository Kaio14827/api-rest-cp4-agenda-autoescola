package br.com.fiap3esph.autoescola3esph.controller;

import br.com.fiap3esph.autoescola3esph.domain.agenda.DadosAgendamento;
import br.com.fiap3esph.autoescola3esph.domain.agenda.DadosCancelamentoInstrucao;
import br.com.fiap3esph.autoescola3esph.domain.agenda.DetalhamentoAgendamento;
import br.com.fiap3esph.autoescola3esph.service.AgendaDeInstrucoes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoInstrucao dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}