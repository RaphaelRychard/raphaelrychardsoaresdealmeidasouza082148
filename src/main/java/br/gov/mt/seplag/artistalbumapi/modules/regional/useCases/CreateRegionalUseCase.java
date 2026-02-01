package br.gov.mt.seplag.artistalbumapi.modules.regional.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.RegionalEntity;
import br.gov.mt.seplag.artistalbumapi.modules.regional.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRegionalUseCase {

    @Autowired
    private RegionalRepository regionalRepository;

    public RegionalEntity execute(RegionalEntity regionalEntity) {
        boolean existExternalId = regionalRepository.existsByExternalId(regionalEntity.getExternalId());

        if (existExternalId) {
            throw new IllegalArgumentException("Regional with this externalId already exists");
        }

        return regionalRepository.save(regionalEntity);
    }
}
