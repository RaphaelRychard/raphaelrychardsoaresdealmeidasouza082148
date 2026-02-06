package br.gov.mt.seplag.artistalbumapi.modules.regional.repository;

import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionalRepository extends JpaRepository<Regional, Long> {
    boolean existsByExternalIdAndActiveTrue(Integer externalId);

    List<Regional> findByActiveTrue();
}