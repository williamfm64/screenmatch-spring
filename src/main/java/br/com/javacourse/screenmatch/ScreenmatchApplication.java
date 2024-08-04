package br.com.javacourse.screenmatch;

import br.com.javacourse.screenmatch.model.DadosEpisodio;
import br.com.javacourse.screenmatch.model.DadosSerie;
import br.com.javacourse.screenmatch.services.ConsomeApi;
import br.com.javacourse.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!!!");

		var consumirApi = new ConsomeApi();
		var json = consumirApi.obterDados("http://www.omdbapi.com/?apikey=dcd8819e&t=the+walking+dead");
		System.out.println(json);

		var conversor = new ConverteDados();

		DadosSerie walking = conversor.converteDados(json, DadosSerie.class);
		System.out.println(walking);

		json = consumirApi.obterDados("http://www.omdbapi.com/?apikey=dcd8819e&t=the+walking+dead&season=1&episode=2");
		DadosEpisodio episodio = conversor.converteDados(json, DadosEpisodio.class);
		System.out.println(episodio);
	}
}
