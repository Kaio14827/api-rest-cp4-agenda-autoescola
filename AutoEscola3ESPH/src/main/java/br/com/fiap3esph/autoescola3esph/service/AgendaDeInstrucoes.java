package br.com.fiap3esph.autoescola3esph.service;

import br.com.fiap3esph.autoescola3esph.domain.agenda.*;
import br.com.fiap3esph.autoescola3esph.domain.aluno.AlunoRepository;
import br.com.fiap3esph.autoescola3esph.domain.instrutor.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AgendaDeInstrucoes {

    @Autowired
    private InstrucaoRepository instrucaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Transactional
    public DetalhamentoAgendamento agendar(DadosAgendamento dados) {
        if (!alunoRepository.existsById(dados.idAluno())) {
            throw new ValidacaoException("Id do aluno informado não existe!");
        }

        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new ValidacaoException("Id do instrutor informado não existe!");
        }

        var aluno = alunoRepository.getReferenceById(dados.idAluno());
        var instrutor = instrutorRepository.getReferenceById(dados.idInstrutor());

        var instrucao = new Instrucao(null, instrutor, aluno, dados.data(), null);
        instrucaoRepository.save(instrucao);

        return new DetalhamentoAgendamento(instrucao);
    }

    @Transactional
    public void cancelar(DadosCancelamentoInstrucao dados) {
        if (!instrucaoRepository.existsById(dados.idInstrucao())) {
            throw new ValidacaoException("Id da instrução informado não existe!");
        }

        var instrucao = instrucaoRepository.getReferenceById(dados.idInstrucao());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, instrucao.getDataHora()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Instrução somente pode ser cancelada com antecedência mínima de 24 horas!");
        }

        instrucao.cancelar(dados.motivo());
    }
}