package br.gov.pnae.agaat.domain.commons.exceptions;

public class DomainException extends NoStackTraceException {
    public DomainException(final String message) {
        super(message);
    }

    public DomainException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
