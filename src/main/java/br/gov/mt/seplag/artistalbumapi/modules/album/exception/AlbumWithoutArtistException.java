package br.gov.mt.seplag.artistalbumapi.modules.album.exception;

public class AlbumWithoutArtistException extends RuntimeException {
    public AlbumWithoutArtistException() {
        super("Album must have at least one artist");
    }
}