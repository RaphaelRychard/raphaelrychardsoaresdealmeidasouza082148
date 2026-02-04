package br.gov.mt.seplag.artistalbumapi.modules.album.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AlbumResponseDTO(

        @Schema(
                description = "ID único do álbum",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Título do álbum",
                example = "Hybrid Theory"
        )
        String title,

        @Schema(
                description = "Ano de lançamento do álbum",
                example = "2000"
        )
        Integer releaseYear,

        @Schema(
                description = "Chave da imagem de capa no storage",
                example = "albums/hybrid-theory.png"
        )
        String coverImageKey,

        @Schema(
                description = "Data de criação",
                example = "2024-01-10T14:32:45"
        )
        LocalDateTime createdAt
) {}
