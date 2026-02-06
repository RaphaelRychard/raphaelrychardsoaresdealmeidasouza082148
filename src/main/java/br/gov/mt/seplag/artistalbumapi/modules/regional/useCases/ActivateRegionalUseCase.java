package br.gov.mt.seplag.artistalbumapi.modules.regional.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalAlreadyActiveForExternalIdException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivateRegionalUseCase {

    @Autowired
    private RegionalRepository regionalRepository;

    @Transactional
    public void execute(Long id) {
        Regional regional = regionalRepository.findById(id)
                .orElseThrow(() -> new RegionalNotFoundException(id.toString()));

        if (!Boolean.TRUE.equals(regional.getActive())) {
            Integer externalId = regional.getExternalId();

            boolean existsOtherActive = regionalRepository
                    .existsByExternalIdAndActiveTrueAndIdNot(externalId, regional.getId());

            if (existsOtherActive) {
                throw new RegionalAlreadyActiveForExternalIdException(externalId);
            }

            regional.setActive(true);
            regionalRepository.save(regional);
        }
    }
}