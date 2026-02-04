package br.gov.mt.seplag.artistalbumapi.modules.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Resposta simplificada de paginação")
public record SimplePageResponseDTO<T>(

        @Schema(description = "Lista de itens da página", example = "[{\"id\":1,\"title\":\"Hybrid Theory\",\"releaseYear\":2000,\"coverImageKey\":\"albums/hybrid-theory.png\",\"createdAt\":\"2024-01-10T14:32:45\"}]")
        List<T> content,

        @Schema(description = "Quantidade total de elementos disponíveis", example = "1")
        long totalElements,

        @Schema(description = "Número total de páginas disponíveis", example = "1")
        int totalPages) {
}
