package br.gov.mt.seplag.artistalbumapi.modules.artist.exception;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException() {
        super("Artist not found.");
    }

    public ArtistNotFoundException(Long id) {
        super("Artist not found with id " + id);
    }
}