package br.com.javacourse.screenmatch.principal;

import br.com.javacourse.screenmatch.model.DadosEpisodio;
import br.com.javacourse.screenmatch.model.DadosSerie;
import br.com.javacourse.screenmatch.model.DadosTemporada;
import br.com.javacourse.screenmatch.model.Episodio;
import br.com.javacourse.screenmatch.services.ConsomeApi;
import br.com.javacourse.screenmatch.services.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final String ENDERECO = "http://www.omdbapi.com/?";
    private final String API_KEY = "apikey=dcd8819e&t=";

    private Scanner scanner = new Scanner(System.in);
    private ConsomeApi buscaApi = new ConsomeApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        System.out.println("Digite a série que deseja buscar: ");
        var serie = scanner.nextLine().replace(" ", "+");

        var json = buscaApi.obterDados(ENDERECO + API_KEY + serie);
        DadosSerie novaSerie = conversor.converteDados(json, DadosSerie.class);

        List<DadosTemporada> listaDeTemporadas = new ArrayList<>();
        for (int i =1; i <= novaSerie.totalTemporadas(); i++){
            json = buscaApi.obterDados(ENDERECO + API_KEY + serie + "&season=" + i);
            listaDeTemporadas.add(conversor.converteDados(json, DadosTemporada.class));
        }

        System.out.println("DADOS DA SÉRIE: ");
        System.out.println(novaSerie);
        listaDeTemporadas.forEach(System.out::println);

        listaDeTemporadas.forEach(e -> e.listaDeEpisodios().forEach(ep -> System.out.println(ep.titulo())));

        List<DadosEpisodio> episodiosTemporada = listaDeTemporadas.stream()
                .flatMap(t -> t.listaDeEpisodios().stream())
                .collect(Collectors.toList());

        episodiosTemporada.stream()
                .sorted(Comparator.comparing(DadosEpisodio::classificacao).reversed())
                .filter(e -> !e.classificacao().equalsIgnoreCase("N/A"))
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodiosSerie = listaDeTemporadas.stream()
                .flatMap(t -> t.listaDeEpisodios().stream()
                        .map(e -> new Episodio(t.numeroTemporada(), e))
                ).collect(Collectors.toList());

        episodiosSerie.forEach(System.out::println);
    }
}
