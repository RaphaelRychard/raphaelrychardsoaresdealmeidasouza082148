package br.gov.mt.seplag.artistalbumapi.modules.regional.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import br.gov.mt.seplag.artistalbumapi.modules.regional.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllRegionalsUseCase {

    @Autowired
    private RegionalRepository regionalRepository;

    public List<Regional> execute() {
        return regionalRepository.findAll();
    }
}
