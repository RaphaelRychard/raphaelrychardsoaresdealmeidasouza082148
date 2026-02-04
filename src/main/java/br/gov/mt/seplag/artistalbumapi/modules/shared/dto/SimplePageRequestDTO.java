package br.gov.mt.seplag.artistalbumapi.modules.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public record SimplePageRequestDTO(

        @NotNull
        @Min(1)
        @Schema(description = "Número da página (1-based)", example = "1")
        Integer page,

        @NotNull
        @Min(1)
        @Max(50)
        @Schema(description = "Tamanho da página", example = "10")
        Integer size
) {
    public Pageable toPageable() {
        return PageRequest.of(page - 1, size);
    }
}
