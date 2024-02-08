package br.gov.pnae.agaat.domain.commons.exceptions;

import br.gov.pnae.agaat.domain.commons.validation.Error;

import java.util.List;

public class DomainException extends NoStackTraceException {
    private List<Error> errors;

    public DomainException(final String message) {
        super(message);
    }

    public DomainException(final String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public DomainException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static DomainException with(final Error error) {
        return new DomainException(error.message(), List.of(error));
    }

    public static DomainException with(final List<Error> error) {
        return new DomainException("", error);
    }

    public List<Error> getErrors() {
        return this.errors;
    }
}
