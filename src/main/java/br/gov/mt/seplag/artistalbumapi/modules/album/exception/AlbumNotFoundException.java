package br.gov.mt.seplag.artistalbumapi.modules.album.exception;

public class AlbumNotFoundException extends RuntimeException {

  public AlbumNotFoundException(Long id) {
    super("Album not found with id " + id);
  }
}
