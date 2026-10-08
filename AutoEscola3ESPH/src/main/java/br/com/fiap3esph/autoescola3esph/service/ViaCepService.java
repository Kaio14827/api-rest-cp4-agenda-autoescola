package br.com.fiap3esph.autoescola3esph.service;

import br.com.fiap3esph.autoescola3esph.infra.viacep.EnderecoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ViaCepService {

    private final RestClient restClient;

    public ViaCepService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://viacep.com.br")
                .build();
    }

    public EnderecoDto consultarCep(String cep) {
        return restClient.get()
                .uri("/ws/{cep}/json/", cep)
                .retrieve()
                .body(EnderecoDto.class);
    }
}