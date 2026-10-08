package br.com.fiap3esph.autoescola3esph.infra.viacep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoDto(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {}