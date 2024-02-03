package br.gov.pnae.agaat.domain.cecanes.fields;

import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;

public record CecaneNome(String value) {

    public CecaneNome {

        if (value == null || value.isBlank()) {
            throw new DomainException("Nome do Cecane não pode ser nulo ou vazio");
        }

        if (value.length() > 255) {
            throw new DomainException("Nome do Cecane não pode ter mais de 255 caracteres");
        }
    }
}

