package br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request;

import br.gov.mt.seplag.artistalbumapi.modules.shared.enums.SortDirection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Schema(description = "DTO para listagem de artistas por tipo com paginação e ordenação")
public record GetArtistsByTypeRequestDTO(

        @NotNull
        @Min(1)
        @Schema(description = "Número da página (1-based)", example = "1")
        Integer page,

        @NotNull
        @Min(1)
        @Max(50)
        @Schema(description = "Tamanho da página", example = "10")
        Integer size,

        @Schema(description = "Campo para ordenação", example = "name", defaultValue = "name")
        String sortBy,

        @Schema(description = "Direção da ordenação", example = "ASC", defaultValue = "ASC")
        SortDirection direction
) {
    public GetArtistsByTypeRequestDTO {
        sortBy = sortBy != null ? sortBy : "name";
        direction = direction != null ? direction : SortDirection.ASC;
    }

    public Pageable toPageable() {
        return PageRequest.of(
                page - 1,
                size,
                Sort.by(direction.toSpringDirection(), sortBy)
        );
    }
}