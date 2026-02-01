package br.gov.mt.seplag.artistalbumapi.modules.regional.repository;

import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.RegionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionalRepository extends JpaRepository<RegionalEntity, Long> {
    Optional<RegionalEntity> findByExternalId(Integer externalId);
    List<RegionalEntity> findByActiveTrue();
    boolean existsByExternalId(Integer externalId);
}
