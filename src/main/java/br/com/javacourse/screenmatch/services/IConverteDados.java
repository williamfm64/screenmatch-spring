package br.com.javacourse.screenmatch.services;

public interface IConverteDados {
    <T> T converteDados(String json, Class<T> classe);
}
