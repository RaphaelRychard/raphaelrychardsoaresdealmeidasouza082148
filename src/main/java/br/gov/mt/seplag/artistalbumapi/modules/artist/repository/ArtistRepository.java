package br.gov.mt.seplag.artistalbumapi.modules.artist.repository;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
}
