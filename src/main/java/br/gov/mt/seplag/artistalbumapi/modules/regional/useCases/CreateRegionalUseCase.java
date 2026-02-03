package br.gov.mt.seplag.artistalbumapi.modules.regional.useCases;

import br.gov.mt.seplag.artistalbumapi.exceptions.RegionalExternalIdAlreadyExistsException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import br.gov.mt.seplag.artistalbumapi.modules.regional.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRegionalUseCase {

    @Autowired
    private RegionalRepository regionalRepository;

    public void execute(Regional regional) {
        boolean existExternalId = regionalRepository.existsByExternalId(regional.getExternalId());

        if (existExternalId) {
            throw new RegionalExternalIdAlreadyExistsException(regional.getExternalId().toString());
        }

        regionalRepository.save(regional);
    }
}
