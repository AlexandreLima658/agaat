package br.gov.pnae.agaat.domain.cecanes.atributos;

import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;

public record CecaneNome(String value) {
    public static final int CECANE_NOME_MAX_LENGTH = 255;

    public void validate() {
        if (value == null || value.isBlank()) {
            throw new DomainException("Nome do Cecane não pode ser nulo ou vazio");
        }

        if (value.length() > CECANE_NOME_MAX_LENGTH) {
            throw new DomainException("Nome do Cecane não pode ter mais de 255 caracteres");
        }
    }
}
