package br.gov.mt.seplag.artistalbumapi.modules.album.services;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;

public interface AlbumNotificationService {
    void notifyNewAlbum(AlbumResponseDTO album);
}