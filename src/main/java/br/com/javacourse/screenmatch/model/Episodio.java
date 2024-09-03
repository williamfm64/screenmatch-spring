package br.com.javacourse.screenmatch.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private String titulo;
    private LocalDate lancamento;
    private double classificacao;
    private int numeroEpisodio;
    private int numeroTemporada;

    public Episodio(int temporada, DadosEpisodio episodio) {
        this.titulo = episodio.titulo();
        try{
            this.lancamento = LocalDate.parse(episodio.lancamento());
        }catch (DateTimeParseException e){
            this.lancamento = null;
        }
        try {
            this.classificacao = Double.valueOf(episodio.classificacao());
        }catch (NumberFormatException e){
            this.classificacao = 0.0;
        }
        this.numeroEpisodio = episodio.numeroEpisodio();
        this.numeroTemporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public Double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Double classificacao) {
        this.classificacao = classificacao;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "titulo='" + titulo + '\'' +
                ", lancamento='" + lancamento + '\'' +
                ", classificacao='" + classificacao + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", numeroTemporada=" + numeroTemporada +
                '}';
    }
}

