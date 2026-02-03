package br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request;


import br.gov.mt.seplag.artistalbumapi.modules.auth.enums.UserRole;

public record RegisterRequestDTO(String login, String password, UserRole role) {
}
