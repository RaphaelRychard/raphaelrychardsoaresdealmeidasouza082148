package br.gov.mt.seplag.artistalbumapi.modules.album.repository;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.AlbumEntity;
import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
    Page<AlbumEntity> findByArtists_ArtistType(ArtistType artistType, Pageable pageable);
}
