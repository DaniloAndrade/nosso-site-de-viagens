package br.com.dandrade.viagens.functions;

@FunctionalInterface
public interface FinderById<ID, Type> {
    Type findById(ID id);
}
