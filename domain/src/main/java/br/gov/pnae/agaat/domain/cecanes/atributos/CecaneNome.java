package br.gov.pnae.agaat.domain.cecanes.atributos;

import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;

public record CecaneNome(String value) {
    public static final int CECANE_NOME_MAX_LENGTH = 255;

    public CecaneNome {
        if (value == null || value.isBlank()) {
            throw DomainException.with("Nome do Cecane não pode ser nulo ou vazio");
        }

        if (value.length() > CECANE_NOME_MAX_LENGTH) {
            throw DomainException.with("Nome do Cecane não pode ter mais de %s caracteres".formatted(CECANE_NOME_MAX_LENGTH));
        }
    }
}
