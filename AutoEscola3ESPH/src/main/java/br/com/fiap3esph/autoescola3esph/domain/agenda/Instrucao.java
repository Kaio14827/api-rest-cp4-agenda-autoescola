package br.com.fiap3esph.autoescola3esph.domain.agenda;

import br.com.fiap3esph.autoescola3esph.domain.aluno.Aluno;
import br.com.fiap3esph.autoescola3esph.domain.instrutor.Instrutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "instrucoes")
@Entity(name = "Instrucao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

@Column (name="data_hora")
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_cancelamento")
    private MotivoCancelamento motivoCancelamento;

    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }
}