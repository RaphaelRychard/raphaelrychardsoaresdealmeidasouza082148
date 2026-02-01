package br.gov.mt.seplag.artistalbumapi.modules.album.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class CreateAlbumRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Schema(description = "Título do álbum", example = "The Rising Tied")
    private String title;

    @NotNull(message = "O ano é obrigatório")
    @Schema(description = "Ano de lançamento", example = "2012")
    private Year releaseYear;

    @NotBlank(message = "A imagem de capa é obrigatória")
    @Schema(description = "Chave da imagem no storage", example = "albums/cover.jpg")
    private String coverImageKey;
}