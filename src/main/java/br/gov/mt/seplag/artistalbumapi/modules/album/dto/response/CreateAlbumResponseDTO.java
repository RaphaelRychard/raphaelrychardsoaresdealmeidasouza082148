package br.gov.mt.seplag.artistalbumapi.modules.album.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Year;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlbumResponseDTO {

    @Schema(description = "ID único do álbum", example = "1")
    private Long id;

    @Schema(description = "Título do álbum", example = "The Rising Tied")
    private String title;

    @Schema(description = "Ano de lançamento do álbum", example = "2012")
    private Year releaseYear;

    @Schema(description = "Chave da imagem de capa no storage", example = "albums/the_rising_tied_cover.jpg")
    private String coverImageKey;
}
