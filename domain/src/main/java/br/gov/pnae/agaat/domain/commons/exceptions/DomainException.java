package br.gov.pnae.agaat.domain.commons.exceptions;

public class DomainException extends RuntimeException {

    public DomainException(final String message) {
        super(message, null, true, false);
    }

    public DomainException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }

}
