package br.gov.mt.seplag.artistalbumapi.modules.regional.repository;

import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionalRepository extends JpaRepository<Regional, Long> {
    Optional<Regional> findByExternalId(Integer externalId);
    List<Regional> findByActiveTrue();
    boolean existsByExternalId(Integer externalId);
}
