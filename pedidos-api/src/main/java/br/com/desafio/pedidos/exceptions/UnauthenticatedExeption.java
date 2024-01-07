package br.com.desafio.pedidos.exceptions;

public class UnauthenticatedExeption extends Exception {
    public UnauthenticatedExeption(String message) {
        super(message);
    }
}
