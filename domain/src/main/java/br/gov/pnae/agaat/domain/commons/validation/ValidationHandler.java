package br.gov.pnae.agaat.domain.commons.validation;

import java.util.List;

public interface ValidationHandler {
    ValidationHandler append(Error error);

    ValidationHandler append(ValidationHandler handler);

    ValidationHandler validate(Validation validation);

    List<Error> getErrors();

    default boolean hasErrors() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError() {
        return (getErrors() != null && !getErrors().isEmpty()) ? getErrors().getFirst() : null;
    }

    public interface Validation {
        void validate();
    }
}
