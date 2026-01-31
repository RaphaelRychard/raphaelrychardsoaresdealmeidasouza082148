package br.gov.mt.seplag.artistalbumapi.modules.artist.repository;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.ArtistEntity;
import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    Page<ArtistEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<ArtistEntity> findByArtistType(ArtistType type, Sort sort);
}
