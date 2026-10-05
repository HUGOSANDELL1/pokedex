package service;

public class InvalidPokemonException extends RuntimeException {

    public InvalidPokemonException(String message) {
        super(message);
    }
}
