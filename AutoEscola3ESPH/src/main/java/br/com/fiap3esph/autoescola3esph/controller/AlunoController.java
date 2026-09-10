package br.com.fiap3esph.autoescola3esph.controller;

import br.com.fiap3esph.autoescola3esph.domain.aluno.Aluno;
import br.com.fiap3esph.autoescola3esph.domain.aluno.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Aluno> cadastrar(@RequestBody @Valid Aluno aluno, UriComponentsBuilder uriBuilder) {
        var alunoSalvo = repository.save(aluno);
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(alunoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(alunoSalvo);
    }
}