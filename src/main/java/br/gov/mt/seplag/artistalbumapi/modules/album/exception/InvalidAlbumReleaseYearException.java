package br.gov.mt.seplag.artistalbumapi.modules.album.exception;

public class InvalidAlbumReleaseYearException extends RuntimeException {

    public InvalidAlbumReleaseYearException() {
        super("Invalid album release year");
    }
}
