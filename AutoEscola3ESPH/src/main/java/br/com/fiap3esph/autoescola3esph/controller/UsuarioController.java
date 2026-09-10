package br.com.fiap3esph.autoescola3esph.controller;

import br.com.fiap3esph.autoescola3esph.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados, senhaCriptografada);
        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(@PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/alterar-senha")
    @Transactional
    public ResponseEntity alterarSenha(@RequestBody @Valid DadosAlteracaoSenha dados) {
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!passwordEncoder.matches(dados.senhaAtual(), usuario.getPassword())) {
            return ResponseEntity.badRequest().body("Senha atual incorreta.");
        }

        usuario.atualizarSenha(passwordEncoder.encode(dados.novaSenha()));
        repository.save(usuario);

        return ResponseEntity.ok("Senha alterada com sucesso!");
    }
}