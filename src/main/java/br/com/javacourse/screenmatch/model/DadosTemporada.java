package br.com.javacourse.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") int numeroTemporada,
                             @JsonAlias("Episodes") List<DadosEpisodio> listaDeEpisodios) {
}
